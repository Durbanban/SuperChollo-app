package com.salesianostriana.dam.superchollo.backend.validation.validator;


import com.salesianostriana.dam.superchollo.backend.service.ProductoService;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueProductName;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {

    @Autowired
    ProductoService productoService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return StringUtils.hasText(value) && !productoService.productoExists(value);
    }
}
