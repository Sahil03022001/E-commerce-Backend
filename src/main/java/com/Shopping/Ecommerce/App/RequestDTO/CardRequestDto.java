package com.Shopping.Ecommerce.App.RequestDTO;

import com.Shopping.Ecommerce.App.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {

    private String cardNo;
    private int cvv;
    private CardType cardType;
    private int customerId;
}
