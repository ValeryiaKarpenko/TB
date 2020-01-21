package com.tb.service.api.handler;

import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

public interface RestResponseHandler extends ResponseHandler <Supplier<?>, ResponseEntity<?>> {


}
