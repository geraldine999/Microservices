package com.globallogic.javaneta.cards.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.globallogic.javaneta.cards.exception.InvalidCardException;
import com.globallogic.javaneta.cards.exception.NoCardException;
import com.globallogic.javaneta.cards.model.dto.ErrorDTO;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(value = { NoCardException.class })
    protected ResponseEntity<ErrorDTO> handleNoCardException(Exception ex, WebRequest request) {
        var noCardError = ErrorDTO.createNoCardError();
        if (log.isInfoEnabled()) {
            log.info(noCardError.toString());
        }
        return new ResponseEntity<ErrorDTO>(noCardError, HttpStatus.valueOf(noCardError.getCode()));
    }

    @ExceptionHandler(value = { InvalidCardException.class })
    protected ResponseEntity<ErrorDTO> handleInvalidCardException(Exception ex, WebRequest request) {
        var invalidCardError = ErrorDTO.createInvalidCardError();
        if (log.isInfoEnabled()) {
            log.info(invalidCardError.toString());
        }
        return new ResponseEntity<ErrorDTO>(invalidCardError, HttpStatus.valueOf(invalidCardError.getCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        var error = ErrorDTO.builder().timestamp(new Date()).code(HttpStatus.BAD_REQUEST.value())
                .details(errors.toString()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ErrorDTO> handleException(Exception ex, WebRequest request) {
        var genericError = ErrorDTO.createGenericError();
        if (log.isInfoEnabled()) {
            log.info(genericError.toString());
        }
        return new ResponseEntity<ErrorDTO>(genericError, HttpStatus.valueOf(genericError.getCode()));
    }

}
