package com.salesianostriana.dam.superchollo.backend.controller;

import com.salesianostriana.dam.superchollo.backend.model.dto.UsuarioDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.UsuarioDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/register/")
    public ResponseEntity<UsuarioDtoResponse> crearUsuarioConRolUser(@RequestBody UsuarioDtoCreateRequest dto) {
        Usuario usuario = usuarioService.crearUsuarioConRolUser(dto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(createdURI).body(UsuarioDtoResponse.of(usuario));
    }

    @PostMapping("/register/admin/")
    public ResponseEntity<UsuarioDtoResponse> crearUsuarioConRolAdmin(@RequestBody UsuarioDtoCreateRequest dto) {
        Usuario usuario = usuarioService.crearUsuarioConRolAdmin(dto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(createdURI).body(UsuarioDtoResponse.of(usuario));
    }
}
