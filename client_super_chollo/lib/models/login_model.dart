
import 'package:json_annotation/json_annotation.dart';

part 'login_model.g.dart';


@JsonSerializable(disallowUnrecognizedKeys: true)
class LoginResponse {
    LoginResponse({
        this.id,
        this.username,
        this.avatar,
        this.fullName,
        this.fechaCreado,
        this.token,
        this.refreshToken,
        this.roles,
    });

    String? id;
    String? username;
    String? avatar;
    String? fullName;
    String? fechaCreado;
    String? token;
    String? refreshToken;
    String? roles;

    factory LoginResponse.fromJson(Map<String, dynamic> data) => _$LoginResponseFromJson(data);

    Map<String, dynamic> toJson() => _$LoginResponseToJson(this);
}


@JsonSerializable(disallowUnrecognizedKeys: true)
class LoginRequest {
    LoginRequest({
        this.username,
        this.password,
    });

    String? username;
    String? password;

    factory LoginRequest.fromJson(Map<String, dynamic> data) => _$LoginRequestFromJson(data);

    Map<String, dynamic> toJson() => _$LoginRequestToJson(this);
}



