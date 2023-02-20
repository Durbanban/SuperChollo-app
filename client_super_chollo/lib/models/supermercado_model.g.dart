// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'supermercado_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SupermercadoResponse _$SupermercadoResponseFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const [
      'contenido',
      'paginasTotales',
      'elementosTotales',
      'paginaAnterior',
      'paginaSiguiente'
    ],
  );
  return SupermercadoResponse(
    contenido: (json['contenido'] as List<dynamic>?)
        ?.map((e) => Supermercado.fromJson(e as Map<String, dynamic>))
        .toList(),
    paginasTotales: json['paginasTotales'] as int?,
    elementosTotales: json['elementosTotales'] as int?,
    paginaAnterior: json['paginaAnterior'] as int?,
    paginaSiguiente: json['paginaSiguiente'] as int?,
  );
}

Map<String, dynamic> _$SupermercadoResponseToJson(
        SupermercadoResponse instance) =>
    <String, dynamic>{
      'contenido': instance.contenido?.map((e) => e.toJson()).toList(),
      'paginasTotales': instance.paginasTotales,
      'elementosTotales': instance.elementosTotales,
      'paginaAnterior': instance.paginaAnterior,
      'paginaSiguiente': instance.paginaSiguiente,
    };

Supermercado _$SupermercadoFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['id', 'nombre', 'address'],
  );
  return Supermercado(
    id: json['id'] as String?,
    nombre: json['nombre'] as String?,
    address: json['address'] as String?,
  );
}

Map<String, dynamic> _$SupermercadoToJson(Supermercado instance) =>
    <String, dynamic>{
      'id': instance.id,
      'nombre': instance.nombre,
      'address': instance.address,
    };
