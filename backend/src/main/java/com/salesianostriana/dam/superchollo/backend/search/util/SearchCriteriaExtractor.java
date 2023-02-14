package com.salesianostriana.dam.superchollo.backend.search.util;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteriaExtractor {

    static List<SearchCriteria> extractorCriteria(String busqueda) {

        List<SearchCriteria> params = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)([\\D\\S-_]+?),");

        if(!busqueda.endsWith(",")) {
            busqueda = busqueda + ",";
        }

        Matcher matcher = pattern.matcher(busqueda);

        while(matcher.find()){
            String clave = matcher.group(1);
            String operador = matcher.group(2);
            Object valor = matcher.group(3);

            params.add(new SearchCriteria(clave, operador, valor));
        }

        return params;

    }

}
