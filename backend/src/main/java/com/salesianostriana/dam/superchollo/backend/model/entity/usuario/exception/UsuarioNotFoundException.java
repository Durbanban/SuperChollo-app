package com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception;


import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UsuarioNotFoundException extends EntityNotFoundException {

    public UsuarioNotFoundException() {
        super("No se ha encontrado al usuario");
    }

    public UsuarioNotFoundException(UUID id) {
        super(String.format("El usuario con id %s no se ha encontrado", id.toString()));
    }
}
