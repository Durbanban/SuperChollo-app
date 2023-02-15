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
public class PageDto<T> {

    @JsonView({
            View.CategoriaView.GeneralCategoriaView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private List<T> contenido;

    @JsonView({
            View.CategoriaView.GeneralCategoriaView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private int paginasTotales;

    @JsonView({
            View.CategoriaView.GeneralCategoriaView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private long elementosTotales;

    @JsonView({
            View.CategoriaView.GeneralCategoriaView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private int paginaAnterior;

    @JsonView({
            View.CategoriaView.GeneralCategoriaView.class,
            View.CategoriaView.DetailedCategoriaView.class
    })
    private int paginaSiguiente;


    public PageDto (Page<T> page) {

        this.contenido = page.getContent();
        this.paginasTotales = page.getTotalPages();
        this.elementosTotales = page.getTotalElements();
    }
}
