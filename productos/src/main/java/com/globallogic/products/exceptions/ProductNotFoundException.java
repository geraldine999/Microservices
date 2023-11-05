package com.globallogic.products.exceptions;

public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2314194636363692199L;

    public ProductNotFoundException(String message) {
        super(message);
    }

}
