package com.salesianostriana.dam.superchollo.backend.search.spec;


import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;

import java.util.List;

public class CategoriaSpecBuilder extends GenericSpecBuilder<Categoria>{

    public CategoriaSpecBuilder(List<SearchCriteria> params) {
        super(params, Categoria.class);
    }
}
