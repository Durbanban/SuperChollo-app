package com.salesianostriana.dam.superchollo.backend.model.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Data
public class RatingPK implements Serializable {

    private UUID usuarioId;

    private UUID productoId;
}
