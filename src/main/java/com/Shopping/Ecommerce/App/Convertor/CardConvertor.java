package com.Shopping.Ecommerce.App.Convertor;

import com.Shopping.Ecommerce.App.Model.Card;
import com.Shopping.Ecommerce.App.RequestDTO.CardRequestDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardConvertor {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .build();
    }
}
