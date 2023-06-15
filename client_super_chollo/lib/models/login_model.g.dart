// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'login_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

LoginResponse _$LoginResponseFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const [
      'id',
      'username',
      'avatar',
      'fullName',
      'fechaCreado',
      'token',
      'refreshToken',
      'roles'
    ],
  );
  return LoginResponse(
    id: json['id'] as String?,
    username: json['username'] as String?,
    avatar: json['avatar'] as String?,
    fullName: json['fullName'] as String?,
    fechaCreado: json['fechaCreado'] as String?,
    token: json['token'] as String?,
    refreshToken: json['refreshToken'] as String?,
    roles: json['roles'] as String?,
  );
}

Map<String, dynamic> _$LoginResponseToJson(LoginResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'username': instance.username,
      'avatar': instance.avatar,
      'fullName': instance.fullName,
      'fechaCreado': instance.fechaCreado,
      'token': instance.token,
      'refreshToken': instance.refreshToken,
      'roles': instance.roles,
    };

LoginRequest _$LoginRequestFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['username', 'password'],
  );
  return LoginRequest(
    username: json['username'] as String?,
    password: json['password'] as String?,
  );
}

Map<String, dynamic> _$LoginRequestToJson(LoginRequest instance) =>
    <String, dynamic>{
      'username': instance.username,
      'password': instance.password,
    };
