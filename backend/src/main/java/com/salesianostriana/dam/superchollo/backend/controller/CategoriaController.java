package com.salesianostriana.dam.superchollo.backend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.dto.PageDto;
import com.salesianostriana.dam.superchollo.backend.model.dto.categoria.CategoriaDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.EmptyCategoriaListException;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.superchollo.backend.service.CategoriaService;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/")
    @JsonView(View.CategoriaView.GeneralCategoriaView.class)
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

    @GetMapping("/{id}")
    @JsonView(View.CategoriaView.DetailedCategoriaView.class)
    public CategoriaDtoResponse getCategoriaById(@PathVariable UUID id) {
        CategoriaDtoResponse resultado = CategoriaDtoResponse.of(categoriaService.findById(id));
        return resultado;
    }
}
