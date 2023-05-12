package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.repository.RefreshTokenRepository;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshToken;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.dam.superchollo.backend.security.jwt.refresh.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh.duration}")
    private int durationInMinutes;


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Usuario usuario) {


        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUsuario(usuario);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(durationInMinutes * 60));

        refreshToken = refreshTokenRepository.save(refreshToken);

        log.info("Se ha generado un nuevo token de refresco %s".formatted(refreshToken.toString()));

        return refreshToken;
    }


    public RefreshToken verify(RefreshToken refreshToken) {

        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Expired refresh token: " + refreshToken.getToken() + ". Please, login again");
        }

        return refreshToken;


    }

    @Transactional
    public int deleteByUsuario(Usuario usuario) {
        return refreshTokenRepository.deleteByUsuario(usuario);
    }
}
