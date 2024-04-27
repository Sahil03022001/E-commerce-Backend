package com.Shopping.Ecommerce.App.Service.Impl;

import com.Shopping.Ecommerce.App.Convertor.ProductConvertor;
import com.Shopping.Ecommerce.App.Exception.SellerNotPresentException;
import com.Shopping.Ecommerce.App.Model.Category;
import com.Shopping.Ecommerce.App.Model.Product;
import com.Shopping.Ecommerce.App.Model.Seller;
import com.Shopping.Ecommerce.App.Repository.CategoryRepository;
import com.Shopping.Ecommerce.App.Repository.ProductRepository;
import com.Shopping.Ecommerce.App.Repository.SellerRepository;
import com.Shopping.Ecommerce.App.RequestDTO.ProductRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.ProductResponseDto;
import com.Shopping.Ecommerce.App.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            SellerRepository sellerRepository,
            CategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDto registerProduct(ProductRequestDto productRequestDto) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerID()).get();
        }
        catch (Exception e){
            throw new SellerNotPresentException("Invalid Seller Id");
        }

        Category category = categoryRepository.getCategoryByName(productRequestDto.getCategoryName());
        if (category == null) {
            throw new Exception("Category does not exist with name : " + productRequestDto.getCategoryName());
        }


        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);
        product.setCategory(category);
        category.getProducts().add(product);

        sellerRepository.save(seller);
        categoryRepository.save(category);

        return ProductConvertor.productToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> get5MostExpensiveProducts() {
        List<Product> products = productRepository.find5HighestPrice();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> get5LeastExpensiveProducts() {
        List<Product> products = productRepository.find5LeastPrice();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }
}
