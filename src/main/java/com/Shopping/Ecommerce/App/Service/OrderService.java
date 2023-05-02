package com.Shopping.Ecommerce.App.Service;


import com.Shopping.Ecommerce.App.RequestDTO.OrderRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.OrderResponseDto;

public interface OrderService {

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;
}
