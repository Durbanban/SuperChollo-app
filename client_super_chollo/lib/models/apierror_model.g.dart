// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'apierror_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ApiError _$ApiErrorFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const [
      'status',
      'message',
      'path',
      'date',
      'subErrors',
      'statusCode'
    ],
  );
  return ApiError(
    status: json['status'] as String?,
    message: json['message'] as String?,
    path: json['path'] as String?,
    date: json['date'] as String?,
    subErrors: (json['subErrors'] as List<dynamic>?)
        ?.map((e) => SubError.fromJson(e as Map<String, dynamic>))
        .toList(),
    statusCode: json['statusCode'] as int?,
  );
}

Map<String, dynamic> _$ApiErrorToJson(ApiError instance) => <String, dynamic>{
      'status': instance.status,
      'message': instance.message,
      'path': instance.path,
      'date': instance.date,
      'subErrors': instance.subErrors?.map((e) => e.toJson()).toList(),
      'statusCode': instance.statusCode,
    };

SubError _$SubErrorFromJson(Map<String, dynamic> json) {
  $checkKeys(
    json,
    allowedKeys: const ['object', 'message', 'field', 'rejectedValue'],
  );
  return SubError(
    object: json['object'] as String?,
    message: json['message'] as String?,
    field: json['field'] as String?,
    rejectedValue: json['rejectedValue'] as String?,
  );
}

Map<String, dynamic> _$SubErrorToJson(SubError instance) => <String, dynamic>{
      'object': instance.object,
      'message': instance.message,
      'field': instance.field,
      'rejectedValue': instance.rejectedValue,
    };
