package com.salesianostriana.dam.superchollo.backend.model.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Data
public class RatingPK implements Serializable {

    @Column(columnDefinition = "uuid")
    private UUID usuarioId;

    @Column(columnDefinition = "uuid")
    private UUID productoId;
}
