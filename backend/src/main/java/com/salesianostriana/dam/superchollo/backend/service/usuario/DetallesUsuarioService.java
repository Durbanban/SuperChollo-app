package com.salesianostriana.dam.superchollo.backend.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("detallesUsuarioService")
@RequiredArgsConstructor
public class DetallesUsuarioService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario: " + username));
    }
}
