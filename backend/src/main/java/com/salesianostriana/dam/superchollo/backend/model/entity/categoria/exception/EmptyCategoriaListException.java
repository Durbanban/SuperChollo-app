package com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyCategoriaListException extends EntityNotFoundException {

    public EmptyCategoriaListException() {
        super("No se han encontrado categorías");
    }
}
