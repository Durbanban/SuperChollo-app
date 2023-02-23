
import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/repositories/repositories.dart';
import 'package:client_super_chollo/services/localstorage_service.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

@Order(2)
@singleton
class ProductoService {

  late LocalStorageService _localStorageService;
  late ProductoRepository _productoRepository;

  ProductoService() {
    _productoRepository = getIt<ProductoRepository>();
    GetIt.I.getAsync<LocalStorageService>().then((value) => _localStorageService = value);
  }

  Future<ProductoResponse> getAllProductos(int page) async {

    String? token = _localStorageService.getFromDisk("user_token");
    if(token != null) {
      ProductoResponse? respuesta = await _productoRepository.getAllProductos(page);
      return respuesta;

    }else {
      throw Exception("No se pudo cargar los productos");
    }

  }

  Future<ProductoDetails> getById(String id) async {

    String? token = _localStorageService.getFromDisk("user_token");
    if(token != null) {
      ProductoDetails? respuesta = await _productoRepository.getById(id);
      return respuesta;
    }else {
      throw Exception("No se encontró el producto");
    }

  }

}