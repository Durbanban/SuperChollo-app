package com.salesianostriana.dam.superchollo.backend.validation.validator;


import com.salesianostriana.dam.superchollo.backend.service.usuario.UsuarioService;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueUsername;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {


    @Autowired
    UsuarioService usuarioService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && !usuarioService.usuarioExists(value);
    }
}
