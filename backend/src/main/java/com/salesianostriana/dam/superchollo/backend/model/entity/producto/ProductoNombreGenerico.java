package com.salesianostriana.dam.superchollo.backend.model.entity.producto;

public enum ProductoNombreGenerico {
    FIAMBREPAVO, CHORIZO, JAMON, CHIPS, LENTEJA, GARBANZO, ESPAGUETI, MACARRON, TORTELINI,
    TERNERA, FIAMBREPOLLO, POLLO, ATUN, YOGUR, PAVO, QUESO, OTRO;

    public static boolean contains(String cadena) {

        try{
            ProductoNombreGenerico.valueOf(cadena);
            return true;
        }catch (IllegalArgumentException ex) {
            return false;
        }
    }


}
