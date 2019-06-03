package com.training.validator.validators;

import com.training.validator.beans.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","name.required","Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.required","Password is required");

        if (errors.hasErrors()){
            return;
        }

        if(user.getName().length() < 8 ){
            errors.rejectValue("name","Name is too short");
        }

        if(user.getPassword().length() < 8){
            errors.rejectValue("password","Password is too short");
        }

        if(!user.getPassword().matches("(.)*(\\d)+(.)*")){
            errors.rejectValue("password", "Password should contain digits");
        }

        if(!user.getPassword().matches("(.)*([a-z])+(.)*")){
            errors.rejectValue("password", "Password should contain small case letters");
        }

        if(!user.getPassword().matches("(.)*([A-Z])+(.)*")){
            errors.rejectValue("password", "Password should contain upper case letters");
        }
    }
}
