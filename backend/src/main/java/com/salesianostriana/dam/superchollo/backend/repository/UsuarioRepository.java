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

    @Query("""
            SELECT u
            FROM Usuario u
            WHERE u.id = :id
            """)
    @EntityGraph(value = "usuario-con-productos-publicados", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Usuario> findByPublicados(@Param("id") UUID id);



    @Query("""
            SELECT u
            FROM Usuario u
            WHERE u.id = :id
            """)
    @EntityGraph(value = "usuario-con-productos-valorados", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Usuario> findByValorados(@Param("id") UUID id);

    @Query("""
            SELECT u
            FROM Usuario u
            WHERE u.id = :id
            """)
    @EntityGraph(value = "usuario-con-supermercados", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Usuario> findByFavoritos(@Param("id") UUID id);
}
