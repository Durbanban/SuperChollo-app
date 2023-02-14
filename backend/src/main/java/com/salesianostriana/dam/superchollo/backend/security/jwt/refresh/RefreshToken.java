package com.salesianostriana.dam.superchollo.backend.security.jwt.refresh;

import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RefreshToken {

    @Id
    private UUID id;

    @MapsId
    @OneToOne
    @JoinColumn(columnDefinition = "uuid")
    private Usuario usuario;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @CreatedDate
    private Instant createdAt;
}

