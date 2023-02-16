package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.entity.Supermercado;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception.EmptyProductoListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.exception.ProductoNotFoundException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.repository.ProductoRepository;
import com.salesianostriana.dam.superchollo.backend.search.spec.ProductoSpecBuilder;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
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
        Categoria cat = categoriaService.findCategoriaByNombre(dto.getCategoria());
        List<String> listaSupermercados = Arrays.stream(dto.getSupermercados().split(", ")).toList();
        List<Supermercado> supers = listaSupermercados.stream().map(adr -> supermercadoService.findByAddress(adr)).collect(Collectors.toList());
        Producto producto = Producto
                .builder()
                .generico(dto.getGenerico())
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .imagen(dto.getImagen())
                .categoria(cat)
                .build();

        producto.setSupermercados(supers);
        producto.setAutor(usuario);

        return productoRepository.save(producto);


    }

    public List<Producto> getProductosFromSupermarket(UUID id) {
        return productoRepository.getAllProductos(id);
    }


}
