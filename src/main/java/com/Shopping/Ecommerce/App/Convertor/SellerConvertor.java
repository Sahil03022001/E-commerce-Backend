package com.Shopping.Ecommerce.App.Convertor;

import com.Shopping.Ecommerce.App.Model.Seller;
import com.Shopping.Ecommerce.App.RequestDTO.SellerRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.SellerResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConvertor {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .mobNo(sellerRequestDto.getMobNo())
                .email(sellerRequestDto.getEmail())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .email(seller.getEmail())
                .build();
    }
}
