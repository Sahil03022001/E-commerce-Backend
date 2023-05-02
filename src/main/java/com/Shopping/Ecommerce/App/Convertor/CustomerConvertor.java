package com.Shopping.Ecommerce.App.Convertor;

import com.Shopping.Ecommerce.App.Model.Customer;
import com.Shopping.Ecommerce.App.RequestDTO.CustomerRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.CustomerResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConvertor {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .build();
    }
}
