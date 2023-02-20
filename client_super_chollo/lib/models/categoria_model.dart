

import 'package:json_annotation/json_annotation.dart';

part 'categoria_model.g.dart';

@JsonSerializable(explicitToJson: true, disallowUnrecognizedKeys: true)
class CategoriaResponse {
    CategoriaResponse({
        this.contenido,
        this.paginasTotales,
        this.elementosTotales,
        this.paginaAnterior,
        this.paginaSiguiente,
    });

    List<Categoria>? contenido;
    int? paginasTotales;
    int? elementosTotales;
    int? paginaAnterior;
    int? paginaSiguiente;

    factory CategoriaResponse.fromJson(Map<String, dynamic> data) => _$CategoriaResponseFromJson(data);

    Map<String, dynamic> toJson() => _$CategoriaResponseToJson(this);
}

@JsonSerializable(disallowUnrecognizedKeys: true)
class Categoria {
    Categoria({
        this.id,
        this.nombre,
    });

    String? id;
    String? nombre;

    factory Categoria.fromJson(Map<String, dynamic> data) => _$CategoriaFromJson(data);

    Map<String, dynamic> toJson() => _$CategoriaToJson(this);
}
