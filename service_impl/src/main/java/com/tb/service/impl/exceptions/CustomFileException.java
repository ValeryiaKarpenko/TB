package com.tb.service.impl.exceptions;

public class CustomFileException extends RuntimeException {

    private static final long serialVersionUID = -5794546767711888118L;

    public CustomFileException(String s) {
        super(s);
    }
}
