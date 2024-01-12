package com.example.bookingservice.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//    private String rewardType;
    @ManyToOne
    private UserDetails userDetails;
//    private String rewardInfo;
    @ManyToOne
    RewardInformation information;
    private String status;
    private LocalDate validDate;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
}
