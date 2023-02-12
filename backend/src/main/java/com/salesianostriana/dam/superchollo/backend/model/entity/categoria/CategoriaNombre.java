package com.salesianostriana.dam.superchollo.backend.model.entity.categoria;

public enum CategoriaNombre {
    CARNICERIA, CHARCUTERIA, CONSUMIBLES, HIGIENE, LIMPIEZA, LEGUMBRES, PASTA, FRUTAS,
    VERDURAS, LACTEOS;

    public static boolean contains(String cadena) {

        try{
            CategoriaNombre.valueOf(cadena);
            return true;
        }catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
