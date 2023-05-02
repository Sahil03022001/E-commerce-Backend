package com.Shopping.Ecommerce.App.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private int customerId;
    private int productId;
    private int requiredQuantity;
}
