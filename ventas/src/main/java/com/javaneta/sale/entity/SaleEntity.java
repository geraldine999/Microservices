package com.javaneta.sale.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleEntity implements Serializable {

    private static final long serialVersionUID = -5861359380028711913L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    @Column (name = "idcustomer", nullable = false, length = 10)
    private String idCustomer;

    @Enumerated(EnumType.STRING)
    @Column (name = "typeMethodPayment", nullable = false)
    private TypeMethodPayment typeMethodPayment;
    
    @Column(name = "idcard")
    private String idCard;
  
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column (name = "datesale")
    private Date dateSale; 

    @Column (name = "currency", nullable = false, length = 20)
    private String currency;
  
    @Column (name = "totalamount", nullable = false, length = 20)
    private double totalAmount;
    
    @OneToMany(targetEntity = ProductEntity.class ,cascade = CascadeType.ALL)
    private List<ProductEntity> products;
    
    public enum TypeMethodPayment {
        CASH, CARD
    }
}
