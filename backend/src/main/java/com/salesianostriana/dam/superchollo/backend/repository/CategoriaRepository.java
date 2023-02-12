package com.salesianostriana.dam.superchollo.backend.repository;

import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
}
