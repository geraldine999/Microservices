package com.globallogic.bff.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CardsDTO {

    @Valid
    @NotEmpty(message = "Debe ingresar un número de tarjeta.")
    private String number;

    private String clientId;

    private String company;
}
