package com.Shopping.Ecommerce.App.Service.Impl;

import com.Shopping.Ecommerce.App.Convertor.CustomerConvertor;
import com.Shopping.Ecommerce.App.Exception.CustomerNotFoundException;
import com.Shopping.Ecommerce.App.Model.Card;
import com.Shopping.Ecommerce.App.Model.Cart;
import com.Shopping.Ecommerce.App.Model.Customer;
import com.Shopping.Ecommerce.App.Repository.CardRepository;
import com.Shopping.Ecommerce.App.Repository.CustomerRepository;
import com.Shopping.Ecommerce.App.RequestDTO.CustomerRequestDto;
import com.Shopping.Ecommerce.App.RequestDTO.RemoveCardForCustomerDto;
import com.Shopping.Ecommerce.App.ResponseDTO.CustomerResponseDto;
import com.Shopping.Ecommerce.App.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws Exception {

        String email = customerRequestDto.getEmail();
        String mobile = customerRequestDto.getMobNo();
        List<Customer> customerList = customerRepository.findAll();
        for(Customer customer : customerList){
            if(customer.getEmail().equals(email)){
                throw new Exception("Email Already exists");
            }
            if(customer.getMobNo().equals(mobile)){
                throw new Exception("Mobile already exists");
            }
        }

        //build the customer
        Customer customer = CustomerConvertor.customerRequestDtoToCustomer(customerRequestDto);

        //make the cart for customer
        Cart cart = new Cart();
        cart.setTotalBill(0);

        customer.setCart(cart);
        cart.setCustomer(customer);

        customerRepository.save(customer);

        //build the customer response dto
        return CustomerConvertor.customerToCustomerResponseDto(customer);
    }

    @Override
    public CustomerResponseDto getCustomerByID(int id) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findById(id).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException(e.getMessage());
        }

        return CustomerConvertor.customerToCustomerResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        for(Customer customer : customerList){
            CustomerResponseDto customerResponseDto = CustomerConvertor.customerToCustomerResponseDto(customer);
            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }

    @Override
    public void deleteCustomerByID(int id) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findById(id).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }

        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponseDto getCustomerByEmail(String email) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }

        return CustomerConvertor.customerToCustomerResponseDto(customer);
    }

    @Override
    public void removeCard(RemoveCardForCustomerDto removeCardForCustomerDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(removeCardForCustomerDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new Exception("Customer not found");
        }

        Card card;
        try{
            card = cardRepository.findById(removeCardForCustomerDto.getCardId()).get();
        }
        catch (Exception e){
            throw new Exception("Card not found");
        }

        cardRepository.delete(card);
    }

    @Override
    public void removeAllCards(int customerId) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new Exception("Customer not found");
        }

        List<Card> cards = cardRepository.findAll();
        for(Card card : cards){
            cardRepository.delete(card);
        }
    }
}

/*
 i want to place an order
 step 1 :- make a order request DTO
 dto - customerid, productid, quantity

 step 2 :- check if customer, product, and quantity exists

 step 3 :- make copy of product (item)

 step 4 :- make order object
 step 5 :- set required objects according to parent child relation

 step 6 :- save the order













*/










