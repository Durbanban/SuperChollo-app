package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoEditRequest;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.Supermercado;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception.EmptyProductoListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception.ProductoNotFoundException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.repository.CategoriaRepository;
import com.salesianostriana.dam.superchollo.backend.repository.ProductoRepository;
import com.salesianostriana.dam.superchollo.backend.repository.SupermercadoRepository;
import com.salesianostriana.dam.superchollo.backend.search.spec.ProductoSpecBuilder;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import com.salesianostriana.dam.superchollo.backend.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    private final SupermercadoRepository supermercadoRepository;

    private final UsuarioService usuarioService;
    private final CategoriaRepository categoriaRepository;

    private final CategoriaService categoriaService;

    private final SupermercadoService supermercadoService;

    public Page<Producto> findAll(List<SearchCriteria> criterios, Pageable pageable) {
        List<Producto> lista = productoRepository.findAll();
        if(lista.isEmpty()) {
            throw new EmptyProductoListException();
        }

        ProductoSpecBuilder specBuilder = new ProductoSpecBuilder(criterios);
        Specification<Producto> spec = specBuilder.build();
        return productoRepository.findAll(spec, pageable);
    }

    public Producto findById(UUID id) {
        return productoRepository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));
    }

    public Producto add(ProductoDtoCreateRequest dto, Usuario usuario) {
        Categoria categoria = categoriaService.findCategoriaByNombre(dto.getCategoria());
        List<String> listaSupermercados = Arrays.stream(dto.getSupermercados().split(", ")).toList();
        List<Supermercado> supers = listaSupermercados
                                                .stream()
                                                .map(supermercadoService::findByAddress)
                                                .collect(Collectors.toList());

        Usuario autor = usuarioService.findByIdConProductos(usuario.getId());


        Producto producto = Producto
                .builder()
                .generico(dto.getGenerico())
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .imagen(dto.getImagen())
                .build();

        List<Supermercado> guardados = supers.stream().map(supermercado -> {
            supermercado.addProducto(producto);
            return supermercado;
        }).collect(Collectors.toList());

        productoRepository.save(producto);

        autor.addPublicado(producto);

        categoria.addProducto(producto);

        supermercadoRepository.saveAll(guardados);
        categoriaRepository.save(categoria);
        usuarioService.add(autor);

        return productoRepository.save(producto);

    }

    public List<Producto> getProductosFromSupermarket(UUID id) {
        return productoRepository.getAllProductos(id);
    }

    public boolean productoExists(String nombre) {
        return productoRepository.existsByNombre(nombre);
    }

    public Producto edit(UUID id, ProductoDtoEditRequest dto) {

        return productoRepository.findById(id).map(producto -> {
            producto.setGenerico(dto.getGenerico());
            producto.setNombre(dto.getNombre());
            producto.setPrecio(dto.getPrecio());
            producto.setImagen(dto.getImagen());
            return producto;
        }).orElseThrow(() -> new ProductoNotFoundException());

    }


}
