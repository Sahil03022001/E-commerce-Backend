package com.Shopping.Ecommerce.App.Convertor;

import com.Shopping.Ecommerce.App.Enum.ProductStatus;
import com.Shopping.Ecommerce.App.Model.Product;
import com.Shopping.Ecommerce.App.RequestDTO.ProductRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.ProductResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .category(productRequestDto.getCategory())
                .productStatus(ProductStatus.IN_STOCK)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .sellerName(product.getSeller().getName())
                .productStatus(product.getProductStatus())
                .build();
    }
}
