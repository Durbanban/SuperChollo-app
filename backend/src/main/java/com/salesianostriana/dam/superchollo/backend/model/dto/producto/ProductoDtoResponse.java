package com.salesianostriana.dam.superchollo.backend.model.dto.producto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.dto.supermercado.SupermercadoDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDtoResponse {

    @JsonView({
            View.ProductoView.GeneralProductoView.class,
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class,
            View.SupermercadoView.DetailedSupermercadoView.class
    })
    private String id;

    @JsonView({
            View.ProductoView.GeneralProductoView.class,
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class,
            View.SupermercadoView.DetailedSupermercadoView.class
    })
    private String generico;

    @JsonView({
            View.ProductoView.GeneralProductoView.class,
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class,
            View.SupermercadoView.DetailedSupermercadoView.class
    })
    private String nombre;

    @JsonView({
            View.ProductoView.GeneralProductoView.class,
            View.ProductoView.DetailedProductoView.class,
            View.CategoriaView.DetailedCategoriaView.class,
            View.SupermercadoView.DetailedSupermercadoView.class
    })
    private double precio;

    @JsonView({
            View.ProductoView.DetailedProductoView.class,
    })
    private String imagen;

    @JsonView({
            View.ProductoView.DetailedProductoView.class,
    })
    private String categoria;

    @JsonView({
            View.ProductoView.DetailedProductoView.class,
    })
    private String autor;

    @JsonView({
            View.SupermercadoView.GeneralSupermercadoView.class,
            View.ProductoView.DetailedProductoView.class
    })
    private List<SupermercadoDtoResponse> supermercados;

    public static ProductoDtoResponse of(Producto producto) {

        List<SupermercadoDtoResponse> lista = producto.getSupermercados().
                                                        stream().
                                                        map(SupermercadoDtoResponse::of)
                                                        .collect(Collectors.toList());

        return ProductoDtoResponse
                .builder()
                .id(producto.getId().toString())
                .generico(producto.getGenerico())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .imagen(producto.getImagen())
                .categoria(producto.getCategoria().getNombre())
                .autor(producto.getAutor().getUsername())
                .supermercados(lista)
                .build();
    }

    public static ProductoDtoResponse supermercadoDetails(Producto producto) {

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
