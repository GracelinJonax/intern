package com.example.userorderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TenantDetails {
    @Id
    private String tenantId;
    private String email;
    private String password;
}
