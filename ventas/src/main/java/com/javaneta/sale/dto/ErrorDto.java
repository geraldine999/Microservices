package com.javaneta.sale.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 3552163267179637703L;
    private static final String GENERIC_ERROR = "Error interno del servidor. Intente de nuevo más tarde.";
    private static final int GENERIC_CODE = 500;
    private static final String NOSALE_ERROR = "No se pudo encontrar la venta especificada.";
    private static final int NOSALE_CODE = 500;

    private Date timestamp;
    private int code;
    private String details;

    public static ErrorDto createGenericError() {
        return ErrorDto.builder().code(GENERIC_CODE).details(GENERIC_ERROR).timestamp(new Date()).build();
    }
    
    public static ErrorDto createNoIdSaleError() {
        return ErrorDto.builder().code(NOSALE_CODE).details(NOSALE_ERROR).timestamp(new Date()).build();
    }
}
