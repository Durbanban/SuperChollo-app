package com.salesianostriana.dam.superchollo.backend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.dto.PageDto;
import com.salesianostriana.dam.superchollo.backend.model.dto.categoria.CategoriaDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.dto.supermercado.SupermercadoDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.supermercado.SupermercadoDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.EmptyCategoriaListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.Supermercado;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception.EmptySupermercadoListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.superchollo.backend.service.ProductoService;
import com.salesianostriana.dam.superchollo.backend.service.SupermercadoService;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supermercado")
@Validated
public class SupermercadoController {

    private final SupermercadoService supermercadoService;

    private final ProductoService productoService;

    @GetMapping("/")
    @JsonView(View.SupermercadoView.GeneralSupermercadoView.class)
    public PageDto<SupermercadoDtoResponse> getAlLSupermercados(@RequestParam(value = "search",
            defaultValue = "") String search, @PageableDefault(size = 10, page = 0) Pageable pageable) {

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

        Supermercado supermercado = supermercadoService.findByIdConTodo(id);

        SupermercadoDtoResponse resultado = SupermercadoDtoResponse.of(supermercado);

        return resultado;
    }

    @PostMapping("/")
    @JsonView(View.SupermercadoView.GeneralSupermercadoView.class)
    public ResponseEntity<SupermercadoDtoResponse> crearSupermercado(@Valid @RequestBody SupermercadoDtoCreateRequest dto) {

        Supermercado added = supermercadoService.add(dto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(added.getId()).toUri();

        return ResponseEntity.created(createdURI).body(SupermercadoDtoResponse.of(added));
    }

    @PutMapping("{id}")
    @JsonView(View.SupermercadoView.GeneralSupermercadoView.class)
    public SupermercadoDtoResponse editarSupermercado(@PathVariable UUID id,
                                                      @Valid @RequestBody SupermercadoDtoCreateRequest dto) {
        Supermercado edited = supermercadoService.edit(id, dto);

        return SupermercadoDtoResponse.of(edited);
    }

    @GetMapping("/{id}/catalogo/")
    @JsonView(View.ProductoView.DetailedProductoView.class)
    public List<ProductoDtoResponse> getAllProductosBySupermarket(
            @PathVariable UUID id/*,
            @RequestParam(value = "search", defaultValue = "") String search,
            @PageableDefault(size = 10, page = 0) Pageable pageable*/) {

//        List<SearchCriteria> params = SearchCriteriaExtractor.extractorCriteria(search);

//        Page<Producto> productos =  productoService.getProductosFromSupermarket(id, params, pageable);

        List<Producto> productos = productoService.getProductosFromSupermarket(id/*, params, pageable*/);

        List<ProductoDtoResponse> resultado = productos.stream().map(ProductoDtoResponse::of).collect(Collectors.toList());
        return resultado;
    }

    @PostMapping("/{id}/favorito/")
    @JsonView(View.SupermercadoView.DetailedSupermercadoView.class)
    public SupermercadoDtoResponse marcarFavorito(@PathVariable UUID id, @AuthenticationPrincipal Usuario logueado) {

        Supermercado favorito = supermercadoService.marcarFavorito(id, logueado);

        return SupermercadoDtoResponse.of(favorito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarSupermercado(@PathVariable UUID id) {

        supermercadoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }




}
