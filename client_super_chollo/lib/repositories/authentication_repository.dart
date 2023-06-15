import 'dart:convert';
import 'dart:io';

import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/services/services.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

import 'package:client_super_chollo/rest/rest.dart';

import 'package:http/http.dart' as http;

import 'package:http_parser/http_parser.dart';

@Order(-1)
@singleton
class AuthenticationRepository {
  late RestClient _client;
  late LocalStorageService _localStorageService;

  AuthenticationRepository() {
    _client = GetIt.I.get<RestClient>();
    GetIt.I
        .getAsync<LocalStorageService>()
        .then((value) => _localStorageService = value);
  }

  Future<dynamic> doLogin(String username, String password) async {
    String url = "/auth/login/";

    var jsonResponse = await _client.post(
        url, LoginRequest(username: username, password: password));
    return LoginResponse.fromJson(jsonDecode(jsonResponse));
  }

  Future<dynamic> register(dynamic body, File file) async {
    final response = _client.register("/auth/register/", body, file);
    return response;
  }

  
}
