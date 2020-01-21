package com.tb.service.impl.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tb.service.api.handler.RestVoidResponseHandler;
import com.tb.service.impl.exceptions.ResponseError;

@Service
public class RestVoidResponseHandlerImpl implements RestVoidResponseHandler {

    @Autowired
    private ExceptionHandlerService handler;

    @Override
    public ResponseEntity<?> handle(Runnable runnable) {
        HttpStatus httpStatus;
        try {
            runnable.run();
            httpStatus = HttpStatus.NO_CONTENT;
            return new ResponseEntity<>((Object)null, httpStatus);
        } catch (Exception e) {
            final ResponseError error = handler.handle(e);
            httpStatus = HttpStatus.valueOf(error.getCode().getValue());
            return new ResponseEntity<>(error, httpStatus);
        }
    }
}
