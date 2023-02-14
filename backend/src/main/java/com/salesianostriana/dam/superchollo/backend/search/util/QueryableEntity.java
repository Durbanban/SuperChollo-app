package com.salesianostriana.dam.superchollo.backend.search.util;


import java.lang.reflect.Field;
import java.util.Arrays;

public interface QueryableEntity {

    static boolean checkQueryParam(Class c, String campo) {

        return Arrays.stream(c.getDeclaredFields())
                .map(Field::getName)
                .anyMatch(name -> name.equalsIgnoreCase(campo));
    }
}
