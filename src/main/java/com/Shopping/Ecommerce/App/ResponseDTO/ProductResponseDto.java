package com.Shopping.Ecommerce.App.ResponseDTO;

import com.Shopping.Ecommerce.App.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponseDto {

    private String productName;
    private int price;
    private String categoryName;
    private int quantity;
    private String sellerName;
    private ProductStatus productStatus;
}
