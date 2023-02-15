package com.salesianostriana.dam.superchollo.backend.model.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Data
public class RatingPK implements Serializable {

    @Type(type = "uuid-char")
    private UUID usuarioId;

    @Type(type = "uuid-char")
    private UUID productoId;
}
