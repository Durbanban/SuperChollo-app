package com.salesianostriana.dam.superchollo.backend.controller;

import com.salesianostriana.dam.superchollo.backend.model.dto.*;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.security.jwt.JwtProvider;
import com.salesianostriana.dam.superchollo.backend.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/register/")
    public ResponseEntity<UsuarioDtoResponse> crearUsuarioConRolUser(@Valid @RequestBody UsuarioDtoCreateRequest dto) {
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
    @PostMapping("/login/")
    public ResponseEntity<UsuarioJwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                                                    .authenticate(new UsernamePasswordAuthenticationToken(
                                                            loginRequest.getUsername(),
                                                            loginRequest.getPassword()
                                                    ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioJwtResponse.of(usuario, token));
    }

    @PutMapping("/user/changePassword")
    public ResponseEntity<UsuarioDtoResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                                       @AuthenticationPrincipal Usuario loggedUser) {

        // Este código es mejorable.
        // La validación de la contraseña nueva se puede hacer con un validador.
        // La gestión de errores se puede hacer con excepciones propias
        try {
            if (usuarioService.passwordMatch(loggedUser, changePasswordRequest.getOldPassword())) {
                Optional<Usuario> modificado = usuarioService.editPassword(loggedUser.getId(), changePasswordRequest.getNewPassword());
                if (modificado.isPresent())
                    return ResponseEntity.ok(UsuarioDtoResponse.of(modificado.get()));
            } else {
                // Lo ideal es que esto se gestionara de forma centralizada
                // Se puede ver cómo hacerlo en la formación sobre Validación con Spring Boot
                // y la formación sobre Gestión de Errores con Spring Boot
                throw new RuntimeException();
            }
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Data Error");
        }

        return null;
    }


}
