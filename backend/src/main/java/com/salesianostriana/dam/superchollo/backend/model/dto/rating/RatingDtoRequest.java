package com.salesianostriana.dam.superchollo.backend.model.dto.rating;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.superchollo.backend.view.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class RatingDtoRequest {

    @JsonView(View.ProductoView.DetailedProductoView.class)
    @Range(min = 0, max = 10, message = "{ratingDtoRequest.range}")
    private int nota;


}
