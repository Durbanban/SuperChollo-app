package com.salesianostriana.dam.superchollo.backend.validation.annotation;

import com.salesianostriana.dam.superchollo.backend.validation.validator.ValidAddressesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAddressesValidator.class)
@Documented
public @interface ValidAddresses {

    String message() default "La dirección del supermercado debe estar registrada. Inténtelo de nuevo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
