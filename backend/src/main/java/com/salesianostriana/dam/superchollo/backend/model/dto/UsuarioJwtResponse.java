package com.salesianostriana.dam.superchollo.backend.model.dto;

import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class UsuarioJwtResponse extends UsuarioDtoResponse{

    private String token;

    public UsuarioJwtResponse(UsuarioDtoResponse usuarioDtoResponse) {
        id = usuarioDtoResponse.getId();
        username = usuarioDtoResponse.getUsername();
        fullName = usuarioDtoResponse.getFullName();
        avatar = usuarioDtoResponse.getAvatar();
        fechaCreado = usuarioDtoResponse.getFechaCreado();
    }

    public static UsuarioJwtResponse of(Usuario usuario, String token) {
        UsuarioJwtResponse resultado = new UsuarioJwtResponse(UsuarioDtoResponse.of(usuario));
        resultado.setToken(token);
        return resultado;
    }
}
