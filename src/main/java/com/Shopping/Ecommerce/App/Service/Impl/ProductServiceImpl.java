package com.Shopping.Ecommerce.App.Service.Impl;

import com.Shopping.Ecommerce.App.Convertor.ProductConvertor;
import com.Shopping.Ecommerce.App.Enum.Category;
import com.Shopping.Ecommerce.App.Exception.SellerNotPresentException;
import com.Shopping.Ecommerce.App.Model.Item;
import com.Shopping.Ecommerce.App.Model.Product;
import com.Shopping.Ecommerce.App.Model.Seller;
import com.Shopping.Ecommerce.App.Repository.ProductRepository;
import com.Shopping.Ecommerce.App.Repository.SellerRepository;
import com.Shopping.Ecommerce.App.RequestDTO.ProductRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.ProductResponseDto;
import com.Shopping.Ecommerce.App.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            SellerRepository sellerRepository
    ) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public ProductResponseDto registerProduct(ProductRequestDto productRequestDto) throws SellerNotPresentException {
        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerID()).get();
        }
        catch (Exception e){
            throw new SellerNotPresentException("Invalid Seller Id");
        }

        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);
        sellerRepository.save(seller);

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
    public List<ProductResponseDto> getAllProductsByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);
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
