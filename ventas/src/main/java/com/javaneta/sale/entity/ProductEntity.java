package com.javaneta.sale.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = -7208586969837598013L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "productid", nullable = false, length = 200)
    private String productId;

    @Column(name = "description", nullable = false, length = 100)
    private String description;
    
    @Column(name = "quantity", nullable = false, length = 10)
    private int quantity;
    
    @Digits(integer = 10, fraction = 2, message = "El precio solo acepta dos decimales")
    @Column(name = "unitprice", nullable = false, length = 10)
    private double unitPrice;

}
