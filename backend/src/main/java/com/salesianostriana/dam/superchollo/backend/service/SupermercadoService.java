package com.salesianostriana.dam.superchollo.backend.service;

import com.salesianostriana.dam.superchollo.backend.model.dto.supermercado.SupermercadoDtoCreateRequest;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.exception.EmptyCategoriaListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.Supermercado;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception.EmptySupermercadoListException;
import com.salesianostriana.dam.superchollo.backend.model.entity.supermercado.exception.SupermercadoNotFoundException;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.repository.ProductoRepository;
import com.salesianostriana.dam.superchollo.backend.repository.SupermercadoRepository;
import com.salesianostriana.dam.superchollo.backend.search.spec.CategoriaSpecBuilder;
import com.salesianostriana.dam.superchollo.backend.search.spec.SupermercadoSpecBuilder;
import com.salesianostriana.dam.superchollo.backend.search.util.SearchCriteria;
import com.salesianostriana.dam.superchollo.backend.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupermercadoService {

    private final SupermercadoRepository supermercadoRepository;

    private final UsuarioService usuarioService;

    private final ProductoRepository productoRepository;

    public Supermercado findByAddress(String address) {
        return supermercadoRepository.findByAddress(address).orElseThrow((() -> new SupermercadoNotFoundException()));
    }

    @Transactional
    public Supermercado findByIdConTodo(UUID id) {
        Supermercado supermercado =  supermercadoRepository.findConSeguidores(id)
                .orElseThrow(() -> new SupermercadoNotFoundException(id));

        if(supermercado != null) {
            productoRepository.getProductosConTodo();
            supermercadoRepository.findConProductos(id);
        }

        return supermercado;


    }

    public Supermercado findByIdConSeguidores(UUID id) {
        return supermercadoRepository.findById(id).orElseThrow(() -> new SupermercadoNotFoundException(id));
    }

    public boolean supermercadoExists(String address) {
        return supermercadoRepository.existsByAddress(address);
    }

    @Transactional
    public Page<Supermercado> findAll(List<SearchCriteria> criterios, Pageable pageable) {

        SupermercadoSpecBuilder specBuilder = new SupermercadoSpecBuilder(criterios);
        Specification<Supermercado> spec = specBuilder.build();

        Page<Supermercado> lista = supermercadoRepository.findAll(spec, pageable);

        if(!lista.isEmpty()) {
            productoRepository.getProductosConTodo();
            supermercadoRepository.getSupermercadosConProductos();
        }

        if(lista.isEmpty()) {
            throw new EmptySupermercadoListException();
        }

        return supermercadoRepository.findAll(spec, pageable);
    }

    public Supermercado add(SupermercadoDtoCreateRequest dto) {

        Supermercado toAdd = Supermercado
                                    .builder()
                                    .nombre(dto.getNombre())
                                    .address(dto.getAddress())
                                    .build();

        return supermercadoRepository.save(toAdd);
    }


    public Supermercado edit(UUID id, SupermercadoDtoCreateRequest dto) {

        return supermercadoRepository.findById(id).map(editado -> {
            editado.setNombre(dto.getNombre());
            editado.setAddress(dto.getAddress());
            return supermercadoRepository.save(editado);
        }).orElseThrow(() -> new SupermercadoNotFoundException(id));
    }

    @Transactional
    public Supermercado marcarFavorito(UUID id, Usuario logueado) {

        Usuario usuario = usuarioService.findByIdConFavoritos(logueado.getId());

        if(usuario != null) {
            productoRepository.getProductosConTodo();
            usuarioService.findByIdConProductos(logueado.getId());
            supermercadoRepository.getSupermercadosConSeguidores();
            supermercadoRepository.getSupermercadosConProductos();
        }

        Supermercado supermercado = findByIdConSeguidores(id);

        if(usuario.getFavoritos().stream().anyMatch(fav -> fav.equals(supermercado))) {
            usuario.removeFavorito(supermercado);
            usuarioService.add(usuario);
        }else {
            usuario.addFavorito(supermercado);
            usuarioService.add(usuario);
        }


        return supermercadoRepository.save(supermercado);

    }

    @Transactional
    public void deleteById(UUID id) {

        if(supermercadoRepository.existsById(id)) {

            Supermercado supermercado = findByIdConTodo(id);

            supermercadoRepository.delete(supermercado);

        }

    }
}
