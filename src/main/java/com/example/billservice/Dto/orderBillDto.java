package com.example.billservice.Dto;

import lombok.Data;

import java.util.List;

@Data
public class orderBillDto {
    private String orderId;
    private String userId;
    private List<Product> products;
    private Double totalPrice;

    @Data
    public static class Product {
        private String productId;
        private int quantity;
        private Double price;
        private Double gst;
    }
}
