
import 'dart:convert';
import 'dart:io';

import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/services/localstorage_service.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/repositories/repositories.dart';


abstract class AuthenticationService {
  Future<Usuario?> getCurrentUser();
  Future<Usuario> signInWithEmailAndPassword(String email, String password);
  Future<void> signOut();
  Future<dynamic> registrarUsuario(UsuarioRequest usuario, File file);
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
    UsuarioResponse response = await _usuarioRepository.me();
    return response;
  }

  @override
  Future<Usuario> signInWithEmailAndPassword(String email, String password) async {
    LoginResponse response = await _authenticationRepository.doLogin(email, password);
    await _localStorageService.saveToDisk('user_token', response.token);
    await _localStorageService.saveToDisk('user_refresh_token', response.refreshToken);
    return Usuario.fromLoginResponse(response);
  }
  

  @override
  Future<void> signOut() async {
    await _localStorageService.deleteFromDisk("user_token");
    await _localStorageService.deleteFromDisk("user_refresh_token");
  }

  Future<dynamic> registrarUsuario(UsuarioRequest usuario, File file) async {
    return await _authenticationRepository.register(usuario, file);
  }

  
  

}