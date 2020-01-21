package com.tb.service.impl.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tb.enums.ErrorStatus;

import lombok.Getter;

import java.util.Arrays;

public class ResponseError {

    @JsonIgnore
    @Getter
    private String trace;
    @Getter
    private String message;
    @JsonIgnore
    @Getter
    private Exception exception;
    @Getter
    private ErrorStatus code;

    public ResponseError(String message, ErrorStatus code, Exception exception) {
        this.message = message;
        this.exception = exception;
        if (exception != null) {
            this.trace = Arrays.toString(exception.getStackTrace());
        }
        this.code = code;
    }
}
