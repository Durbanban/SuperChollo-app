

import 'dart:convert';
import 'dart:io';

import 'package:client_super_chollo/models/refresh_token_model.dart';
import 'package:flutter/material.dart';
import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/main.dart';
import 'package:client_super_chollo/services/localstorage_service.dart';
import 'package:get_it/get_it.dart';
import 'package:http_interceptor/http_interceptor.dart';
import 'package:injectable/injectable.dart';
import 'package:http/http.dart' as http;


class ApiConstants {

  static String baseUrl = "http://localhost:8080";
  //static String baseUrl = "http://10.0.2.2:8080";
  


}

class HeadersApiInterceptor implements InterceptorContract {
  @override
  Future<RequestData> interceptRequest({required RequestData data}) async {
    try {
      data.headers["Content-Type"] = "application/json";
      data.headers["Accept"] = "application/json";
    } catch (e) {
      print(e);
    }
    return data;
  }

  @override
  Future<ResponseData> interceptResponse({required ResponseData data}) async => data;
}

@Order(-10)
@singleton
class RestClient {

  var _httpClient;

  RestClient() {
    _httpClient = InterceptedClient.build(interceptors: [HeadersApiInterceptor()]);
  }

  RestClient.withInterceptors(List<InterceptorContract> interceptors) {
    // El interceptor con los encabezados sobre JSON se añade si no está incluido en la lista
    if (interceptors.where((element) => element is HeadersApiInterceptor).isEmpty) interceptors..add(HeadersApiInterceptor());
    _httpClient = InterceptedClient.build(interceptors: interceptors, retryPolicy: ExpiredTokenRetryPolicy());
  }

  //final _httpClient = http.Client();
  


  Future<dynamic> get(String url) async {

    try {

        Uri uri = Uri.parse(ApiConstants.baseUrl + url);

        final response = await _httpClient.get(uri);
        var responseJson = _response(response);
        return responseJson;


    } on SocketException catch(ex) {
      throw FetchDataException('No internet connection: ${ex.message}');
    }

  }

  Future<dynamic> post(String url, dynamic body) async {

      try {

        Uri uri = Uri.parse(ApiConstants.baseUrl + url);

        final response = await _httpClient.post(uri, body: jsonEncode(body));
        var responseJson = _response(response);
        return responseJson;

    /*} on SocketException catch(ex) {
      throw FetchDataException('No internet connection: ${ex.message}');
    }*/
    } on Exception catch(ex) {
      throw ex;
    }

  }


  dynamic _response(http.Response response) {
    switch (response.statusCode) {
      case 200:
      case 201:
        var responseJson = utf8.decode(response.bodyBytes);
        return responseJson;
      case 204:
        return;
      case 400:
        String message = jsonDecode(utf8.decode(response.bodyBytes))['message'];
        throw BadRequestException(message);
      case 401:
        // Así sacamos el mensaje del JSON devuelto por el API
        String message = jsonDecode(utf8.decode(response.bodyBytes))['message'];
        throw AuthenticationException(message);

        // Así devolvemos un mensaje "genérico"
        // throw AuthenticationException("You have entered an invalid username or password");
      case 403:
        throw UnauthorizedException(utf8.decode(response.bodyBytes));
      case 404:
        throw NotFoundException(utf8.decode(response.bodyBytes));
      case 500:
      default:
        throw FetchDataException(
            'Error occurred while Communication with Server with StatusCode : ${response.statusCode}');
    }
  }
}

// ignore_for_file: prefer_typing_uninitialized_variables
// ignore_for_file: annotate_overrides

class CustomException implements Exception {
  final message;
  final _prefix;

  CustomException([this.message, this._prefix]);

  String toString() {
    return "$_prefix$message";
  }
}

class FetchDataException extends CustomException {
  FetchDataException([String? message])
      : super(message, "");
}

class BadRequestException extends CustomException {
  BadRequestException([message]) : super(message, "");
}

class AuthenticationException extends CustomException {
  AuthenticationException([message]) : super(message,"");
}


