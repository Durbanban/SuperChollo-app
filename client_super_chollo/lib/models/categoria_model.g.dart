// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'categoria_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CategoriaResponse _$CategoriaResponseFromJson(Map<String, dynamic> json) {
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
  return CategoriaResponse(
    contenido: (json['contenido'] as List<dynamic>?)
        ?.map((e) => Categoria.fromJson(e as Map<String, dynamic>))
        .toList(),
    paginasTotales: json['paginasTotales'] as int?,
    elementosTotales: json['elementosTotales'] as int?,
    paginaAnterior: json['paginaAnterior'] as int?,
    paginaSiguiente: json['paginaSiguiente'] as int?,
  );
}

Map<String, dynamic> _$CategoriaResponseToJson(CategoriaResponse instance) =>
    <String, dynamic>{
      'contenido': instance.contenido?.map((e) => e.toJson()).toList(),
      'paginasTotales': instance.paginasTotales,
      'elementosTotales': instance.elementosTotales,
      'paginaAnterior': instance.paginaAnterior,
      'paginaSiguiente': instance.paginaSiguiente,
    };

Categoria _$CategoriaFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['id', 'nombre'],
  );
  return Categoria(
    id: json['id'] as String?,
    nombre: json['nombre'] as String?,
  );
}

Map<String, dynamic> _$CategoriaToJson(Categoria instance) => <String, dynamic>{
      'id': instance.id,
      'nombre': instance.nombre,
    };
