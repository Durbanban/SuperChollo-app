package com.salesianostriana.dam.superchollo.backend.controller;

import com.salesianostriana.dam.superchollo.backend.model.dto.*;
import com.salesianostriana.dam.superchollo.backend.model.dto.login.LoginRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioJwtResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.security.jwt.access.JwtProvider;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshToken;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshTokenRequest;
import com.salesianostriana.dam.superchollo.backend.service.RefreshTokenService;
import com.salesianostriana.dam.superchollo.backend.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    private final RefreshTokenService refreshTokenService;

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

        refreshTokenService.deleteByUsuario(usuario);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UsuarioJwtResponse.of(usuario, token, refreshToken.getToken()));
    }

    @PutMapping("/user/changePassword")
    public ResponseEntity<UsuarioDtoResponse> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest,
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

    @GetMapping("/user/")
    public List<UsuarioDtoResponse> getAllUsers() {
        List<Usuario> users = usuarioService.findAll();
        List<UsuarioDtoResponse> resultado = users.stream()
                                                    .map(UsuarioDtoResponse::of)
                                                    .collect(Collectors.toList());
        return resultado;
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verify)
                .map(RefreshToken::getUsuario)
                .map(user -> {
                    String token = jwtProvider.generateToken((Authentication) user);
                    refreshTokenService.deleteByUsuario(user);
                    RefreshToken refreshToken2 = refreshTokenService.createRefreshToken(user);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(UsuarioJwtResponse.builder()
                                    .token(token)
                                    .refreshToken(refreshToken2.getToken())
                                    .build());
                })
                .orElseThrow(() -> new RefreshTokenException("Refresh token not found"));

    }

    @PostMapping("/logout/")
    public ResponseEntity<?> logout(@AuthenticationPrincipal Usuario logueado) {

        refreshTokenService.deleteByUsuario(logueado);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }


}
