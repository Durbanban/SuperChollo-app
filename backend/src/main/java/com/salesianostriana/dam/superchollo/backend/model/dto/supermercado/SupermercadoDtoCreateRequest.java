package com.salesianostriana.dam.superchollo.backend.model.dto.supermercado;

import com.salesianostriana.dam.superchollo.backend.validation.annotation.UniqueAddress;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.ValidAddresses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SupermercadoDtoCreateRequest {

    private String nombre;

    @UniqueAddress(message = "{supermercadoDtoCreateRequest.uniqueaddress}")
    private String address;
}
