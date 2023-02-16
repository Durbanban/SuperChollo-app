package com.salesianostriana.dam.superchollo.backend.validation.validator;


import com.salesianostriana.dam.superchollo.backend.service.SupermercadoService;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.ValidAddresses;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ValidAddressesValidator implements ConstraintValidator<ValidAddresses, String> {

    @Autowired
    SupermercadoService supermercadoService;



    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> lista = Arrays.stream(value.split(", ")).collect(Collectors.toList());
        boolean checker = lista.stream().allMatch(address -> supermercadoService.supermercadoExists(address));

        return StringUtils.hasText(value) && checker;
    }
}
