package com.salesianostriana.dam.superchollo.backend.validation.annotation;

import com.salesianostriana.dam.superchollo.backend.validation.validator.ValidCategoriaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCategoriaValidator.class)
@Documented
public @interface ValidCategoria {

    String message() default "La categoría debe estar entre los valores admitidos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
