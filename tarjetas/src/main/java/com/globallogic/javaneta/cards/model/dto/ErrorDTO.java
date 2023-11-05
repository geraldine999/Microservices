package com.globallogic.javaneta.cards.model.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = 5232808213780377746L;
    private static final String GENERIC_ERROR = "Error interno del servidor. Intente de nuevo más tarde.";
    private static final int GENERIC_CODE = 500;
    private static final String NOCARD_ERROR = "No se pudo encontrar la tarjeta especificada.";
    private static final int NOCARD_CODE = 404;
    private static final String INVALIDCARD_ERROR = "Número de tarjeta invalido. Verifique e intente nuevamente.";
    private static final int INVALIDCARD_CODE = 400;

    private Date timestamp;
    private int code;
    private String details;

    public static ErrorDTO createGenericError() {
        return ErrorDTO.builder().code(GENERIC_CODE).details(GENERIC_ERROR).timestamp(new Date()).build();
    }

    public static ErrorDTO createNoCardError() {
        return ErrorDTO.builder().code(NOCARD_CODE).details(NOCARD_ERROR).timestamp(new Date()).build();
    }

    public static ErrorDTO createInvalidCardError() {
        return ErrorDTO.builder().code(INVALIDCARD_CODE).details(INVALIDCARD_ERROR).timestamp(new Date()).build();
    }
}
