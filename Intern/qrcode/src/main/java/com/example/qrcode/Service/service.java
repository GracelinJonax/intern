package com.example.qrcode.Service;

import com.example.qrcode.Dto.qrReadDto;
import com.example.qrcode.Model.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface service {
    public byte[] generateQrService(String text);

    void saveUserService(UserDetails userDetails);

    UserDetails connectionService(qrReadDto qrReadDto);
}
