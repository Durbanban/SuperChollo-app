

import 'package:json_annotation/json_annotation.dart';

part 'apierror_model.g.dart';

@JsonSerializable(explicitToJson: true, disallowUnrecognizedKeys: true)
class ApiError {
    ApiError({
        this.status,
        this.message,
        this.path,
        this.date,
        this.subErrors,
        this.statusCode,
    });

    String? status;
    String? message;
    String? path;
    String? date;
    List<SubError>? subErrors;
    int? statusCode;

    factory ApiError.fromJson(Map<String, dynamic> data) => _$ApiErrorFromJson(data);

    Map<String, dynamic> toJson() => _$ApiErrorToJson(this);

}

@JsonSerializable(disallowUnrecognizedKeys: true)
class SubError {
    SubError({
        this.object,
        this.message,
        this.field,
        this.rejectedValue,
    });

    String? object;
    String? message;
    String? field;
    String? rejectedValue;

    factory SubError.fromJson(Map<String, dynamic> data) => _$SubErrorFromJson(data);

    Map<String, dynamic> toJson() => _$SubErrorToJson(this);

}
