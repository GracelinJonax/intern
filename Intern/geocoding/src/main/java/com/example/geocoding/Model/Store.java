package com.example.geocoding.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double longitude;
    private double latitude;
    @ManyToOne
    private Company company;
}
