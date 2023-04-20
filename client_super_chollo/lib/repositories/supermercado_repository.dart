
import 'dart:convert';

import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/rest/rest.dart';
import 'package:injectable/injectable.dart';

@Order(-1)
@singleton
class SupermercadoRepository {

  late RestAuthenticatedClient _restClient;

  SupermercadoRepository() {
    _restClient = getIt<RestAuthenticatedClient>();
  }

  Future<SupermercadoResponse> getAllSupermercados(int page) async {
    String url = "/supermercado/?page=$page";
    var respuesta = await _restClient.get(url);
    return SupermercadoResponse.fromJson(jsonDecode(respuesta));
  }

  Future<SupermercadoDetails> getById(String id) async {
    String url = "/supermercado/$id";
    var respuesta = await _restClient.get(url);
    return SupermercadoDetails.fromJson(jsonDecode(respuesta));

  }

}