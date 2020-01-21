package com.tb.service.impl.handler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.tb.enums.ErrorStatus;
import com.tb.service.impl.exceptions.*;

@Slf4j
@Service
public class ExceptionHandlerService {

    public ResponseError handle(Exception e) {
        ErrorStatus status = ErrorStatus.UNEXPECTED_ERROR;
        if (e instanceof PermissionDeniedException) {
            status = ErrorStatus.UNAUTHORIZED_REQUEST;
        } else if (e instanceof DataNotFoundException) {
            status = ErrorStatus.NOT_FOUND;
        } else if (e instanceof BadDataException) {
            status = ErrorStatus.BAD_DATA;
        } else if (e instanceof DataDuplicationException) {
            status = ErrorStatus.DATA_DUPLICATION;
        }
        log.error("--ERROR--", e);
        return new ResponseError(e.getMessage() != null ? e.getMessage() : e.getClass().getName(), status, e);
    }

}
