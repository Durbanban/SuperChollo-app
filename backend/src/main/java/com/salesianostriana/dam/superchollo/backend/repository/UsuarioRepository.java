package com.salesianostriana.dam.superchollo.backend.repository;

import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, JpaSpecificationExecutor<Usuario> {

    Optional<Usuario> findFirstByUsername(String username);

    @EntityGraph(value = "usuario-con-productos-publicados", type = EntityGraph.EntityGraphType.LOAD)
    @Query("""
            SELECT u
            FROM Usuario u
            WHERE u.id = :id
            """)
    Optional<Usuario> findByPublicados(@Param("id") UUID id);



}
