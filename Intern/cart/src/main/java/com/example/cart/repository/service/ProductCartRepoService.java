package com.example.cart.repository.service;

import com.example.cart.model.ProductCart;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductCartRepoService {
    Optional<ProductCart> findByUserIdAndBillingTypeAndOrderIdNull(String userId, String billingType);

}
