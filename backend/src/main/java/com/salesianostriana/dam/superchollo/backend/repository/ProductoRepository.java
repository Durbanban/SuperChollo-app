package com.salesianostriana.dam.superchollo.backend.repository;

import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID>, JpaSpecificationExecutor<Producto> {

    /*@Modifying
    @Query("""
            DELETE FROM Producto p
            WHERE p.id IN (SELECT p.id
                                    FROM Producto p
                                    WHERE p.categoria.id = :id
                                        )
            """)
    void deleteAllProductosfromSupermercadosByCategoria(UUID id);*/



    @Query("""
            SELECT p
            FROM Producto p JOIN FETCH p.supermercados s
            WHERE s.id = :id
            """)
    List<Producto> getAllProductos(@Param("id") UUID id);

    boolean existsByNombre(String nombre);


}
