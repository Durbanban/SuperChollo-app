package com.salesianostriana.dam.superchollo.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
@Builder
public class UsuarioDtoCreateRequest {

    private String username;

    private String password;

    private String verifyPassword;

    private String avatar;

    private String fullName;
}
