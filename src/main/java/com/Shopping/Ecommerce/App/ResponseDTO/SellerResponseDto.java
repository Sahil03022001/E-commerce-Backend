package com.Shopping.Ecommerce.App.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SellerResponseDto {

    private String name;
    private String mobNo;
    private String email;
}
