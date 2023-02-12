package com.salesianostriana.dam.superchollo.backend.utils.dbconverters;

import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.UsuarioRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

@Converter
public class EnumSetRolesConverter implements AttributeConverter<EnumSet<UsuarioRole>, String> {

    private final String SEPARADOR = ", ";


    @Override
    public String convertToDatabaseColumn(EnumSet<UsuarioRole> attribute) {
        if(!attribute.isEmpty()) {
            return attribute
                    .stream()
                    .map(UsuarioRole::name)
                    .collect(Collectors.joining(SEPARADOR));
        }
        return null;
    }

    @Override
    public EnumSet<UsuarioRole> convertToEntityAttribute(String dbData) {
        if(dbData != null) {
            if(!dbData.isBlank()) {
                return Arrays
                        .stream(dbData.split(SEPARADOR))
                        .map(rol -> UsuarioRole.valueOf(rol))
                        .collect(Collectors.toCollection(() -> EnumSet.noneOf(UsuarioRole.class)));
            }
        }
        return EnumSet.noneOf(UsuarioRole.class);
    }
}
