package com.globallogic.products.exceptions;

public class NotEnoughStockException extends RuntimeException {
    private static final long serialVersionUID = 4973358500822358004L;
    
    public NotEnoughStockException(String productID) {
        super(String.format("No hay suficiente stock del producto %s", productID));
    }
}
