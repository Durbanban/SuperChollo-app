package com.salesianostriana.dam.superchollo.backend.controller;

import com.salesianostriana.dam.superchollo.backend.model.dto.*;
import com.salesianostriana.dam.superchollo.backend.model.dto.login.LoginRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioJwtResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception.UserOldPasswordWrongException;
import com.salesianostriana.dam.superchollo.backend.security.jwt.access.JwtProvider;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshToken;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshTokenRequest;
import com.salesianostriana.dam.superchollo.backend.service.RefreshTokenService;
import com.salesianostriana.dam.superchollo.backend.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public ResponseEntity<UsuarioDtoResponse> crearUsuarioConRolUser(@Valid @RequestPart("usuario") UsuarioDtoCreateRequest dto,
                                                                     @RequestPart("file") MultipartFile file) {
        Usuario usuario = usuarioService.crearUsuarioConRolUser(dto, file);

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioDtoResponse.of(usuario));
    }

    @PostMapping("/register/admin/")
    public ResponseEntity<UsuarioDtoResponse> crearUsuarioConRolAdmin(@Valid @RequestPart("usuario") UsuarioDtoCreateRequest dto,
                                                                      @RequestPart("file") MultipartFile file) {
        Usuario usuario = usuarioService.crearUsuarioConRolAdmin(dto, file);


        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioDtoResponse.of(usuario));
    }

    @PutMapping("/user/editAvatar/")
    public UsuarioDtoResponse editarAvatar(@AuthenticationPrincipal Usuario logueado,
                                           @RequestPart("file") MultipartFile file) {

        return UsuarioDtoResponse.of(usuarioService.editAvatar(logueado, file));

    }


    @Transactional
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

    @PutMapping("/user/changePassword/")
    public ResponseEntity<UsuarioDtoResponse> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest,
                                                       @AuthenticationPrincipal Usuario loggedUser) {

        if(usuarioService.passwordMatch(loggedUser, changePasswordRequest.getOldPassword())) {
            Usuario modificado = usuarioService.editPassword(loggedUser.getId(), changePasswordRequest.getNewPassword());
            return ResponseEntity.ok(UsuarioDtoResponse.of(modificado));
        }else {
            throw new UserOldPasswordWrongException();
        }

    }

    @GetMapping("/user/")
    public List<UsuarioDtoResponse> getAllUsers() {
        List<Usuario> users = usuarioService.findAll();
        List<UsuarioDtoResponse> resultado = users.stream()
                                                    .map(UsuarioDtoResponse::of)
                                                    .collect(Collectors.toList());
        return resultado;
    }

    @Transactional
    @PostMapping("/refreshtoken/")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verify)
                .map(RefreshToken::getUsuario)
                .map(user -> {
                    String token = jwtProvider.generateToken(user);
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

    @GetMapping("/me/")
    public UsuarioDtoResponse checkMyUser(@AuthenticationPrincipal Usuario logueado) {
        return UsuarioDtoResponse.of(logueado);
    }

    @PreAuthorize("hasRole('ADMIN') or (@usuarioRepository.findById(#id).orElse(new com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario()).username == authentication.principal.username)")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable UUID id) {

        usuarioService.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
