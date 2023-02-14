package com.salesianostriana.dam.superchollo.backend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
}