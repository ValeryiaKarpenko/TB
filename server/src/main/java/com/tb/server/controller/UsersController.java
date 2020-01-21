package com.tb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tb.dto.UserDto;
import com.tb.service.api.UserService;
import com.tb.service.api.handler.RestResponseHandler;
import com.tb.service.api.handler.RestVoidResponseHandler;


@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private RestVoidResponseHandler voidResponseHandler;

    @Autowired
    private UserService userService;

    @Autowired
    private RestResponseHandler responseHandler;

    @GetMapping("/all")
    public ResponseEntity getAllUsers(){
        return responseHandler.handle(() ->  userService.getAllUsers());
    }

    @GetMapping("/all-with-roles")
    public ResponseEntity getAllUsersWithRoles(){
        return responseHandler.handle(() ->  userService.getAllUsersWithRoles());
    }

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return responseHandler.handle(() -> userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto user) {
        return voidResponseHandler.handle(() ->  userService.createUser(user));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UserDto user) {
        return voidResponseHandler.handle(() ->  userService.updateUser(id, user));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return voidResponseHandler.handle(() -> userService.deleteUser(id));
    }

}
