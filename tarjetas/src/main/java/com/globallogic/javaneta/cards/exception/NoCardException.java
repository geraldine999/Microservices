package com.globallogic.javaneta.cards.exception;

public class NoCardException extends RuntimeException {

    private static final long serialVersionUID = 6900808536862651969L;
    private static String message;

    public NoCardException() {
        super();
        NoCardException.message = "No se pudo obtener la tarjeta.";
    }
}
