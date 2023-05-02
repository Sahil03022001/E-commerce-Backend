package com.Shopping.Ecommerce.App.Controller;

import com.Shopping.Ecommerce.App.RequestDTO.OrderRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.OrderResponseDto;
import com.Shopping.Ecommerce.App.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addItem(@RequestBody OrderRequestDto orderRequestDto){
        try{
            cartService.addItem(orderRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Item added in cart", HttpStatus.ACCEPTED);
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity checkoutCart(@PathVariable("id") int customerId){
        List<OrderResponseDto> orderResponseDtos;
        try{
            orderResponseDtos = cartService.checkoutCart(customerId);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderResponseDtos, HttpStatus.ACCEPTED);
    }
}
