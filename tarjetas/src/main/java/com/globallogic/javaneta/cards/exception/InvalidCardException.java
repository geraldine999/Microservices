package com.globallogic.javaneta.cards.exception;

public class InvalidCardException extends RuntimeException {

    private static final long serialVersionUID = 6900808536862651969L;
    private static String message;

    public InvalidCardException() {
        super();
        InvalidCardException.message = "La tarjeta es invalida.";
    }

}
