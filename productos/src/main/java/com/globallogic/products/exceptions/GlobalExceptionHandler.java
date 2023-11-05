package com.globallogic.products.exceptions;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.globallogic.products.dtos.ErrorDto;
import com.globallogic.products.dtos.ExceptionResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception exception) {
        return this.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception,
                "Ocurrio un error. Por favor intente mas tarde.");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception,
            WebRequest request) {
        return this.getResponseEntity(HttpStatus.NOT_FOUND.value(), exception);
    }

    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<Object> handleNotEnoughStockException(NotEnoughStockException exception, WebRequest request) {
        return this.getResponseEntity(HttpStatus.BAD_REQUEST.value(), exception);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorDto> errors = new ArrayList<>();
        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            var error = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.value()).details(objectError.getDefaultMessage())
                    .timestamp(Timestamp.from(Instant.now())).build();
            errors.add(error);
        }

        return new ResponseEntity<>(new ExceptionResponseDto(errors), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getResponseEntity(int httpStatus, Exception exception, String message) {
        var error = ErrorDto.builder().code(httpStatus).details(message == null ? exception.getMessage() : message)
                .timestamp(Timestamp.from(Instant.now())).build();

        return new ResponseEntity<>(new ExceptionResponseDto(List.of(error)), HttpStatus.valueOf(error.getCode()));
    }

    private ResponseEntity<Object> getResponseEntity(int httpStatus, Exception exception) {
        return this.getResponseEntity(httpStatus, exception, null);
    }

}
