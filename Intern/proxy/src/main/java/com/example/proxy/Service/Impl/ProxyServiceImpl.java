package com.example.proxy.Service.Impl;

import com.example.proxy.Dto.orderBillDto;
import com.example.proxy.Feign.BillFeign;
import com.example.proxy.Service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProxyServiceImpl implements ProxyService {
    private final BillFeign billFeign;
    @Autowired
    EmailService emailService;

    public ProxyServiceImpl(BillFeign billFeign) {
        this.billFeign = billFeign;
    }

    @Override
    public String saveBillService(orderBillDto orderBillDto) {
        System.out.println(orderBillDto);
        String billId = billFeign.saveBill(orderBillDto);
        sendEmail(orderBillDto);
        return billId;
    }

    void sendEmail(orderBillDto bill) {
        String message = "<head>" + "</head>" + "<body>" + "<table border =1>" + "<tr>" + "<th>Product Name</th>" + "<th>Quantity</th>" + "<th>Gst</th>" + "<th>Price</th>";
        for (orderBillDto.Product product : bill.getProducts()) {
            message += "<tr>" + "<td>" + product.getName() + "</td>" + "<td>" + product.getQuantity() + "</td>" + "<td>" + product.getGst() + "</td>" + "<td>" + product.getPrice() + "</td>" + "</tr>";
        }
        message += "</table>" + "Total Price       " + bill.getTotalPrice() + "</body>" + "</html>";
        String email = bill.getUserEmail();
        emailService.sendEmail(email, "Your Order is Confirmed", message);
    }
}
