package com.Shopping.Ecommerce.App.Controller;

import com.Shopping.Ecommerce.App.RequestDTO.SellerRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.SellerResponseDto;
import com.Shopping.Ecommerce.App.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerSeller(@RequestBody SellerRequestDto sellerRequestDto) throws Exception {
        sellerService.registerSeller(sellerRequestDto);
        return new ResponseEntity<>("Seller Registered", HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllSellers(){
        List<SellerResponseDto> sellerResponseDtoList = sellerService.getAllSellers();
        if(sellerResponseDtoList.size() == 0){
            return new ResponseEntity<>("No Sellers available", HttpStatus.OK);
        }
        return new ResponseEntity<>(sellerResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/getSellerByPanNo")
    public ResponseEntity getSellerByPanNumber(@RequestParam String panNo){
        SellerResponseDto sellerResponseDto;
        try{
            sellerResponseDto = sellerService.getSellerByPanNumber(panNo);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sellerResponseDto, HttpStatus.OK);
    }
}