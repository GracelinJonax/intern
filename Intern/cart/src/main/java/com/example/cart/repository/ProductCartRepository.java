package com.example.cart.repository;

import com.example.cart.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart,String> {
    Optional<ProductCart> findByUserIdAndBillingTypeAndOrderIdNull(String userId, String billingType);

}
