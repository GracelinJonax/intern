package com.example.cart.repository;

import com.example.cart.model.ProductCart;
import com.example.cart.model.ProductCartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCartDetailsRepository extends JpaRepository<ProductCartDetails,String> {
    Optional<ProductCartDetails> findByProductCartUserIdAndProductIdAndPlanIdAndProductCart(String userId, String productId, String planId, ProductCart productCart);

    Optional<List<ProductCartDetails>> findByProductCartUserIdAndProductCartOrderIdNull(String userId);

}
