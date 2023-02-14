package com.salesianostriana.dam.superchollo.backend.security.error;


public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String msg) {
        super(msg);
    }
}
