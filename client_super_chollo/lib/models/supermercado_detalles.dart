

import 'package:json_annotation/json_annotation.dart';

part 'supermercado_detalles.g.dart';


@JsonSerializable(explicitToJson: true, disallowUnrecognizedKeys: true)
class SupermercadoDetails {
    SupermercadoDetails({
        this.id,
        this.nombre,
        this.address,
        this.seguidores,
        this.productos,
    });

    String? id;
    String? nombre;
    String? address;
    List<Seguidor>? seguidores;
    List<Stock>? productos;

    factory SupermercadoDetails.fromJson(Map<String, dynamic> data) => _$SupermercadoDetailsFromJson(data);

    Map<String, dynamic> toJson() => _$SupermercadoDetailsToJson(this);
}

@JsonSerializable(disallowUnrecognizedKeys: true)
class Stock {
    Stock({
        this.id,
        this.generico,
        this.nombre,
        this.precio,
    });

    String? id;
    String? generico;
    String? nombre;
    double? precio;

    factory Stock.fromJson(Map<String, dynamic> data) => _$StockFromJson(data);

    Map<String, dynamic> toJson() => _$StockToJson(this);
}


@JsonSerializable(disallowUnrecognizedKeys: true)
class Seguidor {
    Seguidor({
        this.username,
        this.avatar,
        this.fullName,
    });

    String? username;
    String? avatar;
    String? fullName;

    factory Seguidor.fromJson(Map<String, dynamic> data) => _$SeguidorFromJson(data);

    Map<String, dynamic> toJson() => _$SeguidorToJson(this);
}
