package com.salesianostriana.dam.superchollo.backend.repository;

import com.salesianostriana.dam.superchollo.backend.model.entity.Rating;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID>, JpaSpecificationExecutor<Producto> {


    boolean existsByNombre(String nombre);


    @EntityGraph(value = "producto-con-todo")
    Optional<Producto> findById(UUID id);


    @EntityGraph(value = "producto-con-todo", type = EntityGraph.EntityGraphType.LOAD)
    Page<Producto> findAll(Specification<Producto> spec, Pageable pageable);

    @Query("""
            SELECT DISTINCT p
            FROM Producto p
            """)
    @EntityGraph(value = "producto-con-todo", type = EntityGraph.EntityGraphType.LOAD)
    List<Producto> getProductosConTodo();

    @Query("""
            SELECT DISTINCT p
            FROM Producto p
            """)
    @EntityGraph(value = "producto-con-supermercados", type = EntityGraph.EntityGraphType.LOAD)
    List<Producto> getProductosConSupermercados();

    @Query("""
            SELECT p
            FROM Producto p
            WHERE p.id = :id
            """)
    @EntityGraph(value = "producto-con-supermercados", type = EntityGraph.EntityGraphType.LOAD)
    Producto findByIdConSupermercados(@Param("id") UUID id);

    @Query("""
            SELECT DISTINCT p
            FROM Producto p LEFT JOIN FETCH p.supermercados s
            WHERE s.id = :id
            """)
    List<Producto> findProductosFromSupermercado(@Param("id") UUID id);





}
