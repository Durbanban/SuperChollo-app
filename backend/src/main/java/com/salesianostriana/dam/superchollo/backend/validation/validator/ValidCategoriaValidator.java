package com.salesianostriana.dam.superchollo.backend.validation.validator;


import com.salesianostriana.dam.superchollo.backend.validation.annotation.ValidCategoria;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class ValidCategoriaValidator implements ConstraintValidator<ValidCategoria, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
