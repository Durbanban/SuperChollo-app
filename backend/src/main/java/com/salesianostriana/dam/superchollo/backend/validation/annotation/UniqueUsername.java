package com.salesianostriana.dam.superchollo.backend.validation.annotation;

import com.salesianostriana.dam.superchollo.backend.validation.validator.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Documented
public @interface UniqueUsername {

    String message() default "El nombre de usuario no debe repetirse. El usuario ya existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
