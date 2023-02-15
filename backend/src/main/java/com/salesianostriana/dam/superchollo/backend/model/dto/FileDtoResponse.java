package com.salesianostriana.dam.superchollo.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class FileDtoResponse {

    private String name;

    private String uri;

    private String type;

    private long size;

}
