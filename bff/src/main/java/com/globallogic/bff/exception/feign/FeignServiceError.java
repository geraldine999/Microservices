package com.globallogic.bff.exception.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FeignServiceError {

    private Date timestamp;
    private int code;
    private String details;
}
