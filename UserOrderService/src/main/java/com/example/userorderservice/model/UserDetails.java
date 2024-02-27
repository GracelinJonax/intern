package com.example.userorderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
public class UserDetails {
    @Id
    @UuidGenerator
    private String userId;
    private String name;
    private String email;
    private String password;
}
