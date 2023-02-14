package com.salesianostriana.dam.superchollo.backend.service.storage;

public class StorageException extends RuntimeException{

    public StorageException(String msg) {
        super(msg);
    }

    public StorageException(String msg, Exception ex) {
        super(msg, ex);
    }
}
