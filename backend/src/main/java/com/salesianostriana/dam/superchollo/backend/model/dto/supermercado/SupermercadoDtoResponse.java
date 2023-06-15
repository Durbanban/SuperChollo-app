package com.salesianostriana.dam.superchollo.backend.model.dto.supermercado;


import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.dto.usuario.UsuarioDtoResponse;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.Supermercado;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SupermercadoDtoResponse {

    @JsonView({
            View.SupermercadoView.GeneralSupermercadoView.class,
            View.SupermercadoView.DetailedSupermercadoView.class,
            View.ProductoView.DetailedProductoView.class
    })
    private UUID id;

    @JsonView({
            View.SupermercadoView.GeneralSupermercadoView.class,
            View.SupermercadoView.DetailedSupermercadoView.class,
            View.ProductoView.DetailedProductoView.class
    })
    private String nombre;

    @JsonView({
            View.SupermercadoView.GeneralSupermercadoView.class,
            View.SupermercadoView.DetailedSupermercadoView.class,
            View.ProductoView.DetailedProductoView.class
    })
    private String address;

    @JsonView({
            View.SupermercadoView.DetailedSupermercadoView.class
    })
    private List<UsuarioDtoResponse> seguidores;

    @JsonView({
            View.SupermercadoView.DetailedSupermercadoView.class
    })
    private List<ProductoDtoResponse> productos;


    public static SupermercadoDtoResponse of(Supermercado supermercado) {

        List<UsuarioDtoResponse> listaSeguidores = supermercado.getSeguidores()
                                                                    .stream()
                                                                    .map(UsuarioDtoResponse::of)
                                                                    .collect(Collectors.toList());

        List<ProductoDtoResponse> listaProductos = supermercado.getProductos()
                                                                .stream()
                                                                .map(ProductoDtoResponse::supermercadoDetails)
                                                                .collect(Collectors.toList());

        return SupermercadoDtoResponse
                            .builder()
                            .id(supermercado.getId())
                            .nombre(supermercado.getNombre())
                            .address(supermercado.getAddress())
                            .seguidores(listaSeguidores)
                            .productos(listaProductos)
                            .build();
    }




}
