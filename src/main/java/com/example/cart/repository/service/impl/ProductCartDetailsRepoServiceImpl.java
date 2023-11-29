package com.example.cart.repository.service.impl;

import com.example.cart.model.ProductCart;
import com.example.cart.model.ProductCartDetails;
import com.example.cart.repository.ProductCartDetailsRepository;
import com.example.cart.repository.service.ProductCartDetailsRepoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCartDetailsRepoServiceImpl implements ProductCartDetailsRepoService {

    private final ProductCartDetailsRepository productCartDetailsRepository;

    public ProductCartDetailsRepoServiceImpl(ProductCartDetailsRepository productCartDetailsRepository){
        this.productCartDetailsRepository=productCartDetailsRepository;
    }

    @Override
    public Optional<ProductCartDetails> findByProductCartUserIdAndProductIdAndPlanIdAndProductCart(String userId,
            String productId, String planId, ProductCart productCart) {
        return productCartDetailsRepository.findByProductCartUserIdAndProductIdAndPlanIdAndProductCart(userId, productId, planId, productCart);
    }

    @Override
    public Optional<List<ProductCartDetails>> findByProductCartUserIdAndProductCartOrderIdNull(String userId) {
        return productCartDetailsRepository.findByProductCartUserIdAndProductCartOrderIdNull(userId);
    }
}
