package com.salesianostriana.dam.superchollo.backend.model.dto.producto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueCategoria;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueProductName;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.ValidAddresses;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.ValidCategoria;
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
    @UniqueProductName(message = "{productoDtoCreateRequest.uniqueproductname}")
    private String nombre;

    @Min(value = 0, message = "{productoDtoCreateRequest.min}")
    private double precio;

    @NotBlank
    @ValidCategoria(message = "{productoDtoCreateRequest.validcategoria}")
    private String categoria;

    @NotBlank
    @ValidAddresses(message = "{productoDtoCreateRequest.validaddresses}")
    private String supermercados;





}
