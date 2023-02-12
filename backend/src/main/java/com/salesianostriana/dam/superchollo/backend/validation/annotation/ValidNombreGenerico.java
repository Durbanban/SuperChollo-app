package com.salesianostriana.dam.superchollo.backend.validation.annotation;

import com.salesianostriana.dam.superchollo.backend.validation.validator.ValidNombreGenericoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidNombreGenericoValidator.class)
@Documented
public @interface ValidNombreGenerico {

    String message() default "El nombre genérico debe estar entre los valores admitidos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
