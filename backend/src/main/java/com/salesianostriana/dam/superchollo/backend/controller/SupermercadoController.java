package com.salesianostriana.dam.superchollo.backend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.dto.PageDto;
import com.salesianostriana.dam.superchollo.backend.model.dto.categoria.CategoriaDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.dto.supermercado.SupermercadoDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.EmptyCategoriaListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.Supermercado;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception.EmptySupermercadoListException;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.superchollo.backend.service.SupermercadoService;
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
@RequestMapping("/supermercado")
public class SupermercadoController {

    private final SupermercadoService supermercadoService;

    @GetMapping("/")
    @JsonView(View.SupermercadoView.GeneralSupermercadoView.class)
    public PageDto<SupermercadoDtoResponse> getAlLSupermercados(@RequestParam(value = "search",
            defaultValue = "") String search, @PageableDefault(size = 20, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractorCriteria(search);

        Page<Supermercado> lista = supermercadoService.findAll(params, pageable);

        Page<SupermercadoDtoResponse> result = lista.map(SupermercadoDtoResponse::of);

        if(result.isEmpty()) {
            throw new EmptySupermercadoListException();
        }

        return new PageDto<>(result);

    }

    @GetMapping("/{id}")
    @JsonView(View.SupermercadoView.DetailedSupermercadoView.class)
    public SupermercadoDtoResponse getSupermercadoById(@PathVariable UUID id) {

        SupermercadoDtoResponse resultado = SupermercadoDtoResponse.of(supermercadoService.findById(id));

        return resultado;
    }
}
