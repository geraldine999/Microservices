package com.javaneta.sale.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -666460693651813343L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
