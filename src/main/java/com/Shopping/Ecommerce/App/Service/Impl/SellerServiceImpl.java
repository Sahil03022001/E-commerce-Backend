package com.Shopping.Ecommerce.App.Service.Impl;

import com.Shopping.Ecommerce.App.Convertor.SellerConvertor;
import com.Shopping.Ecommerce.App.Exception.SellerNotPresentException;
import com.Shopping.Ecommerce.App.Model.Customer;
import com.Shopping.Ecommerce.App.Model.Seller;
import com.Shopping.Ecommerce.App.Repository.SellerRepository;
import com.Shopping.Ecommerce.App.RequestDTO.SellerRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.SellerResponseDto;
import com.Shopping.Ecommerce.App.Service.SellerService;
import com.Shopping.Ecommerce.App.Validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void registerSeller(SellerRequestDto sellerRequestDto) throws Exception {

        String email = sellerRequestDto.getEmail();
        if(!Validation.validateEmail(sellerRequestDto.getEmail())) {
            throw new RuntimeException("Email not valid");
        }

        String mobile = sellerRequestDto.getMobNo();
        if(!Validation.validateMobile(sellerRequestDto.getMobNo())) {
            throw new RuntimeException("Mobile number not valid");
        }

        String panNo = sellerRequestDto.getPanNo();
        if(!Validation.validatePanNumber(panNo)) {
            throw new RuntimeException("Pan Number not valid");
        }

        List<Seller> sellerList = sellerRepository.findAll();
        for(Seller seller : sellerList){
            if(seller.getEmail().equals(email)){
                throw new Exception("Email Already exists");
            }
            if(seller.getMobNo().equals(mobile)){
                throw new Exception("Mobile already exists");
            }
            if(seller.getPanNo().equals(panNo)) {
                throw new RuntimeException("Pan number already exists");
            }
        }

        Seller seller = SellerConvertor.SellerRequestDtoToSeller(sellerRequestDto);
        sellerRepository.save(seller);
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        List<Seller> sellerList = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller seller : sellerList){
            SellerResponseDto sellerResponseDto = SellerConvertor.SellerToSellerResponseDto(seller);
            sellerResponseDtoList.add(sellerResponseDto);
        }
        return sellerResponseDtoList;
    }

    @Override
    public SellerResponseDto getSellerByPanNumber(String panNo) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.getSellerByPanNo(panNo);
        }
        catch (Exception e){
            throw new SellerNotPresentException("Invalid PAN NUMBER");
        }
        return SellerConvertor.SellerToSellerResponseDto(seller);
    }
}
