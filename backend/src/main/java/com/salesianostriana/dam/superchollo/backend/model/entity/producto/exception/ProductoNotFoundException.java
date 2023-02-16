package com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception;


import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class ProductoNotFoundException extends EntityNotFoundException {

    public ProductoNotFoundException() {
        super("No se ha encontrado el producto");
    }

    public ProductoNotFoundException(UUID id) {
        super(String.format("El producto con el id %s no ha sido encontrado", id.toString()));
    }
}
