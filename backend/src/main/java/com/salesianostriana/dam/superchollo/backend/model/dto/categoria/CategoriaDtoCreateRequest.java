package com.salesianostriana.dam.superchollo.backend.model.dto.categoria;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueCategoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaDtoCreateRequest {

    @NotBlank(message = "{categoriaDtoCreateRequest.notblank}")
    @UniqueCategoria(message = "{categoriaDtoCreateRequest.uniquecategoria}")
    private String nombre;


}
