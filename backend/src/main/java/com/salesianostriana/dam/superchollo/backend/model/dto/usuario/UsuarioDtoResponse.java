package com.salesianostriana.dam.superchollo.backend.model.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Data
@SuperBuilder
public class UsuarioDtoResponse {

    protected String id;

    @JsonView({
            View.SupermercadoView.DetailedSupermercadoView.class
    })
    protected String username;

    @JsonView({
            View.SupermercadoView.DetailedSupermercadoView.class
    })
    protected String avatar;

    @JsonView({
            View.SupermercadoView.DetailedSupermercadoView.class
    })
    protected String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime fechaCreado;

    protected String roles;

    public static UsuarioDtoResponse of(Usuario usuario) {
        return UsuarioDtoResponse
                .builder()
                .id(usuario.getId().toString())
                .username(usuario.getUsername())
                .avatar(usuario.getAvatar())
                .fullName(usuario.getFullName())
                .fechaCreado(usuario.getFechaCreado())
                .roles(usuario.getRoles().toString())
                .build();
    }
}
