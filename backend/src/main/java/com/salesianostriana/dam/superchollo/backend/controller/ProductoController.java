package com.salesianostriana.dam.superchollo.backend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.dto.PageDto;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoEditRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.dto.rating.RatingDto;
import com.salesianostriana.dam.superchollo.backend.model.dto.rating.RatingDtoRequest;
import com.salesianostriana.dam.superchollo.backend.model.entity.Rating;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception.EmptyProductoListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto")
@Validated
public class ProductoController {

    private final ProductoService productoService;



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

        Producto producto = productoService.findByIdConTodo(id);

        ProductoDtoResponse resultado = ProductoDtoResponse.of(producto);

        return resultado;
    }

    @PostMapping("/")
    @JsonView(View.ProductoView.DetailedProductoView.class)
    public ResponseEntity<ProductoDtoResponse> createProducto(@Valid @RequestPart("producto") ProductoDtoCreateRequest dto,
                                                              @AuthenticationPrincipal Usuario logueado,
                                                              @RequestPart("file") MultipartFile file) {
        Producto producto = productoService.add(dto, logueado, file);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(producto.getId()).toUri();

        return ResponseEntity.created(createdURI).body(ProductoDtoResponse.of(producto));

    }
    @PreAuthorize("hasRole('ADMIN') or (@productoRepository.findById(#id).orElse(new com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto()).autor.username == authentication.principal.username)")
    @PutMapping("/{id}")
    @JsonView(View.ProductoView.DetailedProductoView.class)
    public ProductoDtoResponse editProducto(@PathVariable UUID id,
                                            @Valid @RequestBody ProductoDtoEditRequest dto) {
        return ProductoDtoResponse.of(productoService.edit(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN') or (@productoRepository.findById(#id).orElse(new com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto()).autor.username == authentication.principal.username)")
    @PutMapping("/imagen/{id}")
    @JsonView(View.ProductoView.DetailedProductoView.class)
    public ProductoDtoResponse editImagenProducto(@PathVariable UUID id,
                                                  @RequestPart("file") MultipartFile file) {

        return ProductoDtoResponse.of(productoService.editImagen(id, file));
    }

    @PostMapping("/valorar/{id}")
    @JsonView(View.ProductoView.DetailedProductoView.class)
    public ProductoDtoResponse valorarProducto(@PathVariable UUID id,
                                               @AuthenticationPrincipal Usuario logueado,
                                               @Valid @RequestBody RatingDtoRequest dto) {
        Producto resultado = productoService.valorarProducto(id, logueado, dto);

        return ProductoDtoResponse.of(resultado);
    }


//    @PreAuthorize("hasRole('ADMIN') or (@productoRepository.findById(#id).orElseThrow(new com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception.ProductoNotFoundException(#id)).autor.username == authentication.principal.username)")
    @PreAuthorize("hasRole('ADMIN') or (@productoRepository.findById(#id).orElse(new com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto()).autor.username == authentication.principal.username)")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable UUID id) {

        productoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }






}
