package com.example.coursework_2.exception;

public class CollectionAreEmptyException extends RuntimeException {
    public CollectionAreEmptyException() {
    }

    public CollectionAreEmptyException(String message) {
        super(message);
    }

    public CollectionAreEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectionAreEmptyException(Throwable cause) {
        super(cause);
    }

    public CollectionAreEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
