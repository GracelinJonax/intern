package com.example.qrcode.Controller;

import com.example.qrcode.Api.Api;
import com.example.qrcode.Dto.qrReadDto;
import com.example.qrcode.Model.UserDetails;
import com.example.qrcode.Service.service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Controller implements Api {
    public final service service;
    public Controller(service service){
        this.service=service;
    }

    @Override
    public ResponseEntity<byte[]> generateQr() {
        String uuid= UUID.randomUUID().toString();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(service.generateQrService(uuid));
    }

    @Override
    public void saveUser(UserDetails userDetails) {
        service.saveUserService(userDetails);
    }

    @Override
    public ResponseEntity<UserDetails> connection(qrReadDto qrReadDto) {
        return new ResponseEntity<>(service.connectionService(qrReadDto), HttpStatus.OK);
    }
}
