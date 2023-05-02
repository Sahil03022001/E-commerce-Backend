package com.Shopping.Ecommerce.App.Convertor;

import com.Shopping.Ecommerce.App.Model.Customer;
import com.Shopping.Ecommerce.App.Model.Ordered;
import com.Shopping.Ecommerce.App.Model.Product;
import com.Shopping.Ecommerce.App.ResponseDTO.OrderResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConvertor {

    public static OrderResponseDto orderToOrderResponseDto(Ordered order, Customer customer, Product product, int reqQuantity){
        return OrderResponseDto.builder()
                .customerName(customer.getName())
                .customerEmail(customer.getEmail())
                .customerMobileNo(customer.getMobNo())
                .productName(product.getName())
                .productCategory(product.getCategory())
                .sellerName(product.getSeller().getName())
                .requiredQuantity(reqQuantity)
                .orderedDate(order.getOrderedDate())
                .cardUsed(order.getCardUsed())
                .totalAmount(order.getTotalAmount())
                .transactionID(order.getTransactionID())
                .build();
    }
}
