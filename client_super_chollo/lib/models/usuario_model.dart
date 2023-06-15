
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
        this.roles,
    });

    String? id;
    String? username;
    String? avatar;
    String? fullName;
    String? fechaCreado;
    String? roles;

    Usuario.fromLoginResponse(LoginResponse response) {

      this.id = response.id;
      this.username = response.username;
      this.avatar = response.avatar;
      this.fullName = response.fullName;
      this.fechaCreado = response.fechaCreado;
      this.roles = response.roles;

    }

    
}

@JsonSerializable(disallowUnrecognizedKeys: true)
class UsuarioResponse extends Usuario {

  UsuarioResponse(id, username, avatar, fullName, fechaCreado, roles) : super(id: id, username: username, avatar: avatar, fullName: fullName, fechaCreado: fechaCreado, roles: roles);

  factory UsuarioResponse.fromJson(Map<String, dynamic> data) => _$UsuarioResponseFromJson(data);

  Map<String, dynamic> toJson() => _$UsuarioResponseToJson(this);

}
