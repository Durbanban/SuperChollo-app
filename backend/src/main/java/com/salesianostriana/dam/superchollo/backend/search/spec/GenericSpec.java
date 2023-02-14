package com.salesianostriana.dam.superchollo.backend.search.spec;

import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@AllArgsConstructor
public class GenericSpec<T> implements Specification {

    private SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        Class tipo = root.get(searchCriteria.getKey()).getJavaType();
        String clave = searchCriteria.getKey();
        String operador = searchCriteria.getOperator();
        Object valor = searchCriteria.getValue();

        if(operador.equalsIgnoreCase(">")) {

            if(isTemporal(tipo)) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(clave), getTemporalValue(valor, tipo));
            }

            return criteriaBuilder.greaterThanOrEqualTo(root.<String>get(clave), valor.toString());

        }else if(operador.equalsIgnoreCase("<")) {

            if(isTemporal(tipo)) {
                return criteriaBuilder.lessThanOrEqualTo(root.get(clave), getTemporalValue(valor, tipo));
            }

            return criteriaBuilder.lessThanOrEqualTo(root.<String>get(clave), valor.toString());

        }else if(operador.equalsIgnoreCase(":")) {

            if(isString(tipo)) {

                if(valor.toString().startsWith("*")) {
                    return criteriaBuilder.like(
                            root.get(clave), valor.toString().substring(1) + "%"
                    );
                }

                return criteriaBuilder.like(
                        root.get(clave), "%" + valor.toString() + "%"
                );

            }else if(isBoolean(tipo)) {

                if(isValidBooleanValue(valor.toString())) {
                    return criteriaBuilder.equal(root.get(clave), getBooleanValue(valor.toString()));
                }else {
                    return null;
                }

            }else if(isTemporal(tipo)) {
                return criteriaBuilder.equal(root.get(clave), getTemporalValue(valor, tipo));
            }

            else {
                return criteriaBuilder.equal(root.<String>get(clave), valor);
            }
        }
        return null;
    }

    private boolean isBoolean(Class c) {
        return c.getName().toLowerCase().contains("boolean");
    }

    private boolean isTemporal(Class c) {
        return Arrays.stream(c.getInterfaces()).anyMatch(cl -> cl.getName() == "java.time.temporal.Temporal");
    }

    private boolean isLocalDate(Class c) {
        return c == LocalDate.class;
    }

    private boolean isLocalDateTime(Class c) {
        return c == LocalDateTime.class;
    }

    private boolean isLocalTime(Class c) {
        return c == LocalTime.class;
    }

    private boolean isString(Class c) {
        return c == String.class;
    }

    private Comparable getTemporalValue(Object valor, Class c) {
        Comparable result;

        if (isLocalDate(c)) {
            // Patrón por defecto: yyyy-mm-dd
            result = LocalDate.parse(valor.toString());
        } else if (isLocalDateTime(c)) {
            // Patrón por defecto: yyyy-mm-dd hh24:mi:ss -- NO VALE POR LOS :. Hay que adaptarlo. Por ejemplo yyyy-MM-dd hh24_mi_ss
            String pattern = "yyyy-MM-dd HH_mm_ss";
            result = LocalDateTime.parse(valor.toString(), DateTimeFormatter.ofPattern(pattern));
        } else if (isLocalTime(c)) {
            // Patrón por defecto: hh24:mi:ss -- NO VALE POR LOS :. Hay que adaptarlo. Por ejemplo hh24_mi_ss
            String pattern = "HH_mm_ss";
            result = LocalTime.parse(valor.toString(), DateTimeFormatter.ofPattern(pattern));
        } else
            result = null;

        return result;
    }

    private boolean getBooleanValue(String valor) {
        return (valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("1"));

    }

    private boolean isValidBooleanValue(String valor) {
        return (valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("1")
                || valor.equalsIgnoreCase("false") || valor.equalsIgnoreCase("0")
        );
    }
}
