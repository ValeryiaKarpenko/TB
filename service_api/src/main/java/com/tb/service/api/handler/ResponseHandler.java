package com.tb.service.api.handler;

public interface ResponseHandler <I, O> {

    O handle(I in);

}
