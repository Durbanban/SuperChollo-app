package com.salesianostriana.dam.superchollo.backend.service.storage;


import com.salesianostriana.dam.superchollo.backend.utils.resource.MediaTypeUrlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService{

    @Value("{storage.location}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct
    @Override
    public void init() {
        rootLocation = Paths.get(storageLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("No se pudo inicializar el almacenamiento", e);
        }
    }

    @Override
    public String store(MultipartFile file) {

        try {
            return store(file.getBytes(), file.getOriginalFilename(), file.getContentType());
        } catch (Exception ex) {
            throw new StorageException("Error al guardar el archivo: " + file.getOriginalFilename(), ex);
        }

    }

    @Override
    public String store(byte[] file, String filename, String contentType) throws Exception {

        String newFileName = StringUtils.cleanPath(filename);
        if(file.length == 0) {
            throw new Exception("El archivo está vacío");
        }

        newFileName = calculateNewFilename(newFileName);

        try(InputStream inputStream = new ByteArrayInputStream(file)) {
            Files.copy(inputStream, rootLocation.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex) {
            throw new StorageException("Error al guardar el archivo: " + newFileName, ex);
        }

        return newFileName;

    }

    private String calculateNewFilename(String filename) {

        String newFileName = filename;

        while(Files.exists(rootLocation.resolve(newFileName))) {
            // Se genera uno nuevo
            String extension = StringUtils.getFilenameExtension(newFileName);
            String name = newFileName.replace("." + extension, "");

            String suffix = Long.toString(System.currentTimeMillis());
            suffix.substring(suffix.length()-6);

            newFileName = name + "_" + suffix + "." + extension;
        }
        return newFileName;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {

        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            MediaTypeUrlResource resource = new MediaTypeUrlResource(file.toUri());

            if(resource.exists() && resource.isReadable()) {
                return resource;
            }else {
                throw new StorageException("No se puede leer el archivo: " + filename);
            }
        }catch (MalformedURLException ex) {
            throw new StorageException("No se puede leer el archivo: " + filename);
        }
    }

    @Override
    public void deleteFile(String filename) {
        try {
            Files.delete(load(filename));
        } catch (IOException ex) {
            throw new StorageException("No se pudo borrar el archivo: " + filename, ex);
        }
    }

    @Override
    public void deleteAll() {
        try {
            FileSystemUtils.deleteRecursively(rootLocation.resolve(rootLocation));
        } catch (IOException ex) {
            throw new StorageException("No se pudieron borrar todos los archivos", ex);
        }

    }
}
