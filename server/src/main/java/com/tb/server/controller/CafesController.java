package com.tb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.dto.CafeDto;
import com.tb.service.api.CafeService;
import com.tb.service.api.handler.RestVoidResponseHandler;

@RestController
@RequestMapping(value = "/cafes")
public class CafesController {
    
    @Autowired
    private CafeService cafeService;
    
    @Autowired
    private RestVoidResponseHandler voidResponseHandler;
    
    @PostMapping
    public ResponseEntity register(@RequestBody CafeDto cafe, Authentication authentication) {
        return voidResponseHandler.handle(() ->  cafeService.createCafe(cafe, authentication.getName()));
    }

}
