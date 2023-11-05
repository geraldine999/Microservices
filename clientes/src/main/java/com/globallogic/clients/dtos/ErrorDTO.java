package com.globallogic.clients.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = -586314829915884527L;

    private static final String NOCLIENT_ERROR = "No existe un cliente con el id o dni ingresado. Verifique e intente nuevamente.";
    private static final int NOCLIENT_CODE = 404;
    private static final String EMPTYVALUE_ERROR = "Número de id o dni vacio. Ingrese un valor.";
    private static final int EMPTYVALUE_CODE = 400;

    private int code;
    private Date timestamp;
    private String details;

    public static ErrorDTO createNoClientError() {
        return ErrorDTO.builder().code(NOCLIENT_CODE).details(NOCLIENT_ERROR).timestamp(new Date()).build();
    }

    public static ErrorDTO createEmptyValueError() {
        return ErrorDTO.builder().code(EMPTYVALUE_CODE).details(EMPTYVALUE_ERROR).timestamp(new Date()).build();
    }

}
