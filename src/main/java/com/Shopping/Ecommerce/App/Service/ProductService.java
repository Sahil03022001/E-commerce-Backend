package com.Shopping.Ecommerce.App.Service;

import com.Shopping.Ecommerce.App.Exception.SellerNotPresentException;
import com.Shopping.Ecommerce.App.RequestDTO.ProductRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto registerProduct(ProductRequestDto productRequestDto) throws Exception;

    List<ProductResponseDto> getAllProducts();

    List<ProductResponseDto> get5MostExpensiveProducts();

    List<ProductResponseDto> get5LeastExpensiveProducts();
}
