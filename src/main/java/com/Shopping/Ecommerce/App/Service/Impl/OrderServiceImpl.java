package com.Shopping.Ecommerce.App.Service.Impl;

import com.Shopping.Ecommerce.App.Convertor.OrderConvertor;
import com.Shopping.Ecommerce.App.Enum.ProductStatus;
import com.Shopping.Ecommerce.App.Exception.CustomerNotFoundException;
import com.Shopping.Ecommerce.App.Exception.ProductNotFoundException;
import com.Shopping.Ecommerce.App.Model.*;
import com.Shopping.Ecommerce.App.Repository.CustomerRepository;
import com.Shopping.Ecommerce.App.Repository.ItemRepository;
import com.Shopping.Ecommerce.App.Repository.OrderRepository;
import com.Shopping.Ecommerce.App.Repository.ProductRepository;
import com.Shopping.Ecommerce.App.RequestDTO.OrderRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.OrderResponseDto;
import com.Shopping.Ecommerce.App.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception{

        //check if customer exists
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }

        //check if custamer has card for payment
        if(customer.getCards().size() == 0){
            throw new Exception("Customer don't have card for payment");
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

        product.setQuantity(-orderRequestDto.getRequiredQuantity() + product.getQuantity());
        if(product.getQuantity() == 0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

        int requiredQuantity = orderRequestDto.getRequiredQuantity();

        //make item object
        Item item = new Item();
        item.setRequiredQuantity(requiredQuantity);
        item.setProduct(product);

        //make order object
        Ordered order = new Ordered();
        order.setTransactionID(String.valueOf(UUID.randomUUID()));
        order.setTotalAmount(requiredQuantity * product.getPrice());
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

        //create the order response dto
        return OrderConvertor.orderToOrderResponseDto(order, customer, product, requiredQuantity);
    }
}
