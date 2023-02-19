package com.salesianostriana.dam.superchollo.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.superchollo.backend.model.keys.RatingPK;
import com.salesianostriana.dam.superchollo.backend.model.entity.producto.Producto;
import com.salesianostriana.dam.superchollo.backend.model.entity.usuario.Usuario;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Rating {

    public Rating(Usuario usuario, Producto producto, LocalDateTime fecha, int nota) {
        this.usuario = usuario;
        this.producto = producto;
        fechaRating = fecha;
        this.nota = nota;


        ratingPK.setUsuarioId(this.usuario.getId());
        ratingPK.setProductoId(this.producto.getId());
    }

    @EmbeddedId
    private RatingPK ratingPK = new RatingPK();

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_RATING_USUARIO"))
    private Usuario usuario;

    @ManyToOne()
    @MapsId("productoId")
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "FK_RATING_PRODUCTO"))
    private Producto producto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime fechaRating;

    private int nota;

    @PreRemove
    public void removeUsuario() {
        this.usuario = null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingPK);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Rating r  = (Rating) obj;
        return ratingPK.equals(r.ratingPK);
    }

    @Override
    public String toString() {
        return "Rating { " +
                "ratingPK = " + ratingPK + ", " +
                "fecha = " + fechaRating + ", " +
                "Nota = " + nota + "}";
    }
}