class UnauthorizedException extends CustomException {
  UnauthorizedException([message]) : super(message,"Su token de refresco ha expirado. Por favor, inicie sesión");
}

class NotFoundException extends CustomException {
  NotFoundException([message]) : super(message, "");
}



class AuthorizationInterceptor implements InterceptorContract {

  late LocalStorageService _localStorageService;

  AuthorizationInterceptor() {
    //_localStorageService = getIt<LocalStorageService>();
    GetIt.I.getAsync<LocalStorageService>().then((value) => _localStorageService = value);

  }


  @override
  Future<RequestData> interceptRequest({required RequestData data}) async {

    try {
      var token = await _localStorageService.getFromDisk("user_token"); 
      data.headers["Authorization"] = "Bearer " + token;  
    } catch(e) {
      print(e);
    }

    return Future.value(data);

  }

  @override
  Future<ResponseData> interceptResponse({required ResponseData data}) async {
    
    if (data.statusCode == 401 || data.statusCode == 403) {
      Future.delayed(Duration(seconds: 1), () {
        Navigator.of(GlobalContext.ctx).push<void>(MyApp.route());
      });
    }
    
    
    return Future.value(data);
  }

}

class ExpiredTokenRetryPolicy extends RetryPolicy {

  late LocalStorageService _localStorageService;

  ExpiredTokenRetryPolicy() {
    GetIt.I.getAsync<LocalStorageService>().then((value) => _localStorageService = value);

  }


  
  //Number of retry
  @override
  int maxRetryAttempts = 2;

  @override
  Future<bool> shouldAttemptRetryOnResponse(ResponseData response) async {
    //This is where we need to update our token on 401 response
    bool continueRetry = false;
    print("El código de la respuesta que le llega al método shouldAttemptRetryOnResponse es: ${response.statusCode} a las ${DateTime.now()}");
    if (response.statusCode == 401) {
      //Refresh your token here. Make refresh token method where you get new token from
      //API and set it to your local data
      
      continueRetry = await refreshToken();
    }
    return continueRetry;
  }

  Future<bool> refreshToken() async {

    String nuevoToken;
    String nuevoRefreshToken;
    RefreshTokenResponse pareja;
    bool isTokenRefreshed = false;
    //TODO Hacer la redirección a la pantalla de login, falla en ese punto, el refresh token se hace bien, pero cuando expira no redirecciona.

    print("refrescando token");
    
    var refreshToken = await _localStorageService.getFromDisk("user_refresh_token");
    ResponseData respuesta = await RestClient().post(("/auth/refreshtoken/"), RefreshTokenRequest(refreshToken: refreshToken));
    print(respuesta.statusCode);
    if(respuesta.statusCode == 403) {
      print("La respuesta que ha llegado de la petición de refresh token es 403");
    }else {
      pareja = RefreshTokenResponse.fromJson(jsonDecode(respuesta as String));
      nuevoToken = pareja.token!;
      nuevoRefreshToken = pareja.refreshToken!;
      await _localStorageService.deleteFromDisk("user_token");
      await _localStorageService.deleteFromDisk("user_refresh_token");
      await _localStorageService.saveToDisk("user_token", nuevoToken);
      await _localStorageService.saveToDisk("user_refresh_token", nuevoRefreshToken);
      isTokenRefreshed = true;
      
    }
    return isTokenRefreshed;

  }

  Future<dynamic> peticionPost(String url, dynamic body) async{
    try {

        Uri uri = Uri.parse(ApiConstants.baseUrl + url);
        final response = await http.Client().post(uri, headers: {'ContentType': 'application/json'}, body: jsonEncode(body));
        return response;

    /*} on SocketException catch(ex) {
      throw FetchDataException('No internet connection: ${ex.message}');
    }*/
    } on Exception catch(ex) {
      throw ex;
    }
  }

}


@Order(-10)
@singleton
class RestAuthenticatedClient extends RestClient {

  RestAuthenticatedClient() : super.withInterceptors(List.of(<InterceptorContract>[AuthorizationInterceptor()]));

}