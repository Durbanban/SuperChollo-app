

import 'package:json_annotation/json_annotation.dart';

part 'categoria_detalles.g.dart';

@JsonSerializable(explicitToJson: true, disallowUnrecognizedKeys: true)
class CategoriaDetails {
    CategoriaDetails({
        this.id,
        this.nombre,
        this.productos,
    });

    String? id;
    String? nombre;
    List<Catalogo>? productos;

    factory CategoriaDetails.fromJson(Map<String, dynamic> data) => _$CategoriaDetailsFromJson(data);

    Map<String, dynamic> toJson() => _$CategoriaDetailsToJson(this);
}

@JsonSerializable(disallowUnrecognizedKeys: true)
class Catalogo {
    Catalogo({
        this.id,
        this.generico,
        this.nombre,
        this.precio,
    });

    String? id;
    String? generico;
    String? nombre;
    double? precio;

    factory Catalogo.fromJson(Map<String, dynamic> data) => _$CatalogoFromJson(data);

    Map<String, dynamic> toJson() => _$CatalogoToJson(this);
}
