package com.salesianostriana.dam.superchollo.backend.security.jwt.access;

import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import com.salesianostriana.dam.superchollo.backend.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UsuarioService usuarioService;
    private final JwtProvider jwtProvider;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtTokenFromRequest(request);

        try {
            if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
                UUID id = jwtProvider.getUserIdFromJwtToken(token);

                Optional<Usuario> result = usuarioService.findById(id);

                if (result.isPresent()) {
                    Usuario usuario = result.get();

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    usuario,
                                    null,
                                    usuario.getAuthorities()
                            );

                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        } catch (Exception ex) {
            log.info("Authentication error using token JWT: " + ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtProvider.TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProvider.TOKEN_PREFIX)) {
            return bearerToken.substring(JwtProvider.TOKEN_PREFIX.length());
        }
        return null;
    }
}
