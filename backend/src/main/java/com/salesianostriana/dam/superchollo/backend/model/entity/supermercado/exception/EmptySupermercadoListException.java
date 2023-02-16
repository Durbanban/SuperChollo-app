package com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception;


import javax.persistence.EntityNotFoundException;

public class EmptySupermercadoListException extends EntityNotFoundException {

    public EmptySupermercadoListException() {
        super("No se han encontrado supermercasdos");
    }
}
