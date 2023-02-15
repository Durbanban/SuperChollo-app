package com.salesianostriana.dam.superchollo.backend.security.jwt.refresh;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class RefreshTokenNotFoundException extends EntityNotFoundException {

    public RefreshTokenNotFoundException() {
        super("El token de refresco no ha sido encontrado");
    }

    public RefreshTokenNotFoundException(UUID id) {
        super(String.format("El token de refresco del usuario con id %d no ha sido encontrado", id));
    }
}
