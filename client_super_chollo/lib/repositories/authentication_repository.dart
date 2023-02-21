

import 'dart:convert';

import 'package:client_super_chollo/models/login_model.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

import 'package:client_super_chollo/rest/rest.dart';

@Order(-1)
@singleton
class AuthenticationRepository {

  late RestClient _client;

  AuthenticationRepository() {
    _client = GetIt.I.get<RestClient>();
  }

  Future<dynamic> doLogin(String username, String password) async {
    String url = "/auth/login/";

    var jsonResponse = await _client.post(url, LoginRequest(username: username, password: password));
    return LoginResponse.fromJson(jsonDecode(jsonResponse));

  }

  Future<dynamic> doLoginWithRefreshToken(String refreshToken) async {

    String url = "/auth/refreshtoken/";

    var respuesta = await _client.post(url, RefreshTokenRequest(refreshToken: refreshToken));
    return LoginResponse.fromJson(jsonDecode(respuesta));

  }





}