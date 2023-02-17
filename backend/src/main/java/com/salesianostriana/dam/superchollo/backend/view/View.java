package com.salesianostriana.dam.superchollo.backend.view;

public class View {

    public static interface CategoriaView {

        public static interface GeneralCategoriaView {}
        public static interface DetailedCategoriaView{}
    }

    public static interface ProductoView {

        public static interface GeneralProductoView {}
        public static interface DetailedProductoView {}
    }

    public static interface SupermercadoView {
        public static interface GeneralSupermercadoView{}

        public static interface DetailedSupermercadoView{}
    }
}
