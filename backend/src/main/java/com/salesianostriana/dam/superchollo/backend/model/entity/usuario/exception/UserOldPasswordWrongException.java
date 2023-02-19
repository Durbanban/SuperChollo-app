package com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception;


public class UserOldPasswordWrongException extends RuntimeException {

    public UserOldPasswordWrongException() {
        super("La contraseña antigua no es correcta");
    }
}
