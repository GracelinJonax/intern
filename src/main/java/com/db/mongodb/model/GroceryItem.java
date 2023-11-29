package com.db.mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
public class GroceryItem implements Serializable {
    @Id
    private String id;
    private String name;
    private String category;
    private int quantity;
    @DBRef
    private Price price;
}
