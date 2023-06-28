package com.Shopping.Ecommerce.App.Service;


import com.Shopping.Ecommerce.App.RequestDTO.SellerRequestDto;
import com.Shopping.Ecommerce.App.ResponseDTO.SellerResponseDto;

import java.util.List;

public interface SellerService {

    void registerSeller(SellerRequestDto sellerRequestDto) throws Exception;

    List<SellerResponseDto> getAllSellers();

    SellerResponseDto getSellerByPanNumber(String panNo) throws Exception;
}
