package com.example.bookingservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Offers {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private String information;
    private int validity;
}
