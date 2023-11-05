package com.javaneta.sale.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.javaneta.sale.dto.ErrorDto;
import com.javaneta.sale.exceptions.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ErrorDto> handlerNoSaleException(Exception ex, WebRequest reques) {
        var noSaleError = ErrorDto.createNoIdSaleError();
        log.info(noSaleError.toString());
        return new ResponseEntity<>(noSaleError, HttpStatus.valueOf(noSaleError.getCode()));
    }
}
