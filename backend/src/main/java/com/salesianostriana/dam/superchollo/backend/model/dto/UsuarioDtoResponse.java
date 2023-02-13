package com.salesianostriana.dam.superchollo.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Data
@SuperBuilder
public class UsuarioDtoResponse {

    protected String id;

    protected String username;

    protected String avatar;

    protected String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime fechaCreado;

    public static UsuarioDtoResponse of(Usuario usuario) {
        return UsuarioDtoResponse
                .builder()
                .id(usuario.getId().toString())
                .username(usuario.getUsername())
                .avatar(usuario.getAvatar())
                .fullName(usuario.getFullName())
                .fechaCreado(usuario.getFechaCreado())
                .build();
    }
}
