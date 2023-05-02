package com.Shopping.Ecommerce.App.ResponseDTO;

import com.Shopping.Ecommerce.App.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {

    private String name;
    private String email;
    private String mobNo;
}
