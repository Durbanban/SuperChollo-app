package com.salesianostriana.dam.superchollo.backend.repository;

import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.Supermercado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupermercadoRepository extends JpaRepository<Supermercado, UUID>, JpaSpecificationExecutor<Supermercado> {

    Optional<Supermercado> findByAddress(String address);

    boolean existsByAddress(String address);


    @Query("""
            SELECT DISTINCT s
            FROM Supermercado s
            WHERE s.id = :id
            """)
    @EntityGraph(value = "supermercado-con-productos", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Supermercado> findConProductos(@Param("id") UUID id);

    @Query("""
            SELECT DISTINCT s
            FROM Supermercado s
            WHERE s.id = :id
            """)
    @EntityGraph(value = "supermercado-con-usuarios")
    Optional<Supermercado> findConSeguidores(@Param("id") UUID id);

    @Query("""
            SELECT DISTINCT s
            FROM Supermercado s LEFT JOIN FETCH s.seguidores
            """)
    List<Supermercado> getSupermercadosConSeguidores();

    @Query("""
            SELECT DISTINCT s
            FROM Supermercado s LEFT JOIN FETCH s.productos
            """)
    List<Supermercado> getSupermercadosConProductos();

    @EntityGraph(value = "supermercado-con-usuarios", type = EntityGraph.EntityGraphType.LOAD)
    Page<Supermercado> findAll(Specification<Supermercado> spec, Pageable pageable);



}
