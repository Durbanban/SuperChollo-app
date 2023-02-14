package com.salesianostriana.dam.superchollo.backend.search.spec;


import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;

import java.util.List;

public class ProductoSpecBuilder extends GenericSpecBuilder<Producto>{

    public ProductoSpecBuilder(List<SearchCriteria> params) {
        super(params, Producto.class);
    }
}
