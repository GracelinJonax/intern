package com.example.cart.repository.service;

import com.example.cart.model.ProductCart;
import com.example.cart.model.ProductCartDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductCartDetailsRepoService {

    Optional<ProductCartDetails> findByProductCartUserIdAndProductIdAndPlanIdAndProductCart(String userId, String productId, String planId, ProductCart productCart);

    Optional<List<ProductCartDetails>> findByProductCartUserIdAndProductCartOrderIdNull(String userId);

}
