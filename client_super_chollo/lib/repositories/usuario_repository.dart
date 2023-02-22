import 'dart:convert';

import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/models/login_model.dart';
import 'package:client_super_chollo/models/usuario_model.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

import 'package:client_super_chollo/rest/rest.dart';

@Order(-10)
@singleton
class UsuarioRepository {

  late RestAuthenticatedClient _client;

  UsuarioRepository() {

    _client = getIt<RestAuthenticatedClient>();
    //_client = GetIt.I.get<RestAuthenticatedClient>();


  }

  Future<dynamic> me() async {

    String url = "/auth/me/";

    var jsonResponse = await _client.get(url);

    return UsuarioResponse.fromJson(jsonDecode(jsonResponse));

  }

}