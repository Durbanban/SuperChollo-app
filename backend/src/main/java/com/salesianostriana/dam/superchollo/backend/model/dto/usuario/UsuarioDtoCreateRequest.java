package com.salesianostriana.dam.superchollo.backend.model.dto.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDtoCreateRequest {

    private String username;

    private String password;

    private String verifyPassword;

    private String fullName;
}
