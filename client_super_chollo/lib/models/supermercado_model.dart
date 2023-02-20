

import 'package:json_annotation/json_annotation.dart';

part 'supermercado_model.g.dart';

@JsonSerializable(explicitToJson: true, disallowUnrecognizedKeys: true)
class SupermercadoResponse {
    SupermercadoResponse({
        this.contenido,
        this.paginasTotales,
        this.elementosTotales,
        this.paginaAnterior,
        this.paginaSiguiente,
    });

    List<Supermercado>? contenido;
    int? paginasTotales;
    int? elementosTotales;
    int? paginaAnterior;
    int? paginaSiguiente;

    factory SupermercadoResponse.fromJson(Map<String, dynamic> data) => _$SupermercadoResponseFromJson(data);

    Map<String, dynamic> toJson() => _$SupermercadoResponseToJson(this);
}


@JsonSerializable(disallowUnrecognizedKeys: true)
class Supermercado {
    Supermercado({
        this.id,
        this.nombre,
        this.address,
    });

    String? id;
    String? nombre;
    String? address;


    factory Supermercado.fromJson(Map<String, dynamic> data) => _$SupermercadoFromJson(data);

    Map<String, dynamic> toJson() => _$SupermercadoToJson(this);
}
