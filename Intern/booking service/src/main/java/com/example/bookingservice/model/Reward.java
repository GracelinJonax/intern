package com.example.bookingservice.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private UserDetails userDetails;
    @ManyToOne
    Offers offers;
    private String linkId;
    private String status;
    private boolean scratch;
    private LocalDate validDate;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
}
