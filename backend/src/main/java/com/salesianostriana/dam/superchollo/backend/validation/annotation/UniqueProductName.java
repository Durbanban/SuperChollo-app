package com.salesianostriana.dam.superchollo.backend.validation.annotation;


import com.salesianostriana.dam.superchollo.backend.validation.validator.UniqueCategoriaValidator;
import com.salesianostriana.dam.superchollo.backend.validation.validator.UniqueProductNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueProductNameValidator.class)
@Documented
public @interface UniqueProductName {

    String message() default "El nombre del producto no debe repetirse. El producto ya existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
