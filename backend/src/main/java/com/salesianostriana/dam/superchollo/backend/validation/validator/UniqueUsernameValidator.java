package com.salesianostriana.dam.superchollo.backend.validation.validator;


import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueUsername;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //Implementar cuando esté el servicio del usuario
        return false;
    }
}
