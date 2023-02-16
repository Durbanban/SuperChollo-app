package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.entity.Supermercado;
import com.salesianostriana.dam.superchollo.backend.repository.SupermercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupermercadoService {

    private final SupermercadoRepository supermercadoRepository;

    public Supermercado findByAddress(String address) {
        return supermercadoRepository.findByAddress(address);
    }


}
