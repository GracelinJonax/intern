package com.example.qrcode.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String deviceId;
    private String status;
    @ManyToOne
    UserDetails user;
}
