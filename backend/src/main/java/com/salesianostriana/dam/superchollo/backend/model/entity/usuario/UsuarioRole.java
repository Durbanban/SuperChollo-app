package com.salesianostriana.dam.superchollo.backend.model.entity.usuario;

public enum UsuarioRole {
    ADMIN , USER;

    // Para el momento que se intente dar un nombre de rol que no está contemplado en la enum en la request
    public static boolean contains(String cadena) {

        try {
            UsuarioRole.valueOf(cadena);
            return true;
        }catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
