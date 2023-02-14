package com.salesianostriana.dam.superchollo.backend.security.jwt.refresh;


import com.salesianostriana.dam.superchollo.backend.security.error.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {

    public RefreshTokenException(String msg) {
        super(msg);
    }

}
