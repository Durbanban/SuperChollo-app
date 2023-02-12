package com.salesianostriana.dam.superchollo.backend.model.entity.producto;

public enum ProductoNombreGenerico {
    PAVO, CHORIZO, JAMON, CHIPS, LENTEJAS, GARBANZOS, ESPAGUETIS, MACARRONES, TORTELINIS,
    TERNERA, POLLO, ATUN, OTRO;

    public static boolean contains(String cadena) {

        try{
            ProductoNombreGenerico.valueOf(cadena);
            return true;
        }catch (IllegalArgumentException ex) {
            return false;
        }
    }


}
