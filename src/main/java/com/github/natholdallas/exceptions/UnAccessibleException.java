package com.github.natholdallas.exceptions;

public class UnAccessibleException extends RuntimeException {

    public static final String MESSAGE = "The class can not be proxied, cause by unaccessible";

    public UnAccessibleException() {
        super(MESSAGE);
    }

    public UnAccessibleException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
