package com.training.validator.controller;

import com.training.validator.beans.StatusCreate;
import com.training.validator.beans.User;
import com.training.validator.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserValidator userValidator;

    @PostMapping(value = "/create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<StatusCreate> create(@RequestBody User user){

        StatusCreate response = new StatusCreate();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}