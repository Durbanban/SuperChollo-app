package com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class CategoriaNotFoundException extends EntityNotFoundException {

    public CategoriaNotFoundException() {
        super("La categoría no ha sido encontrada");
    }

    public CategoriaNotFoundException(UUID id) {
        super(String.format("La categoría con id %s no ha sido encontrada", id.toString()));
    }
}
