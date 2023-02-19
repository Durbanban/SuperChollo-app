package com.salesianostriana.dam.superchollo.backend.controller;

import com.salesianostriana.dam.superchollo.backend.model.dto.FileDtoResponse;
import com.salesianostriana.dam.superchollo.backend.service.storage.FileSystemStorageService;
import com.salesianostriana.dam.superchollo.backend.service.storage.StorageService;
import com.salesianostriana.dam.superchollo.backend.utils.resource.MediaTypeUrlResource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileSystemStorageService storageService;

    @PostMapping("/upload/")
    public ResponseEntity<?> upload(@RequestPart("file") MultipartFile file) {

        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder
                                        .fromCurrentContextPath()
                                        .path("/download/")
                                        .path(name)
                                        .toUriString();

        FileDtoResponse respuesta = FileDtoResponse
                                            .builder()
                                            .name(name)
                                            .size(file.getSize())
                                            .type(file.getContentType())
                                            .uri(uri)
                                            .build();

        return ResponseEntity.created(URI.create(uri)).body(respuesta);

    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {

        MediaTypeUrlResource resource = (MediaTypeUrlResource) storageService.loadAsResource(filename);

        return ResponseEntity.status(HttpStatus.OK).header("Content-Type", resource.getType())
                .body(resource);

    }
}
