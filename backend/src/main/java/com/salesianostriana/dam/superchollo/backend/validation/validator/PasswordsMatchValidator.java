package com.salesianostriana.dam.superchollo.backend.validation.validator;

import com.salesianostriana.dam.superchollo.backend.validation.annotation.PasswordsMatch;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {


    String passwordField;
    String verifyPasswordField;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        this.passwordField = constraintAnnotation.passwordField();
        this.verifyPasswordField = constraintAnnotation.verifyPasswordField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String password = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(this.passwordField);
        String verifyPassword = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(this.verifyPasswordField);

        return StringUtils.hasText(password) && password.contentEquals(verifyPassword);
    }
}
