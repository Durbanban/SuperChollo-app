
import 'package:json_annotation/json_annotation.dart';

part 'usuario_request_model.g.dart';

@JsonSerializable(disallowUnrecognizedKeys: true)
class UsuarioRequest {
    UsuarioRequest({
        this.username,
        this.password,
        this.verifyPassword,
        this.fullName
    });

    String? username;
    String? password;
    String? verifyPassword;
    String? fullName;

    factory UsuarioRequest.fromJson(Map<String, dynamic> data) => _$UsuarioRequestFromJson(data);

    Map<String, dynamic> toJson() => _$UsuarioRequestToJson(this);
  
}