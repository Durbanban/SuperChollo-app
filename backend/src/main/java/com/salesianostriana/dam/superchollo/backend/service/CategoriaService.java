package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.EmptyCategoriaListException;
import com.salesianostriana.dam.superchollo.backend.repository.CategoriaRepository;
import com.salesianostriana.dam.superchollo.backend.search.spec.CategoriaSpecBuilder;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Page<Categoria> findAll(List<SearchCriteria> criterios, Pageable pageable) {
        List<Categoria> lista = categoriaRepository.findAll();
        if(lista.isEmpty()) {
            throw new EmptyCategoriaListException();
        }

        CategoriaSpecBuilder specBuilder = new CategoriaSpecBuilder(criterios);
        Specification<Categoria> spec = specBuilder.build();
        return categoriaRepository.findAll(spec, pageable);
    }
}
