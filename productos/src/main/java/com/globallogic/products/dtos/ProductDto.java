package com.globallogic.products.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 7327026715164893273L;

    private String id;
    private String description;
    private int stock;
    private double price;

}
