package com.salesianostriana.dam.superchollo.backend.search.spec;

import com.salesianostriana.dam.superchollo.backend.search.util.QueryableEntity;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GenericSpecBuilder <T> {

    private List<SearchCriteria> params;

    private Class tipo;

    public Specification<T> build() {

        List<SearchCriteria> checkedParams = params
                .stream()
                .filter(param -> !param.getKey().equalsIgnoreCase("id") && QueryableEntity.checkQueryParam(tipo, param.getKey()))
                .collect(Collectors.toList());

        if(checkedParams.isEmpty()) {
            return null;
        }


        Specification<T> resultado = new GenericSpec<>(checkedParams.get(0));

        for (int i = 1; i < checkedParams.size(); i++) {
            resultado = resultado.and(new GenericSpec<T>(checkedParams.get(i)));

        }
        return resultado;
    }
}
