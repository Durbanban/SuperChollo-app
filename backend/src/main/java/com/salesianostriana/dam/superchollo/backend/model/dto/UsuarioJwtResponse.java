package com.salesianostriana.dam.superchollo.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioJwtResponse extends UsuarioDtoResponse{

    private String token;
    private String refreshToken;

    public UsuarioJwtResponse(UsuarioDtoResponse usuarioDtoResponse) {
        id = usuarioDtoResponse.getId();
        username = usuarioDtoResponse.getUsername();
        fullName = usuarioDtoResponse.getFullName();
        avatar = usuarioDtoResponse.getAvatar();
        fechaCreado = usuarioDtoResponse.getFechaCreado();
    }

    public static UsuarioJwtResponse of(Usuario usuario, String token, String refreshToken) {
        UsuarioJwtResponse resultado = new UsuarioJwtResponse(UsuarioDtoResponse.of(usuario));
        resultado.setToken(token);
        resultado.setRefreshToken(refreshToken);
        return resultado;
    }
}
