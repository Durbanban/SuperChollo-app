package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.EmptyCategoriaListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.Supermercado;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception.EmptySupermercadoListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception.SupermercadoNotFoundException;
import com.salesianostriana.dam.superchollo.backend.repository.SupermercadoRepository;
import com.salesianostriana.dam.superchollo.backend.search.spec.CategoriaSpecBuilder;
import com.salesianostriana.dam.superchollo.backend.search.spec.SupermercadoSpecBuilder;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupermercadoService {

    private final SupermercadoRepository supermercadoRepository;

    public Supermercado findByAddress(String address) {
        return supermercadoRepository.findByAddress(address);
    }

    public Supermercado findById(UUID id) {
        return supermercadoRepository.findById(id).orElseThrow(() -> new SupermercadoNotFoundException(id));
    }

    public boolean supermercadoExists(String address) {
        return supermercadoRepository.existsByAddress(address);
    }

    public Page<Supermercado> findAll(List<SearchCriteria> criterios, Pageable pageable) {
        List<Supermercado> lista = supermercadoRepository.findAll();
        if(lista.isEmpty()) {
            throw new EmptySupermercadoListException();
        }

        SupermercadoSpecBuilder specBuilder = new SupermercadoSpecBuilder(criterios);
        Specification<Supermercado> spec = specBuilder.build();
        return supermercadoRepository.findAll(spec, pageable);
    }


}
