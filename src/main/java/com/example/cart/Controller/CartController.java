package com.example.cart.Controller;

import com.example.cart.Configuration.datasource.Employee;
import com.example.cart.Service.CartService;
import com.example.cart.api.CartApi;
import com.example.cart.dto.ProductCartDTO;
import com.example.cart.dto.ProductCartResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController implements CartApi {

    private final CartService cartService;
    @Autowired
    Employee e;

    public CartController(CartService cartService){
        this.cartService=cartService;
    }

    @Override
    public ResponseEntity<String> addCartItem(ProductCartDTO productCartDTO) {
        return new ResponseEntity<>(cartService.addCartItem(productCartDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductCartResponseDTO>> getCartItems() {
        return new ResponseEntity<>(cartService.getCartItems(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> temp() {
        return  e.allEmp();
    }
}
