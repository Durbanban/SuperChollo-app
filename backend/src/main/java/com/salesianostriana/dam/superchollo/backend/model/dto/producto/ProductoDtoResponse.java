package com.salesianostriana.dam.superchollo.backend.model.dto.producto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@JsonView(View.CategoriaView.DetailedCategoriaView.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDtoResponse {

    private String id;

    private String generico;

    private String nombre;

    private double precio;

    private String imagen;

    private String categoria;

    private String autor;

    public static ProductoDtoResponse of(Producto producto) {
        return ProductoDtoResponse
                .builder()
                .id(producto.getId().toString())
                .generico(producto.getGenerico())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .imagen(producto.getImagen())
                .categoria(producto.getCategoria().getNombre())
                .autor(producto.getAutor().getUsername())
                .build();
    }


}
