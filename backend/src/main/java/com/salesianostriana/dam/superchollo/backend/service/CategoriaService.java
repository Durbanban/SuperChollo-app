package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.dto.categoria.CategoriaDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.categoria.CategoriaDtoEditRequest;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.CategoriaNotFoundException;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.EmptyCategoriaListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.repository.CategoriaRepository;
import com.salesianostriana.dam.superchollo.backend.repository.ProductoRepository;
import com.salesianostriana.dam.superchollo.backend.repository.SupermercadoRepository;
import com.salesianostriana.dam.superchollo.backend.search.spec.CategoriaSpecBuilder;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    private final ProductoRepository productoRepository;

    private final SupermercadoRepository supermercadoRepository;

    @Transactional
    public Page<Categoria> findAll(List<SearchCriteria> criterios, Pageable pageable) {

        CategoriaSpecBuilder specBuilder = new CategoriaSpecBuilder(criterios);
        Specification<Categoria> spec = specBuilder.build();

        Page<Categoria> lista = categoriaRepository.findAll(spec, pageable);

        if(!lista.isEmpty()) {
            productoRepository.getProductosConSupermercados();
            productoRepository.getProductosConTodo();
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();
        }


        if(lista.isEmpty()) {
            throw new EmptyCategoriaListException();
        }

        return categoriaRepository.findAll(spec, pageable);
    }

    public Categoria findById(UUID id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria add(CategoriaDtoCreateRequest dto) {
        Categoria categoria = Categoria
                                    .builder()
                                    .nombre(dto.getNombre())
                                    .build();

        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria edit(UUID id, CategoriaDtoEditRequest dto) {

        Optional<Categoria> categoria = categoriaRepository.findByIdConProductos(id);

        if(categoria.isPresent()) {
            productoRepository.getProductosConTodo();
            productoRepository.findByIdConSupermercados(id);
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();
        }

        return categoria.map(cat -> {
            cat.setNombre(dto.getNombre());
            return cat;
        }).orElseThrow(() -> new CategoriaNotFoundException(id));

    }

    @Transactional
    public void deleteById(UUID id) {
        if(categoriaRepository.existsById(id)) {

            Categoria borrada = findByIdConTodo(id);

            categoriaRepository.delete(borrada);
        }
    }

    public boolean categoriaExists(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }

    public Categoria findCategoriaByNombre(String nombre) {
        return categoriaRepository.findByNombreIgnoreCase(nombre);
    }

    @Transactional
    public Categoria findByIdConTodo(UUID id) {

        Categoria categoria = categoriaRepository.findByIdConProductos(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        if(categoria != null) {
            productoRepository.getProductosConSupermercados();
            productoRepository.getProductosConTodo();
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();
        }

        return categoria;

    }
}
