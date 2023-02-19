package com.salesianostriana.dam.superchollo.backend.validation.annotation;

import com.salesianostriana.dam.superchollo.backend.validation.validator.UniqueAddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueAddressValidator.class)
@Documented
public @interface UniqueAddress {

    String message() default "La dirección del supermercado no debe repetirse. El supermercado ya existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
