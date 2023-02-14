package com.salesianostriana.dam.superchollo.backend.repository;


import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Usuario> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUsuario(Usuario usuario);
}
