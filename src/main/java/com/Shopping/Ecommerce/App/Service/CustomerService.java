package com.Shopping.Ecommerce.App.Service;

import com.Shopping.Ecommerce.App.Exception.CustomerNotFoundException;
import com.Shopping.Ecommerce.App.RequestDTO.CustomerRequestDto;
import com.Shopping.Ecommerce.App.RequestDTO.RemoveCardForCustomerDto;
import com.Shopping.Ecommerce.App.ResponseDTO.CustomerResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws Exception;

    CustomerResponseDto getCustomerByID(int id) throws CustomerNotFoundException;

    List<CustomerResponseDto> getAllCustomers();

    void deleteCustomerByID(int id) throws CustomerNotFoundException;

    CustomerResponseDto getCustomerByEmail(String email) throws CustomerNotFoundException;

    void removeCard(RemoveCardForCustomerDto removeCardForCustomerDto) throws Exception;

    void removeAllCards(int customerId) throws Exception;
}
