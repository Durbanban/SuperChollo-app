package com.salesianostriana.dam.superchollo.backend.controller;

import com.salesianostriana.dam.superchollo.backend.model.dto.PageDto;
import com.salesianostriana.dam.superchollo.backend.model.dto.categoria.CategoriaDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.EmptyCategoriaListException;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.superchollo.backend.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/")
    public PageDto<CategoriaDtoResponse> getAlLCategorias(@RequestParam(value = "search",
            defaultValue = "") String search, @PageableDefault(size = 20, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractorCriteria(search);

        Page<Categoria> lista = categoriaService.findAll(params, pageable);

        Page<CategoriaDtoResponse> result = lista.map(CategoriaDtoResponse::of);

        if(result.isEmpty()) {
            throw new EmptyCategoriaListException();
        }

        return new PageDto<>(result);

    }
}
