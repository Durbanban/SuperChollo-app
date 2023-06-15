// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'usuario_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UsuarioResponse _$UsuarioResponseFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const [
      'id',
      'username',
      'avatar',
      'fullName',
      'fechaCreado',
      'roles'
    ],
  );
  return UsuarioResponse(
    json['id'],
    json['username'],
    json['avatar'],
    json['fullName'],
    json['fechaCreado'],
    json['roles'],
  );
}

Map<String, dynamic> _$UsuarioResponseToJson(UsuarioResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'username': instance.username,
      'avatar': instance.avatar,
      'fullName': instance.fullName,
      'fechaCreado': instance.fechaCreado,
      'roles': instance.roles,
    };
