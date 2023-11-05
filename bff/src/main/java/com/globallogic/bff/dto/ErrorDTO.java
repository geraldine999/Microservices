package com.globallogic.bff.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = 5515767099999793246L;
    private static final String GENERIC_ERROR = "Error interno del servidor, intente más tarde.";
    private static final int GENERIC_STATUS = 500;

    private int status;
    private String description;

    public static ErrorDTO createGenericError() {
        return ErrorDTO.builder().status(GENERIC_STATUS).description(GENERIC_ERROR).build();
    }
}