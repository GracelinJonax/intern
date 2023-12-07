package com.example.cart.Service;

import com.example.cart.dto.ProductCartDTO;
import com.example.cart.dto.ProductCartResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    String addCartItem(ProductCartDTO productCartDTO);

    List<ProductCartResponseDTO> getCartItems();

    ResponseEntity<String> temp();
}
