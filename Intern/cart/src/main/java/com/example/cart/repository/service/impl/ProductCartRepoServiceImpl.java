package com.example.cart.repository.service.impl;

import com.example.cart.model.ProductCart;
import com.example.cart.repository.ProductCartRepository;
import com.example.cart.repository.service.ProductCartRepoService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductCartRepoServiceImpl implements ProductCartRepoService {
    private final ProductCartRepository productCartRepository;
    public ProductCartRepoServiceImpl(ProductCartRepository productCartRepository){
        this.productCartRepository=productCartRepository;
    }

    @Override
    public Optional<ProductCart> findByUserIdAndBillingTypeAndOrderIdNull(String userId, String billingType) {
        return productCartRepository.findByUserIdAndBillingTypeAndOrderIdNull(userId,billingType);
    }
}
