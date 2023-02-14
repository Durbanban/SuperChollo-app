package com.salesianostriana.dam.superchollo.backend.model.dto.categoria;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CategoriaDtoResponse {

    @JsonView({
            View.CategoriaView.GeneralCategoriaView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private String id;

    @JsonView({
            View.CategoriaView.GeneralCategoriaView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private String nombre;

    @JsonView({
            View.CategoriaView.DetailedCategoriaView.class
    })
    private List<ProductoDtoResponse> productos = new ArrayList<>();

    public static CategoriaDtoResponse of(Categoria categoria) {
        return CategoriaDtoResponse
                .builder()
                .id(categoria.getId().toString())
                .nombre(categoria.getNombre())
                .productos(categoria.getProductos()
                                        .stream()
                                        .map(ProductoDtoResponse::of)
                                        .collect(Collectors.toList()))
                .build();
    }


}
