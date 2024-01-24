package com.example.proxy.Service.Impl;

import com.example.proxy.Dto.orderBillDto;
import com.example.proxy.Feign.BillFeign;
import com.example.proxy.Service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;

@Service
public class ProxyServiceImpl implements ProxyService {
    private final BillFeign billFeign;
    @Autowired
    EmailService emailService;

    public ProxyServiceImpl(BillFeign billFeign) {
        this.billFeign = billFeign;
    }

    @Override
    public void saveBillService(orderBillDto orderBillDto) {
        billFeign.saveBill(orderBillDto);
        sendEmail(orderBillDto);
    }
    @Autowired
    TemplateEngine templateEngine;
    @Value("${productsHtml}")
    String productsHtml;
    @Value("${total}")
    String total;
    @Value("${htmlPageName}")
    String html;
    @Value("${emailSubject}")
    String subject;
    void sendEmail(orderBillDto bill) {
        Context context=new Context();

        context.setVariable(productsHtml,bill.getProducts());
        context.setVariable(total,bill.getTotalPrice());
        String message=templateEngine.process(html,context);
        String email = bill.getUserEmail();
        emailService.sendEmail(email, subject, message);
    }
}
