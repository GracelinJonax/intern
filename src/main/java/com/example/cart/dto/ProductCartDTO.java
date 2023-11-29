package com.example.cart.dto;

import lombok.Data;

@Data
public class ProductCartDTO {

    private String productId;

    private String planId;

    private int quantity;

    private String billingType;

    private String applicationId;

    private String skuId;

}
