package com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception;


import javax.persistence.EntityNotFoundException;

public class EmptyProductoListException extends EntityNotFoundException {

    public EmptyProductoListException() {
        super("No se han encontrado productos");
    }
}
