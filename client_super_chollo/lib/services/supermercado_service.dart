
import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/repositories/repositories.dart';
import 'package:client_super_chollo/services/localstorage_service.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

@Order(2)
@singleton
class SupermercadoService {

  late LocalStorageService _localStorageService;
  late SupermercadoRepository _supermercadoRepository;

  SupermercadoService() {
    _supermercadoRepository = getIt<SupermercadoRepository>();
    GetIt.I.getAsync<LocalStorageService>().then((value) => _localStorageService = value);
  }

  Future<SupermercadoResponse> getAllSupermercados(int page) async {

    String? token = _localStorageService.getFromDisk("user_token");
    if(token != null) {
      SupermercadoResponse? respuesta = await _supermercadoRepository.getAllSupermercados(page);
      return respuesta;

    }else {
      throw Exception("No se pudo cargar los supermercados");
    }

  }

  Future<SupermercadoDetails> getById(String id) async {

    String? token = _localStorageService.getFromDisk("user_token");
    if(token != null) {
      SupermercadoDetails? respuesta = await _supermercadoRepository.getById(id);
      return respuesta;
    }else {
      throw Exception("No se encontró el producto");
    }

  }

}