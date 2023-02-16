package com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception;


import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class SupermercadoNotFoundException extends EntityNotFoundException {

    public SupermercadoNotFoundException() {
        super("El supermercado no ha sido encontrado");
    }

    public SupermercadoNotFoundException(UUID id) {
        super(String.format("El supermercado con id %s no ha sido encontrado", id.toString()));
    }
}
