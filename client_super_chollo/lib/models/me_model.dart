
import 'package:json_annotation/json_annotation.dart';

part 'me_model.g.dart';

@JsonSerializable(disallowUnrecognizedKeys: true)
class Me {
    Me({
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

    factory Me.fromJson(Map<String, dynamic> data) => _$MeFromJson(data);

    Map<String, dynamic> toJson() => _$MeToJson(this);
}
