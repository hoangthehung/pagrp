package hung.hoang.pagrp.exception;


public class FileCSVNotFoundException extends RuntimeException {
    public FileCSVNotFoundException() {
        super();
    }

    public FileCSVNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FileCSVNotFoundException(final String message) {
        super(message);
    }

    public FileCSVNotFoundException(final Throwable cause) {
        super(cause);
    }

}
