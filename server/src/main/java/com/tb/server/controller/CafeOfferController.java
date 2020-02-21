package com.tb.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.dto.CafeOfferDto;
import com.tb.service.api.CafeOfferService;
import com.tb.service.api.handler.RestVoidResponseHandler;

@RestController
@RequestMapping(value = "/services")
public class CafeOfferController {

    @Autowired
    private CafeOfferService cafeOfferService;

    @Autowired
    private RestVoidResponseHandler voidResponseHandler;

    @PostMapping
    public ResponseEntity addCafeOffer(@Valid @RequestBody CafeOfferDto cafeOffer) {
        return voidResponseHandler.handle(() -> cafeOfferService.createCafeOffer(cafeOffer,
                SecurityContextHolder.getContext().getAuthentication().getName()));
    }

}
