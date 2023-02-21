

import 'package:json_annotation/json_annotation.dart';

part 'refresh_token_model.g.dart';

@JsonSerializable(disallowUnrecognizedKeys: true)
class RefreshTokenRequest {
    RefreshTokenRequest({
        this.refreshToken,
    });

    String? refreshToken;

    factory RefreshTokenRequest.fromJson(Map<String, dynamic> data) => _$RefreshTokenRequestFromJson(data);

    Map<String, dynamic> toJson() => _$RefreshTokenRequestToJson(this); 

}

@JsonSerializable(disallowUnrecognizedKeys: true)
class RefreshTokenResponse {
    RefreshTokenResponse({
        this.token,
        this.refreshToken,
    });

    String? token;
    String? refreshToken;

    factory RefreshTokenResponse.fromJson(Map<String, dynamic> data) => _$RefreshTokenResponseFromJson(data);

    Map<String, dynamic> toJson() => _$RefreshTokenResponseToJson(this); 
}