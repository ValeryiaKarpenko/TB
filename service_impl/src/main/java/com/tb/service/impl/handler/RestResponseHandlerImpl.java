package com.tb.service.impl.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tb.service.api.handler.RestResponseHandler;
import com.tb.service.impl.exceptions.ResponseError;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class RestResponseHandlerImpl implements RestResponseHandler {

    @Autowired
    private ExceptionHandlerService handler;

    @SuppressWarnings("all")
    @Override
    public ResponseEntity<?> handle(Supplier<?> s) {
        HttpStatus httpStatus;
        try {
            Object val = Optional.ofNullable(s.get()).filter(o -> !(o instanceof Void)).orElse(null);
            if (val != null) {
                httpStatus = HttpStatus.OK;
            } else {
                httpStatus = HttpStatus.NO_CONTENT;
            }
            return new ResponseEntity<>(val, httpStatus);
        } catch (Exception e) {
            final ResponseError error = handler.handle(e);
            httpStatus = HttpStatus.valueOf(error.getCode().getValue());
            return new ResponseEntity<>(error, httpStatus);
        }
    }
}
