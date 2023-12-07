package com.example.proxy.Service.Impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String toEmail, String subject, String message) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mail = new MimeMessageHelper(mimeMailMessage, false, "utf-8");
            mail.setTo(toEmail);
            mail.setSubject(subject);
            mimeMailMessage.setContent(message, "text/html");
            javaMailSender.send(mimeMailMessage);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
