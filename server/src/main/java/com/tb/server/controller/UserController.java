package com.tb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tb.dto.UserDto;
import com.tb.service.api.UserService;
import com.tb.service.api.handler.RestVoidResponseHandler;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private RestVoidResponseHandler voidResponseHandler;

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity register(@RequestBody UserDto user) {
        return voidResponseHandler.handle(() ->  userService.createUser(user));
    }
    
    @PutMapping("/image/{id}")
    public ResponseEntity updateUserImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        return voidResponseHandler.handle(() ->  userService.updateUserImage(id, file));
    }
}
