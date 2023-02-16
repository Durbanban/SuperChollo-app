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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDtoResponse {

    @JsonView({
            View.ProductoView.GeneralProductoView.class,
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private String id;

    @JsonView({
            View.ProductoView.GeneralProductoView.class,
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private String generico;

    @JsonView({
            View.ProductoView.GeneralProductoView.class,
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private String nombre;

    @JsonView({
            View.ProductoView.GeneralProductoView.class,
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private double precio;

    @JsonView({
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private String imagen;

    @JsonView({
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private String categoria;

    @JsonView({
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
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
