package com.tb.service.impl.exceptions;

public class FileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6174775313290352084L;

    public FileNotFoundException(String s) {
        super("File not found" + s);
    }
}
