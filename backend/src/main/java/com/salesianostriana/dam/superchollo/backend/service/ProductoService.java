package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.producto.ProductoDtoEditRequest;
import com.salesianostriana.dam.superchollo.backend.model.dto.rating.RatingDtoRequest;
import com.salesianostriana.dam.superchollo.backend.model.entity.Rating;
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
import com.salesianostriana.dam.superchollo.backend.service.storage.FileSystemStorageService;
import com.salesianostriana.dam.superchollo.backend.service.storage.StorageService;
import com.salesianostriana.dam.superchollo.backend.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    private final FileSystemStorageService storageService;


    @Transactional
    public Page<Producto> findAll(List<SearchCriteria> criterios, Pageable pageable) {


        ProductoSpecBuilder specBuilder = new ProductoSpecBuilder(criterios);
        Specification<Producto> spec = specBuilder.build();


        Page<Producto> lista = productoRepository.findAll(spec, pageable);

        if(!lista.isEmpty()) {
            productoRepository.getProductosConSupermercados();
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();

        }

        if(lista.isEmpty()) {
            throw new EmptyProductoListException();
        }


        return lista;
    }



    @Transactional
    public Producto findByIdConTodo(UUID id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        if(producto != null) {
            productoRepository.getProductosConTodo();
            productoRepository.findByIdConSupermercados(id);
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();
        }

        return producto;
    }

    @Transactional
    public Producto add(ProductoDtoCreateRequest dto, Usuario usuario, MultipartFile file) {

        String filename = storageService.store(file);
        Categoria categoria = categoriaService.findCategoriaByNombre(dto.getCategoria());
        List<String> listaSupermercados = Arrays.stream(dto.getSupermercados().split(", ")).toList();
        List<Supermercado> supers = listaSupermercados
                                                .stream()
                                                .map(supermercadoService::findByAddress)
                                                .collect(Collectors.toList());

        Usuario autor = usuarioService.findByIdConProductos(usuario.getId());

        if(autor != null) {
            productoRepository.getProductosConTodo();
            productoRepository.getProductosConSupermercados();
            supermercadoRepository.getSupermercadosConProductos();
            supermercadoRepository.getSupermercadosConSeguidores();
        }


        Producto producto = Producto
                .builder()
                .generico(dto.getGenerico())
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .imagen(filename)
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


    public boolean productoExists(String nombre) {
        return productoRepository.existsByNombre(nombre);
    }

    @Transactional
    public Producto edit(UUID id, ProductoDtoEditRequest dto) {

        Optional<Producto> product = productoRepository.findById(id);

        if(product.isPresent()) {
            productoRepository.getProductosConTodo();
            productoRepository.findByIdConSupermercados(id);
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();
        }

        return product.map(producto -> {
            producto.setGenerico(dto.getGenerico());
            producto.setNombre(dto.getNombre());
            producto.setPrecio(dto.getPrecio());
            return producto;
        }).orElseThrow(() -> new ProductoNotFoundException());

    }

    @Transactional
    public Producto editImagen(UUID id, MultipartFile file) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        if(producto != null) {
            productoRepository.getProductosConTodo();
            productoRepository.findByIdConSupermercados(id);
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();
        }



        Path rootLocation = storageService.getRootLocation();

        if(Files.exists(rootLocation.resolve(producto.getImagen()))) {

            storageService.deleteFile(producto.getImagen());
        }


        String filename = storageService.store(file);

        producto.setImagen(filename);

        return productoRepository.save(producto);
    }



    @Transactional
    public Producto valorarProducto(UUID id, Usuario logueado, RatingDtoRequest dto) {

        Rating nota;

        Usuario usuarioConValorados = usuarioService.findByIdConValorados(logueado.getId());

        if(usuarioConValorados != null) {
            productoRepository.getProductosConTodo();
            productoRepository.findByIdConSupermercados(id);
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();

        }

        Producto producto = productoRepository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));

        if(producto != null) {
            productoRepository.getProductosConTodo();
            productoRepository.findByIdConSupermercados(id);
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();
        }

        if(usuarioConValorados.getValorados()
                .stream()
                .anyMatch(val -> val.getProducto().equals(producto))) {
            nota = usuarioConValorados
                    .getValorados()
                    .stream()
                    .filter(val -> val.getProducto().equals(producto))
                    .findFirst().orElseThrow(() -> new ProductoNotFoundException());

            usuarioConValorados.getValorados().remove(nota);

            producto.removeRating(nota);

        }else {
            Rating nuevaNota = new Rating(logueado, producto, LocalDateTime.now(), dto.getNota());

            usuarioConValorados.getValorados().add(nuevaNota);

            producto.addRating(nuevaNota);

        }

        Producto resultado = productoRepository.save(producto);

        usuarioService.add(usuarioConValorados);


        return resultado;
    }

//    public Page<Producto> getProductosFromSupermarket(UUID id, List<SearchCriteria> criterios, Pageable pageable) {
    @Transactional
    public List<Producto> getProductosFromSupermarket(UUID id/*, List<SearchCriteria> criterios, Pageable pageable*/) {

        /*ProductoSpecBuilder specBuilder = new ProductoSpecBuilder(criterios);

        Specification<Producto> spec = specBuilder.build();

        Page<Producto> lista = productoRepository.findAll(spec, pageable);*/

        List<Producto> lista = productoRepository.findProductosFromSupermercado(id);

        if(!lista.isEmpty()) {
            productoRepository.getProductosConTodo();
            productoRepository.getProductosConSupermercados();
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();
        }

        if(lista.isEmpty()) {
            throw new EmptyProductoListException();
        }

        return lista;

    }

    @Transactional
    public void deleteById(UUID id) {

        if(productoRepository.existsById(id)) {

            Producto borrado = findByIdConTodo(id);

            productoRepository.delete(borrado);

        }
    }
}
