package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.dto.categoria.CategoriaDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.CategoriaNotFoundException;
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
import java.util.UUID;

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

    public Categoria findById(UUID id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria add(CategoriaDtoCreateRequest dto) {
        Categoria categoria = Categoria
                                    .builder()
                                    .nombre(dto.getNombre())
                                    .build();
        return categoriaRepository.save(categoria);
    }

    public boolean categoriaExists(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }
}
