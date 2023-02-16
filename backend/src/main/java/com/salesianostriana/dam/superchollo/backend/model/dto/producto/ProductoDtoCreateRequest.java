package com.salesianostriana.dam.superchollo.backend.model.dto.producto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDtoCreateRequest {

    @NotBlank
    private String generico;

    @NotBlank
    private String nombre;

    @Min(0)
    @NotBlank
    private double precio;

    private String imagen;

    @NotBlank
    private String categoria;

    @NotBlank
    private String supermercados;





}
