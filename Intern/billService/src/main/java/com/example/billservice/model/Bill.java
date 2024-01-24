package com.example.billservice.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Bill {
    List<OrderedProduct> products;
    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @UuidGenerator
    private String id;
    private String orderId;
    private String userId;
    private Double totalPrice;
}
