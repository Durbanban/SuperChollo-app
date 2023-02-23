
import 'dart:convert';

import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/rest/rest.dart';
import 'package:injectable/injectable.dart';

@Order(-1)
@singleton
class ProductoRepository {

  late RestAuthenticatedClient _restClient;

  ProductoRepository() {
    _restClient = getIt<RestAuthenticatedClient>();
  }

  Future<ProductoResponse> getAllProductos(int page) async {
    String url = "/producto/?page=$page";
    var respuesta = await _restClient.get(url);
    return ProductoResponse.fromJson(jsonDecode(respuesta));
  }

  Future<ProductoDetails> getById(String id) async {
    String url = "/producto/$id";
    var respuesta = await _restClient.get(url);
    return ProductoDetails.fromJson(jsonDecode(respuesta));

  }

}