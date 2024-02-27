package com.example.userorderservice.Dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderBillDto {
    private String orderId;
    private String userId;
    private String userEmail;
    private List<Product> products;
    private Double totalPrice;

    @Data
    public static class Product {
        private String productId;
        private String name;
        private int quantity;
        private Double price;
        private Double gst;
        private Double totalPrice;
    }
}
