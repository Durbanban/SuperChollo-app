// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'producto_detalles.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ProductoDetails _$ProductoDetailsFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const [
      'id',
      'generico',
      'nombre',
      'precio',
      'imagen',
      'categoria',
      'autor',
      'supermercados'
    ],
  );
  return ProductoDetails(
    id: json['id'] as String?,
    generico: json['generico'] as String?,
    nombre: json['nombre'] as String?,
    precio: (json['precio'] as num?)?.toDouble(),
    imagen: json['imagen'] as String?,
    categoria: json['categoria'] as String?,
    autor: json['autor'] as String?,
    supermercados: (json['supermercados'] as List<dynamic>?)
        ?.map((e) => Pertenece.fromJson(e as Map<String, dynamic>))
        .toList(),
  );
}

Map<String, dynamic> _$ProductoDetailsToJson(ProductoDetails instance) =>
    <String, dynamic>{
      'id': instance.id,
      'generico': instance.generico,
      'nombre': instance.nombre,
      'precio': instance.precio,
      'imagen': instance.imagen,
      'categoria': instance.categoria,
      'autor': instance.autor,
      'supermercados': instance.supermercados?.map((e) => e.toJson()).toList(),
    };

Pertenece _$PerteneceFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['id', 'nombre', 'address'],
  );
  return Pertenece(
    id: json['id'] as String?,
    nombre: json['nombre'] as String?,
    address: json['address'] as String?,
  );
}

Map<String, dynamic> _$PerteneceToJson(Pertenece instance) => <String, dynamic>{
      'id': instance.id,
      'nombre': instance.nombre,
      'address': instance.address,
    };
