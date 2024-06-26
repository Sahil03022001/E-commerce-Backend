package com.Shopping.Ecommerce.App.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequestDto {

    private String name;
    private int price;
    private String categoryName;
    private int quantity;
    private int sellerID;
}
