package com.Shopping.Ecommerce.App.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequestDto {

    private String name;
    private String mobNo;
    private String email;
    private String panNo;
}
