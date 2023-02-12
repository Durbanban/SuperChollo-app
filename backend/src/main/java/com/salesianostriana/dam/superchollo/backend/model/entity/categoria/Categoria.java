package com.salesianostriana.dam.superchollo.backend.model.entity.categoria;

import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.validation.annotation.ValidCategoria;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Categoria {

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

    @ValidCategoria
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categoria categoria = (Categoria) obj;
        return id.equals(categoria.id);
    }

    @Override
    public String toString() {
        return "Categoría {" +
                "id = " + id + ", " +
                "Nombre = " + nombre + "}";
    }
}
