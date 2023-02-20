// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'supermercado_detalles_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SupermercadoDetails _$SupermercadoDetailsFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['id', 'nombre', 'address', 'seguidores', 'productos'],
  );
  return SupermercadoDetails(
    id: json['id'] as String?,
    nombre: json['nombre'] as String?,
    address: json['address'] as String?,
    seguidores: (json['seguidores'] as List<dynamic>?)
        ?.map((e) => Seguidor.fromJson(e as Map<String, dynamic>))
        .toList(),
    productos: (json['productos'] as List<dynamic>?)
        ?.map((e) => Stock.fromJson(e as Map<String, dynamic>))
        .toList(),
  );
}

Map<String, dynamic> _$SupermercadoDetailsToJson(
        SupermercadoDetails instance) =>
    <String, dynamic>{
      'id': instance.id,
      'nombre': instance.nombre,
      'address': instance.address,
      'seguidores': instance.seguidores?.map((e) => e.toJson()).toList(),
      'productos': instance.productos?.map((e) => e.toJson()).toList(),
    };

Stock _$StockFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['id', 'generico', 'nombre', 'precio'],
  );
  return Stock(
    id: json['id'] as String?,
    generico: json['generico'] as String?,
    nombre: json['nombre'] as String?,
    precio: (json['precio'] as num?)?.toDouble(),
  );
}

Map<String, dynamic> _$StockToJson(Stock instance) => <String, dynamic>{
      'id': instance.id,
      'generico': instance.generico,
      'nombre': instance.nombre,
      'precio': instance.precio,
    };

Seguidor _$SeguidorFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['username', 'avatar', 'fullName'],
  );
  return Seguidor(
    username: json['username'] as String?,
    avatar: json['avatar'] as String?,
    fullName: json['fullName'] as String?,
  );
}

Map<String, dynamic> _$SeguidorToJson(Seguidor instance) => <String, dynamic>{
      'username': instance.username,
      'avatar': instance.avatar,
      'fullName': instance.fullName,
    };
