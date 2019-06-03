package com.training.validator.controller;

import com.training.validator.beans.StatusCreate;
import com.training.validator.beans.User;
import com.training.validator.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserValidator userValidator;

    @PostMapping(value = "/create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<StatusCreate> create(@RequestBody User user, BindingResult bindingResult){

        userValidator.validate(user, bindingResult);

        System.out.println("has errors: "+ bindingResult.hasErrors());
        System.out.println("Errors: " + bindingResult.getAllErrors());

        StatusCreate response = new StatusCreate();

        if (bindingResult.hasErrors()){
            response.setCreated(false);
            response.setErrors(
                    bindingResult.getAllErrors()
                            .stream().map((error)->(error.getCode())).collect(Collectors.toList())
                            .toString());
        }
        else {
            response.setCreated(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}