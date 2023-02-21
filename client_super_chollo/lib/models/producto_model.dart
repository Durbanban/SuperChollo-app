
import 'package:json_annotation/json_annotation.dart';

part 'producto_model.g.dart';

@JsonSerializable(explicitToJson: true, disallowUnrecognizedKeys: true)
class ProductoResponse {
    ProductoResponse({
        this.contenido,
        this.paginasTotales,
        this.elementosTotales,
        this.paginaAnterior,
        this.paginaSiguiente,
        this.paginaActual
    });

    List<Producto>? contenido;
    int? paginasTotales;
    int? elementosTotales;
    int? paginaAnterior;
    int? paginaSiguiente;
    int? paginaActual;

    factory ProductoResponse.fromJson(Map<String, dynamic> data) => _$ProductoResponseFromJson(data);

    Map<String, dynamic> toJson() => _$ProductoResponseToJson(this);
}

@JsonSerializable(disallowUnrecognizedKeys: true)
class Producto {
    Producto({
        this.id,
        this.generico,
        this.nombre,
        this.precio,
        this.imagen
    });

    String? id;
    String? generico;
    String? nombre;
    double? precio;
    String? imagen;

    factory Producto.fromJson(Map<String, dynamic> data) => _$ProductoFromJson(data);

    Map<String, dynamic> toJson() => _$ProductoToJson(this);

    
}
