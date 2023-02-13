package com.salesianostriana.dam.superchollo.backend.model.dto;

import com.salesianostriana.dam.superchollo.backend.validation.annotation.PasswordsMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
@Builder
@PasswordsMatch(
        passwordField = "password",
        verifyPasswordField = "verifyPassword",
        message = "{usuarioDtoCreateRequest.passwordsNotMatch}"
)
public class UsuarioDtoCreateRequest {

    private String username;

    private String password;

    private String verifyPassword;

    private String avatar;

    private String fullName;
}
