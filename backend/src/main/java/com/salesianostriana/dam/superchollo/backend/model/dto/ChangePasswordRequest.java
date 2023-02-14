package com.salesianostriana.dam.superchollo.backend.model.dto;

import com.salesianostriana.dam.superchollo.backend.validation.annotation.PasswordsMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@PasswordsMatch(
        passwordField = "newPassword",
        verifyPasswordField = "verifyPassword",
        message = "{usuarioDtoCreateRequest.passwordsNotMatch}"
)
public class ChangePasswordRequest {

    private String oldPassword;
    private String newPassword;
    private String verifyPassword;
}
