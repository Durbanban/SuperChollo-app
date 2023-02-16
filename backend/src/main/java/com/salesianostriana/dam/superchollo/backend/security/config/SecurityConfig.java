package com.salesianostriana.dam.superchollo.backend.security.config;

import com.salesianostriana.dam.superchollo.backend.security.jwt.access.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final AccessDeniedHandler jwtAccessDeniedHandler;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.authenticationProvider(authenticationProvider()).build();

        return authenticationManager;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*http
                .cors(Customizer.withDefaults())
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .antMatcher("/auth/**")
                    .authorizeRequests()
                        .antMatchers("/auth/register/admin/", "/auth/user/").hasRole("ADMIN")
                .and()
                .antMatcher("/categoria/**")
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/categoria/").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/categoria/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/categoria/**").hasRole("ADMIN")
                .and()
                .antMatcher("/supermercado/**")
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/supermercado/").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/supermercado/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/supermercado/**").hasRole("ADMIN")
                    .anyRequest().authenticated();*/
        http
                .cors(Customizer.withDefaults())
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/register/admin/", "/auth/user/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/categoria/", "/supermercado/").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/categoria/**", "/supermercado/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/categoria/**", "/supermercado/**").hasRole("ADMIN")
                .anyRequest().authenticated();





        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        http.headers().frameOptions().disable();

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().antMatchers("/h2-console/**", "/auth/register/", "/auth/login/"));
    }


}
