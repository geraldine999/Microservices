package com.globallogic.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SoldProductDTO {

    @NotBlank(message = "productId no puede estar vacío")
    private String productId;
    @NotBlank(message = "description no puede estar vacío")
    private String description;
    @NotNull(message = "quantity no puede estar vacío")
    private int quantity;
    @NotNull(message = "unitPrice no puede estar vacío")
    private double unitPrice;
}
