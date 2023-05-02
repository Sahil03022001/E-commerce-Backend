package com.Shopping.Ecommerce.App.Service;

import com.Shopping.Ecommerce.App.Exception.CustomerNotFoundException;
import com.Shopping.Ecommerce.App.RequestDTO.OrderRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.OrderResponseDto;

import java.util.List;

public interface CartService {

    void addItem(OrderRequestDto orderRequestDto) throws Exception;

    List<OrderResponseDto> checkoutCart(int customerId) throws Exception;
}
