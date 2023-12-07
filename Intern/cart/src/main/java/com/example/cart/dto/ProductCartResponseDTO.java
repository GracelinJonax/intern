package com.example.cart.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductCartResponseDTO  {

    private String cartId;

    private String billingType;

    private List<Item> items;

    @Data
    public static class Item {

        private String productId;

        private String planId;

        private Integer quantity;

        private String skuId;

        private String applicationId;

    }

}
