package com.example.qrcode.Api;

import com.example.qrcode.Dto.qrReadDto;
import com.example.qrcode.Model.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface Api {

    @GetMapping("/qrcode")
    ResponseEntity<byte[]> generateQr();
    @PostMapping("/user")
    void saveUser(@RequestBody UserDetails userDetails);
    @PostMapping("/createConnection")
    ResponseEntity<UserDetails> connection(@RequestBody qrReadDto qrReadDto);
}
