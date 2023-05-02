package com.Shopping.Ecommerce.App.RequestDTO;

import com.Shopping.Ecommerce.App.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequestDto {

    private String name;
    private int price;
    private Category category;
    private int quantity;
    private int sellerID;
}
