package com.salesianostriana.dam.superchollo.backend.model.entity;

import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
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
@NamedEntityGraphs(value = {
        @NamedEntityGraph(
                name = "supermercado-con-usuarios",
                attributeNodes = {
                        @NamedAttributeNode("seguidores")
                }
        )
})
public class Supermercado {

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

    private String nombre;

    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "catalogo",
            joinColumns = @JoinColumn(
                    name = "supermercado_id",
                    foreignKey = @ForeignKey(name = "FK_CATALOGO_SUPERMERCADO")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "producto_id",
                    foreignKey = @ForeignKey(name = "FK_CATALOGO_PRODUCTO")
            )
    )
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    @ManyToMany(mappedBy = "favoritos", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Usuario> seguidores = new ArrayList<>();

    public void addProducto(Producto producto) {
        producto.getSupermercados().add(this);
        this.productos.add(producto);
    }

    public void removeProducto(Producto producto) {
        producto.getSupermercados().add(this);
        this.productos.remove(producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Supermercado supermercado = (Supermercado) obj;
        return id.equals(supermercado.id);
    }

    @Override
    public String toString() {
        return "Supermercado{ " +
                "id = " + id + ", " +
                "Nombre = " + nombre + ", " +
                "Dirección = " + address + "}";
    }
}
