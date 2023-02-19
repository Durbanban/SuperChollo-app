package com.salesianostriana.dam.superchollo.backend.model.dto.rating;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.model.entity.Rating;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingDto {

    @JsonView(View.ProductoView.DetailedProductoView.class)
    private String usuario;

    @JsonView(View.ProductoView.DetailedProductoView.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fecha;

    @JsonView(View.ProductoView.DetailedProductoView.class)
    private int nota;

    public static RatingDto of(Rating rating) {
        return RatingDto
                .builder()
                .usuario(rating.getUsuario().getUsername())
                .fecha(rating.getFechaRating())
                .nota(rating.getNota())
                .build();
    }

}
