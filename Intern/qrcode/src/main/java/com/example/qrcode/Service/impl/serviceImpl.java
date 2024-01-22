package com.example.qrcode.Service.impl;

import com.example.qrcode.Dto.qrReadDto;
import com.example.qrcode.Model.Device;
import com.example.qrcode.Model.UserDetails;
import com.example.qrcode.Repository.DeviceRepository;
import com.example.qrcode.Repository.Service.UserDetailsRepoService;
import com.example.qrcode.Repository.UserDetailsRepository;
import com.example.qrcode.Service.service;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

@Service
public class serviceImpl implements service {
    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsRepoService userDetailsRepoService;
    private final DeviceRepository deviceRepository;
    public serviceImpl(UserDetailsRepository userDetailsRepository,DeviceRepository deviceRepository,UserDetailsRepoService userDetailsRepoService){
        this.userDetailsRepository=userDetailsRepository;
        this.deviceRepository=deviceRepository;
        this.userDetailsRepoService=userDetailsRepoService;
    }
    @Override
    public byte[] generateQrService(String text) {
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        byte[] qr;
        try {
            BitMatrix bitMatrix=qrCodeWriter.encode(text, BarcodeFormat.QR_CODE,250,250);
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"PNG",out);
            MatrixToImageWriter.writeToPath(bitMatrix,"PNG", FileSystems.getDefault().getPath("./src/main/resources/qrcodes/qr.png"));
            qr=out.toByteArray();
//            bufferedImage= MatrixToImageWriter.toBufferedImage(bitMatrix);
//            System.out.println(bitMatrix);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }

        return qr;
    }

    @Override
    public void saveUserService(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    @Override
    public UserDetails connectionService(qrReadDto qrReadDto) {
        String deviceId=qrReadDto.getDeviceId();
        UserDetails userDetail=userDetailsRepoService.findByMobileNumber(qrReadDto.getMobileNumber());
            Device device=new Device();
            device.setDeviceId(deviceId);
            device.setUser(userDetail);
            device.setStatus("active");
            deviceRepository.save(device);
        return userDetail;
    }
}
