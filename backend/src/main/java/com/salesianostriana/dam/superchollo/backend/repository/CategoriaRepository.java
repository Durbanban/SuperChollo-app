package com.salesianostriana.dam.superchollo.backend.repository;

import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID>, JpaSpecificationExecutor<Categoria> {

    boolean existsByNombre(String nombre);

    Categoria findByNombre(String nombre);

    @EntityGraph(value = "categoria-con-productos", type = EntityGraph.EntityGraphType.LOAD)
    Page<Categoria> findAll(Specification<Categoria> spec, Pageable pageable);

    @Query("""
            SELECT c
            FROM Categoria c
            WHERE c.id = :id
            """)
    @EntityGraph(value = "categoria-con-productos", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Categoria> findByIdConProductos(@Param("id") UUID id);

    @Query("""
            SELECT c
            FROM Categoria c
            WHERE c.id = 'eefcf70d-5bbe-479a-a61b-1f5df283341d'
            """)
    @EntityGraph(value = "categoria-con-productos", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Categoria> findOtros();
}
