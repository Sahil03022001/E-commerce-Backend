package com.Shopping.Ecommerce.App.Controller;

import com.Shopping.Ecommerce.App.Enum.Category;
import com.Shopping.Ecommerce.App.RequestDTO.ProductRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.ProductResponseDto;
import com.Shopping.Ecommerce.App.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("add")
    public ResponseEntity registerProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto;
        try{
            productResponseDto = productService.registerProduct(productRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productResponseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public List<ProductResponseDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/getAllByCategory/{category}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("category") Category category){
        return productService.getAllProductsByCategory(category);
    }

    @GetMapping("/5MostExpensive")
    public List<ProductResponseDto> get5MostExpensiveProducts(){
        return productService.get5MostExpensiveProducts();
    }

    @GetMapping("/5LeastExpensive")
    public List<ProductResponseDto> get5LeastExpensiveProducts(){
        return productService.get5LeastExpensiveProducts();
    }
}
