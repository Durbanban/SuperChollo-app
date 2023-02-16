package com.salesianostriana.dam.superchollo.backend.model.dto.producto;

import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueProductName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductoDtoEditRequest {

    @NotBlank
    private String generico;

    @NotBlank
    @UniqueProductName(message = "{productoDtoCreateRequest.uniqueproductname}")
    private String nombre;

    @Min(value = 0, message = "{productoDtoCreateRequest.min}")
    private double precio;

    private String imagen;
}
