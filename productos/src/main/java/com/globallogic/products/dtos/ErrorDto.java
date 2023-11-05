package com.globallogic.products.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 6799097308672681642L;

    private String details;
    private int code;
    private Timestamp timestamp;

}
