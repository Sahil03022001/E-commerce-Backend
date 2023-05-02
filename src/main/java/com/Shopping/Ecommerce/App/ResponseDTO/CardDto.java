package com.Shopping.Ecommerce.App.ResponseDTO;

import com.Shopping.Ecommerce.App.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private String cardNo;
    private CardType cardType;
}
