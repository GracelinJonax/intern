package com.example.seatingarrangement.model;

import jakarta.persistence.GeneratedValue;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class DefaultLayout {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(strategy = "syatem-uuid",name = "uuid")
    private String id;
    private int[][] defaultLayout;
    private String[][] layout;
    private int totalSpace;
    @CreatedDate
    private Date createdDated;
    @LastModifiedDate
    private Date lastModifiedDate;
}
