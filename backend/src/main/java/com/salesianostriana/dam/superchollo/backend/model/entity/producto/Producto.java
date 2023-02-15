package com.salesianostriana.dam.superchollo.backend.model.entity.producto;

import com.salesianostriana.dam.superchollo.backend.model.entity.Rating;
import com.salesianostriana.dam.superchollo.backend.model.entity.Supermercado;
import com.salesianostriana.dam.superchollo.backend.model.entity.categoria.Categoria;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Producto {

    @Id
    @Type(type = "uuid-char")
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

    /*@Convert(converter = EnumSetProductoGenericoConverter.class)
    private EnumSet<ProductoNombreGenerico> genericos;*/

    private String generico;

    private String nombre;

    private double precio;

    private String imagen;

    @ManyToOne
    @JoinColumn(name = "categoria_id", foreignKey = @ForeignKey(name = "FK_PRODUCTO_CATEGORIA"))
    private Categoria categoria;

    @ManyToMany(mappedBy = "productos", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Supermercado> supermercados = new ArrayList<>();

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Rating> valoraciones = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "autor_id", foreignKey = @ForeignKey(name = "FK_PRODUCTO_AUTOR"))
    private Usuario autor;

    public void addRating(Rating rating) {
        rating.setProducto(this);
        this.valoraciones.add(rating);
    }

    public void removeRating(Rating rating) {
        rating.setProducto(null);
        this.valoraciones.remove(rating);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return id.equals(producto.id);
    }

    @Override
    public String toString() {
        return "Producto {" +
                "id = " + id + ", " +
                "Nombre genérico = " + generico + ", " +
                "Nombre = " + nombre + ", " +
                "Precio = " + precio + ", " +
                "Categoria = " + categoria.getNombre() +
                "Autor = " + autor.getUsername() + "}";
    }
}
