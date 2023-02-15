package com.salesianostriana.dam.superchollo.backend.validation.annotation;

import com.salesianostriana.dam.superchollo.backend.validation.validator.UniqueCategoriaValidator;
import com.salesianostriana.dam.superchollo.backend.validation.validator.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCategoriaValidator.class)
@Documented
public @interface UniqueCategoria {

    String message() default "El nombre de la categoría no debe repetirse. La categoría ya existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
