package com.salesianostriana.dam.superchollo.backend.validation.validator;


import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.CategoriaNombre;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.ValidCategoria;
import lombok.NoArgsConstructor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class ValidCategoriaValidator implements ConstraintValidator<ValidCategoria, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return StringUtils.hasText(value) && !CategoriaNombre.contains(value);
    }
}
