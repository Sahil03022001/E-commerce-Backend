package com.Shopping.Ecommerce.App.Service;

import com.Shopping.Ecommerce.App.Exception.CustomerNotFoundException;
import com.Shopping.Ecommerce.App.RequestDTO.CardRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.CardResponseDto;

import java.util.List;

public interface CardService {

    CardResponseDto addCard(CardRequestDto cardRequestDto) throws Exception;
}