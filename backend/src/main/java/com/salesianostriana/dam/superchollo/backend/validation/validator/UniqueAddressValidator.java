package com.salesianostriana.dam.superchollo.backend.validation.validator;

import com.salesianostriana.dam.superchollo.backend.service.SupermercadoService;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueAddress;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class UniqueAddressValidator implements ConstraintValidator<UniqueAddress, String> {

    @Autowired
    SupermercadoService supermercadoService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && !supermercadoService.supermercadoExists(value);
    }
}
