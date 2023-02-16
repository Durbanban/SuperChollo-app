package com.salesianostriana.dam.superchollo.backend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.dto.PageDto;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoEditRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception.EmptyProductoListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.repository.ProductoRepository;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.superchollo.backend.service.ProductoService;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    private final ProductoRepository productoRepository;

    @GetMapping("/supermercado/{id}")
    @JsonView(View.ProductoView.DetailedProductoView.class)
    public List<ProductoDtoResponse> getAllProductosBySupermarket(@PathVariable UUID id) {
        List<Producto> productos =  productoService.getProductosFromSupermarket(id);
        List<ProductoDtoResponse> resultado = productos.stream().map(ProductoDtoResponse::of).collect(Collectors.toList());
        return resultado;
    }

    @GetMapping("/")
    @JsonView(View.ProductoView.GeneralProductoView.class)
    public PageDto<ProductoDtoResponse> getAllProductos(@RequestParam(value = "search",
    defaultValue = "") String search, @PageableDefault(size = 10, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractorCriteria(search);

        Page<Producto> lista = productoService.findAll(params, pageable);

        Page<ProductoDtoResponse> result = lista.map(ProductoDtoResponse::of);

        if(result.isEmpty()) {
            throw new EmptyProductoListException();
        }

        return new PageDto<>(result);
    }

    @GetMapping("/{id}")
    @JsonView(View.ProductoView.DetailedProductoView.class)
    public ProductoDtoResponse getProductoById(@PathVariable UUID id) {

        ProductoDtoResponse resultado = ProductoDtoResponse.of(productoService.findById(id));

        return resultado;
    }

    @PostMapping("/")
    @JsonView(View.ProductoView.DetailedProductoView.class)
    public ResponseEntity<ProductoDtoResponse> createProducto(@Valid @RequestBody ProductoDtoCreateRequest dto,
                                                              @AuthenticationPrincipal Usuario logueado) {
        Producto producto = productoService.add(dto, logueado);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(producto.getId()).toUri();

        return ResponseEntity.created(createdURI).body(ProductoDtoResponse.of(producto));

    }
    @PreAuthorize("@productoRepository.findById(#id).autor == authentication.principal.getId().toString()")
    @PutMapping("/{id}")
    @JsonView(View.ProductoView.DetailedProductoView.class)
    public ProductoDtoResponse editProducto(@PathVariable UUID id,
                                            @Valid @RequestBody ProductoDtoEditRequest dto) {
        return ProductoDtoResponse.of(productoService.edit(id, dto));
    }



}
