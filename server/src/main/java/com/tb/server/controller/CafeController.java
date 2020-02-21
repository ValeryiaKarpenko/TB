package com.tb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.dto.CafeDto;
import com.tb.service.api.CafeService;
import com.tb.service.api.handler.RestVoidResponseHandler;

@RestController
@RequestMapping(value = "/cafes")
public class CafeController {

    @Autowired
    private CafeService cafeService;

    @Autowired
    private RestVoidResponseHandler voidResponseHandler;

    @PostMapping
    public ResponseEntity addCafe(@RequestBody CafeDto cafe) {
        return voidResponseHandler.handle(
                () -> cafeService.createCafe(cafe, SecurityContextHolder.getContext().getAuthentication().getName()));
    }
    
    @PostMapping("/waiter/{userId}/{cafeId}")
    public ResponseEntity addWaiter(@PathVariable("userId") Long userId, @PathVariable("cafeId") Long cafeId) {
        return voidResponseHandler.handle(
                () -> cafeService.addWaiter(userId, cafeId, SecurityContextHolder.getContext().getAuthentication().getName()));
    }

}
