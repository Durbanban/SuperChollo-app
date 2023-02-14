package com.salesianostriana.dam.superchollo.backend.model.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class PageDto<T> {

    private List<T> content;

    private int paginasTotales;

    private long elementosTotales;

    private int paginaAnterior;

    private int paginaSiguiente;


    public PageDto (Page<T> page) {

        this.content = page.getContent();
        this.paginasTotales = page.getTotalPages();
        this.elementosTotales = page.getTotalElements();
    }
}
