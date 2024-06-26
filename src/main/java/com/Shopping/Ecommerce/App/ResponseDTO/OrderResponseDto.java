package com.Shopping.Ecommerce.App.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private String customerName;
    private String customerEmail;
    private String customerMobileNo;

    private String productName;
    private String productCategory;
    private String sellerName;
    private int requiredQuantity;

    private String transactionID;
    private Date orderedDate;
    private String cardUsed;
    private int totalAmount;
}
