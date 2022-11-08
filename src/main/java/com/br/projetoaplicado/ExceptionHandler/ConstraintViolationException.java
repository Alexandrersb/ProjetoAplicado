package com.br.projetoaplicado.ExceptionHandler;

public class ConstraintViolationException extends RuntimeException{

    public ConstraintViolationException(String message) {
        super(message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
