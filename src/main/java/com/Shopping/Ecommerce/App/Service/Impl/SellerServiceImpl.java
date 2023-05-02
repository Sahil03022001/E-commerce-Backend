package com.Shopping.Ecommerce.App.Service.Impl;

import com.Shopping.Ecommerce.App.Convertor.SellerConvertor;
import com.Shopping.Ecommerce.App.Exception.SellerNotPresentException;
import com.Shopping.Ecommerce.App.Model.Seller;
import com.Shopping.Ecommerce.App.Repository.SellerRepository;
import com.Shopping.Ecommerce.App.RequestDTO.SellerRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.SellerResponseDto;
import com.Shopping.Ecommerce.App.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public void registerSeller(SellerRequestDto sellerRequestDto) {
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
