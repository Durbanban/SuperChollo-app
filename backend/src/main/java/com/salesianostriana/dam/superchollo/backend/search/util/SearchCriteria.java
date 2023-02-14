package com.salesianostriana.dam.superchollo.backend.search.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SearchCriteria {

    private String key;

    private String operator;

    private Object value;
}
