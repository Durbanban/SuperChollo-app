package com.salesianostriana.dam.superchollo.backend.service.usuario;

import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioJwtResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.Supermercado;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.UsuarioRole;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception.EmptyUsuarioListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.exception.UsuarioNotFoundException;
import com.salesianostriana.dam.superchollo.backend.repository.ProductoRepository;
import com.salesianostriana.dam.superchollo.backend.repository.RefreshTokenRepository;
import com.salesianostriana.dam.superchollo.backend.repository.SupermercadoRepository;
import com.salesianostriana.dam.superchollo.backend.repository.UsuarioRepository;
import com.salesianostriana.dam.superchollo.backend.service.RefreshTokenService;
import com.salesianostriana.dam.superchollo.backend.service.storage.FileSystemStorageService;
import com.salesianostriana.dam.superchollo.backend.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final FileSystemStorageService storageService;

    private final ProductoRepository productoRepository;

    private final SupermercadoRepository supermercadoRepository;

    private final RefreshTokenService refreshTokenService;

    @Transactional
    public Usuario crearUsuario(UsuarioDtoCreateRequest dto, EnumSet<UsuarioRole> roles, MultipartFile file) {

        String filename = storageService.store(file);

        Usuario usuario = Usuario
                .builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .avatar(filename)
                .fullName(dto.getFullName())
                .roles(roles)
                .build();
        return usuarioRepository.save(usuario);
    }

    public Usuario crearUsuarioConRolUser(UsuarioDtoCreateRequest dto, MultipartFile file) {
        return crearUsuario(dto, EnumSet.of(UsuarioRole.USER), file);
    }

    public Usuario crearUsuarioConRolAdmin(UsuarioDtoCreateRequest dto, MultipartFile file) {
        return crearUsuario(dto, EnumSet.of(UsuarioRole.ADMIN), file);
    }

    public Optional<Usuario> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findFirstByUsername(username);
    }

    public Optional<Usuario> edit(Usuario usuario) {
        return usuarioRepository.findById(usuario.getId()).map(u -> {
            u.setFullName(usuario.getFullName());
            return usuarioRepository.save(u);
        }).or(() -> Optional.empty());
    }

    public Usuario editPassword(UUID id, String newPassword) {
        return usuarioRepository.findById(id).map(u -> {
            u.setPassword(passwordEncoder.encode(newPassword));
            return usuarioRepository.save(u);
        }).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Usuario editAvatar(Usuario logueado, MultipartFile file) {

        Path rootLocation = storageService.getRootLocation();

        if(Files.exists(rootLocation.resolve(logueado.getAvatar()))) {
            storageService.deleteFile(logueado.getAvatar());

        }


        String filename = storageService.store(file);

        logueado.setAvatar(filename);

        return usuarioRepository.save(logueado);

    }

    public void delete(Usuario usuario) {
        deleteById(usuario.getId());
    }

    @Transactional
    public void deleteById(UUID id) {
        if (usuarioRepository.existsById(id)) {

            refreshTokenService.deleteByUsuario(usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id)));
            usuarioRepository.deleteById(id);

        }
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


    public Usuario findByIdConProductos(UUID id) {
        return usuarioRepository.findByPublicados(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Usuario findByIdConValorados(UUID id) {
        return usuarioRepository.findByValorados(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Usuario findByIdConFavoritos(UUID id) {
        return usuarioRepository.findByFavoritos(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Usuario add(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }




}
