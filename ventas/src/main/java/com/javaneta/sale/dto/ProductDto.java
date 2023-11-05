package com.javaneta.sale.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.javaneta.sale.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.BeanUtils;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto { 

    private Long id;
    private String productId;

    @Valid
    @NotEmpty(message = "El campo DESCRIPTION no puede ser vacio")    
    private String description;
    @Valid
    @NotEmpty(message = "El campo QUANTITY no puede ser vacio")
    private int quantity;

    @Valid
    @NotEmpty(message = "El campo UNITPRICE no puede ser vacio")
    private double unitPrice;

    public static List<ProductEntity> productDtosToEntities(List<ProductDto> products) {
        List<ProductEntity> productsList = new ArrayList<>();
        for (ProductDto prodDto: products) {
            var productEntity = new ProductEntity();
            BeanUtils.copyProperties(prodDto, productEntity);
            productsList.add(productEntity);
        }
        return productsList;
    }
}
