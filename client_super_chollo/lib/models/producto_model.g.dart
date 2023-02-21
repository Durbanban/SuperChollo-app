// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'producto_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ProductoResponse _$ProductoResponseFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const [
      'contenido',
      'paginasTotales',
      'elementosTotales',
      'paginaAnterior',
      'paginaSiguiente',
      'paginaActual'
    ],
  );
  return ProductoResponse(
    contenido: (json['contenido'] as List<dynamic>?)
        ?.map((e) => Producto.fromJson(e as Map<String, dynamic>))
        .toList(),
    paginasTotales: json['paginasTotales'] as int?,
    elementosTotales: json['elementosTotales'] as int?,
    paginaAnterior: json['paginaAnterior'] as int?,
    paginaSiguiente: json['paginaSiguiente'] as int?,
    paginaActual: json['paginaActual'] as int?,
  );
}

Map<String, dynamic> _$ProductoResponseToJson(ProductoResponse instance) =>
    <String, dynamic>{
      'contenido': instance.contenido?.map((e) => e.toJson()).toList(),
      'paginasTotales': instance.paginasTotales,
      'elementosTotales': instance.elementosTotales,
      'paginaAnterior': instance.paginaAnterior,
      'paginaSiguiente': instance.paginaSiguiente,
      'paginaActual': instance.paginaActual,
    };

Producto _$ProductoFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['id', 'generico', 'nombre', 'precio', 'imagen'],
  );
  return Producto(
    id: json['id'] as String?,
    generico: json['generico'] as String?,
    nombre: json['nombre'] as String?,
    precio: (json['precio'] as num?)?.toDouble(),
    imagen: json['imagen'] as String?,
  );
}

Map<String, dynamic> _$ProductoToJson(Producto instance) => <String, dynamic>{
      'id': instance.id,
      'generico': instance.generico,
      'nombre': instance.nombre,
      'precio': instance.precio,
      'imagen': instance.imagen,
    };
