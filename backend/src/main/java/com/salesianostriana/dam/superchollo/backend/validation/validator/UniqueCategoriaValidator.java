package com.salesianostriana.dam.superchollo.backend.validation.validator;


import com.salesianostriana.dam.superchollo.backend.service.CategoriaService;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueCategoria;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class UniqueCategoriaValidator implements ConstraintValidator<UniqueCategoria, String> {

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && !categoriaService.categoriaExists(value);
    }
}
