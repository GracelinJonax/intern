package com.example.cart.api;

import com.example.cart.dto.ProductCartDTO;
import com.example.cart.dto.ProductCartResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface CartApi {

    @PutMapping(value = "/productCart")
    ResponseEntity<String> addCartItem(@RequestBody ProductCartDTO productCartDTO);

    @GetMapping(value = "/productCart")
    ResponseEntity<List<ProductCartResponseDTO>> getCartItems();

    @GetMapping(value = "/rest")
    ResponseEntity<String> temp();

}
