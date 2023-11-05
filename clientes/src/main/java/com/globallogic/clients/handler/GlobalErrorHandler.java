package com.globallogic.clients.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.globallogic.clients.dtos.ErrorDTO;
import com.globallogic.clients.exceptions.NoClientException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @Value("${print-stacktrace}")
    private boolean logStackMessage;

    @ExceptionHandler(value = { NoClientException.class })
    protected ResponseEntity<ErrorDTO> handleNoClientException(Exception ex, WebRequest request) {
        var noClientError = ErrorDTO.createNoClientError();
        if (log.isInfoEnabled()) {
            log.info(noClientError.toString());
        }
        return new ResponseEntity<>(noClientError, HttpStatus.valueOf(noClientError.getCode()));
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ErrorDTO> handleEmptyValueException(Exception ex, WebRequest request) {
        var emptyValueError = ErrorDTO.createEmptyValueError();
        if (log.isInfoEnabled()) {
            log.info(emptyValueError.toString());
        }
        return new ResponseEntity<>(emptyValueError, HttpStatus.valueOf(emptyValueError.getCode()));
    }

}
