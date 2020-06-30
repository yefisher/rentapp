package com.efisher.flatrent.error;

public class InfoUpdateException extends RuntimeException {
    public InfoUpdateException() {
        super();
    }

    public InfoUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfoUpdateException(String message) {
        super(message);
    }

    public InfoUpdateException(Throwable cause) {
        super(cause);
    }
}
