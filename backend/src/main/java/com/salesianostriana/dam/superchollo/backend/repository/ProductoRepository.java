package com.salesianostriana.dam.superchollo.backend.repository;

import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {
}
