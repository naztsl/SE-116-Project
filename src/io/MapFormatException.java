package io;

public class MapFormatException extends RuntimeException {

    public MapFormatException(String message) {
        super(message);
    }

    public MapFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}