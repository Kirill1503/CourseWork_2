package com.example.coursework_2.exception;

public class ElementMissingException extends RuntimeException {
    public ElementMissingException() {
    }

    public ElementMissingException(String message) {
        super(message);
    }

    public ElementMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementMissingException(Throwable cause) {
        super(cause);
    }

    public ElementMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
