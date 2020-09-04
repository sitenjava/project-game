package com.game.core.validator.impl;

import com.game.core.validator.PasswordMatches;
import com.game.data.dto.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        UserRegistrationDto user = (UserRegistrationDto) o;
        boolean isValid = user.getPassword().equals(user.getRetypePassword());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("retypePassword").addConstraintViolation();
        }
        return isValid;
    }
}
