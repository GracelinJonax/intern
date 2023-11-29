package com.example.userorderservice.feign;

import lombok.Data;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
//@Data
public class EmailService {
    private final JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }
    public void sendEmail(String toEmail, String subject, String message){
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(toEmail);
        mail.setSubject(subject);
        mail.setText(subject);
    }
}
