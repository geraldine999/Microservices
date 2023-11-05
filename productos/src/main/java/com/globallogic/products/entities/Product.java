package com.globallogic.products.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = -9191278870557390105L;

    @Id
    private String id;
    private String description;
    private int stock;
    private double price;

}
