package com.globallogic.bff.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.globallogic.bff.dto.ErrorDTO;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        var error = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST.value()).description(errors.toString()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        var genericError = ErrorDTO.createGenericError();

        log.info(ex.getMessage() + " - " + Arrays.toString(ex.getStackTrace()));
        return new ResponseEntity<>(genericError, HttpStatus.valueOf(genericError.getStatus()));
    }

    @ExceptionHandler(value = {Exception400.class })
    protected ResponseEntity<Object> handleBadRequestException(Exception exception) {
        var genericError = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST.value()).description(exception.getMessage())
                .build();

        log.info(exception.getMessage() + " - " + Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(genericError, HttpStatus.valueOf(genericError.getStatus()));
    }

    @ExceptionHandler(value = {Exception404.class })
    protected ResponseEntity<Object> handleNotFoundException(Exception exception) {
        var genericError = ErrorDTO.builder().status(HttpStatus.NOT_FOUND.value()).description(exception.getMessage())
                .build();

        log.info(exception.getMessage() + " - " + Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(genericError, HttpStatus.valueOf(genericError.getStatus()));
    }

}
