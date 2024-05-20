package com.example.bookingservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Links {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String link;
    private String publish;
}
