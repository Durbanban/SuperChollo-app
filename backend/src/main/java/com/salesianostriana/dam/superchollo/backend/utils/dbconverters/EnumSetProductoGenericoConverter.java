package com.salesianostriana.dam.superchollo.backend.utils.dbconverters;

import com.salesianostriana.dam.superchollo.backend.model.entity.producto.ProductoNombreGenerico;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

@Converter
public class EnumSetProductoGenericoConverter implements AttributeConverter<EnumSet<ProductoNombreGenerico>, String> {

    private final String SEPARADOR = ", ";

    @Override
    public String convertToDatabaseColumn(EnumSet<ProductoNombreGenerico> attribute) {
        if(!attribute.isEmpty()) {
            return attribute
                    .stream()
                    .map(ProductoNombreGenerico::name)
                    .collect(Collectors.joining(SEPARADOR));
        }
        return null;
    }

    @Override
    public EnumSet<ProductoNombreGenerico> convertToEntityAttribute(String dbData) {
        if(dbData != null) {
            if(!dbData.isBlank()) {
                return Arrays
                        .stream(dbData.split(SEPARADOR))
                        .map(nombre -> ProductoNombreGenerico.valueOf(nombre))
                        .collect(Collectors.toCollection(() -> EnumSet.noneOf(ProductoNombreGenerico.class)));
            }
        }
        return EnumSet.noneOf(ProductoNombreGenerico.class);
    }
}
