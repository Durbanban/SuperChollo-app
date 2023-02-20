// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'categoria_detalles_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CategoriaDetails _$CategoriaDetailsFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['id', 'nombre', 'productos'],
  );
  return CategoriaDetails(
    id: json['id'] as String?,
    nombre: json['nombre'] as String?,
    productos: (json['productos'] as List<dynamic>?)
        ?.map((e) => Catalogo.fromJson(e as Map<String, dynamic>))
        .toList(),
  );
}

Map<String, dynamic> _$CategoriaDetailsToJson(CategoriaDetails instance) =>
    <String, dynamic>{
      'id': instance.id,
      'nombre': instance.nombre,
      'productos': instance.productos?.map((e) => e.toJson()).toList(),
    };

Catalogo _$CatalogoFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['id', 'generico', 'nombre', 'precio'],
  );
  return Catalogo(
    id: json['id'] as String?,
    generico: json['generico'] as String?,
    nombre: json['nombre'] as String?,
    precio: (json['precio'] as num?)?.toDouble(),
  );
}

Map<String, dynamic> _$CatalogoToJson(Catalogo instance) => <String, dynamic>{
      'id': instance.id,
      'generico': instance.generico,
      'nombre': instance.nombre,
      'precio': instance.precio,
    };
