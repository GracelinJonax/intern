package com.example.userorderservice.Dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private String userId;
    private List<Product> productDetailsList;

    @Data
    public static class Product {
        private String productId;
        private int quantity;
    }
}
