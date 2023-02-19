package com.salesianostriana.dam.superchollo.backend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.dto.PageDto;
import com.salesianostriana.dam.superchollo.backend.model.dto.categoria.CategoriaDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.categoria.CategoriaDtoEditRequest;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
@Validated
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/")
    @JsonView(View.CategoriaView.GeneralCategoriaView.class)
    public PageDto<CategoriaDtoResponse> getAlLCategorias(@RequestParam(value = "search",
            defaultValue = "") String search, @PageableDefault(size = 10, page = 0) Pageable pageable) {

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

        Categoria categoria = categoriaService.findByIdConTodo(id);

        CategoriaDtoResponse resultado = CategoriaDtoResponse.of(categoria);

        return resultado;
    }

    @PostMapping("/")
    @JsonView(View.CategoriaView.GeneralCategoriaView.class)
    public ResponseEntity<CategoriaDtoResponse> createCategoria(@Valid @RequestBody CategoriaDtoCreateRequest dto) {

        Categoria categoria = categoriaService.add(dto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(createdURI).body(CategoriaDtoResponse.of(categoria));
    }

    @PutMapping("/{id}")
    @JsonView(View.CategoriaView.GeneralCategoriaView.class)
    public CategoriaDtoResponse editCategoria(@PathVariable UUID id, @Valid @RequestBody CategoriaDtoEditRequest dto) {

        return CategoriaDtoResponse.of(categoriaService.edit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable UUID id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
