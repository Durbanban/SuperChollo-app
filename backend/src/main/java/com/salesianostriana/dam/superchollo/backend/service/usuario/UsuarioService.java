package com.salesianostriana.dam.superchollo.backend.service.usuario;

import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioJwtResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.UsuarioRole;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception.EmptyUsuarioListException;
import com.salesianostriana.dam.superchollo.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(UsuarioDtoCreateRequest dto, EnumSet<UsuarioRole> roles) {
        Usuario usuario = Usuario
                .builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .avatar(dto.getAvatar())
                .fullName(dto.getFullName())
                .roles(roles)
                .build();
        return usuarioRepository.save(usuario);
    }

    public Usuario crearUsuarioConRolUser(UsuarioDtoCreateRequest dto) {
        return crearUsuario(dto, EnumSet.of(UsuarioRole.USER));
    }

    public Usuario crearUsuarioConRolAdmin(UsuarioDtoCreateRequest dto) {
        return crearUsuario(dto, EnumSet.of(UsuarioRole.ADMIN));
    }

    public Optional<Usuario> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findFirstByUsername(username);
    }

    public Optional<Usuario> edit(Usuario usuario) {
        return usuarioRepository.findById(usuario.getId()).map(u -> {
            u.setAvatar(usuario.getAvatar());
            u.setFullName(usuario.getFullName());
            return usuarioRepository.save(u);
        }).or(() -> Optional.empty());
    }

    public Optional<Usuario> editPassword(UUID id, String newPassword) {
        return usuarioRepository.findById(id).map(u -> {
            u.setPassword(passwordEncoder.encode(newPassword));
            return usuarioRepository.save(u);
        }).or(() -> Optional.empty());
    }

    public void delete(Usuario usuario) {
        deleteById(usuario.getId());
    }

    public void deleteById(UUID id) {
        if (usuarioRepository.existsById(id))
            usuarioRepository.deleteById(id);
    }

    public boolean passwordMatch(Usuario usuario, String clearPassword) {
        return passwordEncoder.matches(clearPassword, usuario.getPassword());
    }

    public List<Usuario> findAll() {
        List<Usuario> users = usuarioRepository.findAll();
        if(users.isEmpty()) {
            throw new EmptyUsuarioListException();
        }
        return users;
    }



}
