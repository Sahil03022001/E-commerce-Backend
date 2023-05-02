package com.Shopping.Ecommerce.App.Service.Impl;

import com.Shopping.Ecommerce.App.Convertor.OrderConvertor;
import com.Shopping.Ecommerce.App.Enum.ProductStatus;
import com.Shopping.Ecommerce.App.Exception.CustomerNotFoundException;
import com.Shopping.Ecommerce.App.Exception.ProductNotFoundException;
import com.Shopping.Ecommerce.App.Model.*;
import com.Shopping.Ecommerce.App.Repository.*;
import com.Shopping.Ecommerce.App.RequestDTO.OrderRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.OrderResponseDto;
import com.Shopping.Ecommerce.App.Service.CartService;
import com.Shopping.Ecommerce.App.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;


    @Override
    public void addItem(OrderRequestDto orderRequestDto) throws Exception {

        //check if customer exists
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }

        //check if product exists
        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Product not found");
        }

        //check if product is in stock
        if(product.getProductStatus().equals(ProductStatus.OUT_OF_STOCK)){
            throw new Exception("Product is out of stock");
        }

        //check if required quantity exists in stock
        if(orderRequestDto.getRequiredQuantity() > product.getQuantity()){
            throw new Exception("Required quantity not available in stock");
        }

        int requiredQuantity = orderRequestDto.getRequiredQuantity();

        //create the item to add in cart
        Item item = new Item();
        item.setRequiredQuantity(requiredQuantity);
        item.setProduct(product);

        //get the cart from customer id
        Cart cart = customer.getCart();
        int billForTheItem = product.getPrice() * requiredQuantity;
        int totalBill = cart.getTotalBill() + billForTheItem;
        cart.setTotalBill(totalBill);
        item.setCart(cart);
        cart.getItems().add(item);

        cartRepository.save(cart);
    }

    @Override
    public List<OrderResponseDto> checkoutCart(int customerId) throws Exception {

        //check if customer exists
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }

        //get the cart of customer
        Cart cart = customer.getCart();

        //check if items are present in cart
        if(cart.getItems().size() == 0){
            throw new Exception("Your cart is Empty!");
        }

        //call place order for each item
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        for(Item item : cart.getItems()){

            int price = item.getProduct().getPrice();
            int quantity = item.getRequiredQuantity();

            //create the order
            Ordered order = new Ordered();
            order.setTransactionID(String.valueOf(UUID.randomUUID()));
            order.setTotalAmount(quantity * price);
            order.setCustomer(customer);

            //create the string for card used
            Card card = customer.getCards().get(0);
            String cardNo = card.getCardNo();
            String cardNumber = "";
            for(int i=0; i<12; i++){
                cardNumber += 'X';
            }
            cardNumber += cardNo.substring(12);

            order.setCardUsed(cardNumber);
            item.setOrder(order);
            order.getItems().add(item);
            customer.getOrderedList().add(order);

            //save order and item
            orderRepository.save(order);

            Product product = item.getProduct();

            //create the order response dto
            OrderResponseDto orderResponseDto = OrderConvertor.orderToOrderResponseDto(order, customer, product, quantity);
            orderResponseDtos.add(orderResponseDto);
        }

        cart.setItems(new ArrayList<>());
        cart.setTotalBill(0);
        return orderResponseDtos;
    }
}
