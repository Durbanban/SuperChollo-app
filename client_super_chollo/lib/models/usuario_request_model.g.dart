// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'usuario_request_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UsuarioRequest _$UsuarioRequestFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['username', 'password', 'verifyPassword', 'fullName'],
  );
  return UsuarioRequest(
    username: json['username'] as String?,
    password: json['password'] as String?,
    verifyPassword: json['verifyPassword'] as String?,
    fullName: json['fullName'] as String?,
  );
}

Map<String, dynamic> _$UsuarioRequestToJson(UsuarioRequest instance) =>
    <String, dynamic>{
      'username': instance.username,
      'password': instance.password,
      'verifyPassword': instance.verifyPassword,
      'fullName': instance.fullName,
    };
