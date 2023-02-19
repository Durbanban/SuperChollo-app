

import 'package:json_annotation/json_annotation.dart';

part 'producto_detalles.g.dart';


@JsonSerializable(explicitToJson: true, disallowUnrecognizedKeys: true)
class ProductoDetails {
    ProductoDetails({
        this.id,
        this.generico,
        this.nombre,
        this.precio,
        this.imagen,
        this.categoria,
        this.autor,
        this.supermercados,
        this.valoraciones,
    });

    String? id;
    String? generico;
    String? nombre;
    double? precio;
    String? imagen;
    String? categoria;
    String? autor;
    List<Pertenece>? supermercados;
    List<Valoracion>? valoraciones;

    factory ProductoDetails.fromJson(Map<String, dynamic> data) => _$ProductoDetailsFromJson(data);

    Map<String, dynamic> toJson() => _$ProductoDetailsToJson(this);
}

@JsonSerializable(disallowUnrecognizedKeys: true)
class Pertenece {
    Pertenece({
        this.id,
        this.nombre,
        this.address,
    });

    String? id;
    String? nombre;
    String? address;

    factory Pertenece.fromJson(Map<String, dynamic> data) => _$PerteneceFromJson(data);

    Map<String, dynamic> toJson() => _$PerteneceToJson(this);
}

@JsonSerializable(disallowUnrecognizedKeys: true)
class Valoracion {
    Valoracion({
        this.usuario,
        this.fecha,
        this.nota,
    });

    String? usuario;
    String? fecha;
    int? nota;

    factory Valoracion.fromJson(Map<String, dynamic> data) => _$ValoracionFromJson(data);

    Map<String, dynamic> toJson() => _$ValoracionToJson(this);
}

