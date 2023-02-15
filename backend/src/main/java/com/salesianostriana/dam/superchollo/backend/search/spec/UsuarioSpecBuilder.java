package com.salesianostriana.dam.superchollo.backend.search.spec;

import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;

import java.util.List;

public class UsuarioSpecBuilder extends GenericSpecBuilder<Usuario> {

    public UsuarioSpecBuilder(List<SearchCriteria> params) {
        super(params, Usuario.class);
    }
}
