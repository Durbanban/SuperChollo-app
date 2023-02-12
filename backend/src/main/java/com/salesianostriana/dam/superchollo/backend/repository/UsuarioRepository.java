package com.salesianostriana.dam.superchollo.backend.repository;

import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findFirstByUsername(String username);

}
