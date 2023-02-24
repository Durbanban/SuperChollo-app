
import 'dart:convert';
//import 'dart:developer';

import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/services/localstorage_service.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

//import '../exceptions/exceptions.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/repositories/repositories.dart';

abstract class AuthenticationService {
  Future<Usuario?> getCurrentUser();
  Future<Usuario> signInWithEmailAndPassword(String email, String password);
  Future<void> signOut();
  Future<void> signInWithRefreshToken(String refreshToken);
}


@Order(2)
@singleton
class JwtAuthenticationService extends AuthenticationService {

  late AuthenticationRepository _authenticationRepository;
  late LocalStorageService _localStorageService;
  late UsuarioRepository _usuarioRepository;

  JwtAuthenticationService() {
    _authenticationRepository = getIt<AuthenticationRepository>();
    _usuarioRepository = getIt<UsuarioRepository>();
    GetIt.I.getAsync<LocalStorageService>().then((value) => _localStorageService = value);
  }


  @override
  Future<Usuario?> getCurrentUser() async {
    String? token = _localStorageService.getFromDisk("user_token");
    String? refreshToken = _localStorageService.getFromDisk("user_refresh_token");
    if(token != null) {
      UsuarioResponse response = await _usuarioRepository.me();
      return response;
    }else if(token == null && refreshToken != null) {
       print("Token actualizado");
       signInWithRefreshToken(refreshToken);
       UsuarioResponse respuesta = await _usuarioRepository.me();
       return respuesta;

    }
    return null;
  }

  @override
  Future<Usuario> signInWithEmailAndPassword(String email, String password) async {
    LoginResponse response = await _authenticationRepository.doLogin(email, password);
    await _localStorageService.saveToDisk('user_token', response.token);
    await _localStorageService.saveToDisk('user_refresh_token', response.refreshToken);
    return Usuario.fromLoginResponse(response);
  }
  
@override
Future<void> signInWithRefreshToken(String refreshToken) async {
  RefreshTokenResponse respuesta = await _authenticationRepository.doRefreshToken(refreshToken);
  await _localStorageService.deleteFromDisk('user_token');
  await _localStorageService.deleteFromDisk('user_refresh_token');
  await _localStorageService.saveToDisk('user_token', respuesta.token);
  await _localStorageService.saveToDisk('user_refresh_token', respuesta.refreshToken);
}

  @override
  Future<void> signOut() async {
    await _localStorageService.deleteFromDisk("user_token");
    await _localStorageService.deleteFromDisk("user_refresh_token");
  }

}