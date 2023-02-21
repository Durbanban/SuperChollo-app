
import 'package:json_annotation/json_annotation.dart';

import 'login_model.dart';

part 'usuario_model.g.dart';

class Usuario {
    Usuario({
        this.id,
        this.username,
        this.avatar,
        this.fullName,
        this.fechaCreado,
    });

    String? id;
    String? username;
    String? avatar;
    String? fullName;
    String? fechaCreado;

    Usuario.fromLoginResponse(LoginResponse response) {

      this.id = response.id;
      this.username = response.username;
      this.avatar = response.avatar;
      this.fullName = response.fullName;
      this.fechaCreado = response.fechaCreado;

    }

    
}

@JsonSerializable(disallowUnrecognizedKeys: true)
class UsuarioResponse extends Usuario {

  UsuarioResponse(id, username, avatar, fullName, fechaCreado) : super(id: id, username: username, fullName: fullName, fechaCreado: fechaCreado);

  factory UsuarioResponse.fromJson(Map<String, dynamic> data) => _$UsuarioResponseFromJson(data);

  Map<String, dynamic> toJson() => _$UsuarioResponseToJson(this);

}
