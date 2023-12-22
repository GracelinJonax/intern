package com.example.bookingservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Journey {
    @Id
    @UuidGenerator
    private String id;
    private String boardingPoint;
    private String endPoint;
    private LocalTime startTime;
    private LocalTime endTime;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
}
