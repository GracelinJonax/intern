package com.example.userorderservice.Dto;

import com.example.userorderservice.model.ProductDetails;
import lombok.Data;

import java.util.List;

@Data
public class orderDto {
    private String userId;
    private List<Product> productDetailsList;

    @Data
    public static class Product {
        private String productId;
        private int quantity;
    }
}
