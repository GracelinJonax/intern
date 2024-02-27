package com.example.userorderservice.model;

import com.example.userorderservice.util.ApplicationConstants;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
public class OrderedProductDetails {
    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @UuidGenerator
    private String orderedProductId;
    @ManyToOne
    @JoinColumn(name = ApplicationConstants.orderForeignKey)
    OrderDetails orderDetails;
    @OneToOne
    @JoinColumn(name = ApplicationConstants.productForeignKey)
    ProductDetails productDetails;

    private int quantity;
}
