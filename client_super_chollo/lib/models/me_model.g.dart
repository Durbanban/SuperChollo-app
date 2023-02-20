// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'me_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Me _$MeFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['id', 'username', 'avatar', 'fullName', 'fechaCreado'],
  );
  return Me(
    id: json['id'] as String?,
    username: json['username'] as String?,
    avatar: json['avatar'] as String?,
    fullName: json['fullName'] as String?,
    fechaCreado: json['fechaCreado'] as String?,
  );
}

Map<String, dynamic> _$MeToJson(Me instance) => <String, dynamic>{
      'id': instance.id,
      'username': instance.username,
      'avatar': instance.avatar,
      'fullName': instance.fullName,
      'fechaCreado': instance.fechaCreado,
    };
