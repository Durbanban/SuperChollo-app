package com.salesianostriana.dam.superchollo.backend.model.entity.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.model.entity.Rating;
import com.salesianostriana.dam.superchollo.backend.model.entity.Supermercado;
import com.salesianostriana.dam.superchollo.backend.utils.dbconverters.EnumSetRolesConverter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value ="org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(columnDefinition = "uuid")
    private UUID id;

    @NaturalId
    @Column(unique = true, updatable = false)
    private String username;

    private String password;

    private String verifyPassword;

    private String avatar;

    private String fullName;

    @Builder.Default
    private boolean accountNonExpired = true;

    @Builder.Default
    private boolean accountNonLocked = true;

    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Builder.Default
    private boolean enabled = true;

    @Convert(converter = EnumSetRolesConverter.class)
    private EnumSet<UsuarioRole> roles;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime fechaCreado;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime fechaModificado;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime fechaCambioPassword = LocalDateTime.now();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Rating> valorados = new ArrayList<>();

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Producto> publicados = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "preferido",
            joinColumns = @JoinColumn(
                    name = "usuario_id",
                    foreignKey = @ForeignKey(name = "FK_PREFERIDO_USUARIO")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "supermercado_id",
                    foreignKey = @ForeignKey(name = "FK_PREFERIDO_SUPERMERCADO")
            )
    )
    @Builder.Default
    private List<Supermercado> favoritos = new ArrayList<>();


    public void addFavorito(Supermercado supermercado) {
        supermercado.getSeguidores().add(this);
        this.favoritos.add(supermercado);
    }

    public void removeFavorito(Supermercado supermercado) {
        supermercado.getSeguidores().remove(this);
        this.favoritos.remove(supermercado);
    }

    public void addPublicado(Producto publicado) {
        publicado.setAutor(this);
        this.publicados.add(publicado);
    }

    public void removePublicado(Producto publicado) {
        publicado.setAutor(null);
        this.publicados.remove(publicado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

