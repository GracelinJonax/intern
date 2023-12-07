package com.example.userorderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class OrderedProductDetails {
    @ManyToOne
    @JoinColumn(name = "orderId")
    OrderDetails orderDetails;
    @OneToOne
    @JoinColumn(name = "productId")
    ProductDetails productDetails;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String orderedProductId;
    private int quantity;
}
