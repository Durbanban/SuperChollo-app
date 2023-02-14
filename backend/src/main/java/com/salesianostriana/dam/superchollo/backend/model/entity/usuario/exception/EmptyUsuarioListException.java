package com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyUsuarioListException extends EntityNotFoundException {

    public EmptyUsuarioListException() {
        super("No se han encontrado usuarios");
    }
}
