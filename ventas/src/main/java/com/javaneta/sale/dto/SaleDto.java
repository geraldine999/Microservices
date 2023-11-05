package com.javaneta.sale.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaneta.sale.entity.SaleEntity.TypeMethodPayment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {

    private Long id;

    private TypeMethodPayment typeMethodPayment;

    private String idCard;

    private String idCustomer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateSale;

    private String currency;

    private double totalAmount;

    private List<ProductDto> products;
}
