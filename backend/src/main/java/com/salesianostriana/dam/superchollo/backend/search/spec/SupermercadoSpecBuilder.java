package com.salesianostriana.dam.superchollo.backend.search.spec;

import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.Supermercado;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;

import java.util.List;

public class SupermercadoSpecBuilder extends GenericSpecBuilder<Supermercado> {

    public SupermercadoSpecBuilder(List<SearchCriteria> params) {
        super(params, Supermercado.class);
    }
}
