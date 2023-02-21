package com.salesianostriana.dam.superchollo.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonView({
        View.CategoriaView.GeneralCategoriaView.class,
        View.CategoriaView.DetailedCategoriaView.class,
        View.ProductoView.GeneralProductoView.class,
        View.ProductoView.DetailedProductoView.class,
        View.SupermercadoView.GeneralSupermercadoView.class,
        View.SupermercadoView.DetailedSupermercadoView.class
})
public class PageDto<T> {


    private List<T> contenido;


    private int paginasTotales;


    private long elementosTotales;


    private int paginaAnterior;


    private int paginaSiguiente;

    private int paginaActual;


    public PageDto (Page<T> page) {

        this.contenido = page.getContent();
        this.paginasTotales = page.getTotalPages();
        this.elementosTotales = page.getTotalElements();
        this.paginaAnterior = page.getPageable().getPageNumber() == 0 ? 0 : page.getPageable().getPageNumber() - 1;
        this.paginaActual = page.getPageable().getPageNumber();
        this.paginaSiguiente = page.getPageable().getPageNumber() + 1;
    }
}
