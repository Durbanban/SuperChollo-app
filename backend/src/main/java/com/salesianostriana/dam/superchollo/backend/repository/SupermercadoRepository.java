package com.salesianostriana.dam.superchollo.backend.repository;

import com.salesianostriana.dam.superchollo.backend.model.entity.Supermercado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupermercadoRepository extends JpaRepository<Supermercado, UUID> {
}
