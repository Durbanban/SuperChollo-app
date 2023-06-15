import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:client_super_chollo/models/apierror_model.dart';
import 'package:flutter_form_bloc/flutter_form_bloc.dart';

import '../../config/locator.dart';
import '../../models/usuario_request_model.dart';
import '../../services/authentication_service.dart';

class RegisterFormBloc extends FormBloc<String, String> {

  final AuthenticationService _authenticationService = getIt<JwtAuthenticationService>();



  final username = TextFieldBloc(
    validators: [
      FieldBlocValidators.required,
    ],
  );

  final password = TextFieldBloc(
    validators: [
      FieldBlocValidators.required,
    ],
  );

  final verifyPassword = TextFieldBloc(
    validators: [
      FieldBlocValidators.required,
    ],
  );

  final fullName = TextFieldBloc(
    validators: [
      FieldBlocValidators.required,
    ],
  );

  File? file;

  Validator<String> _verifyPassword(
    TextFieldBloc passwordBloc,
  ) {
    return (String? verifyPassword) {
      if (verifyPassword == passwordBloc.value) {
        return null;
      }
      return 'Las contraseñas no coinciden';
    };
  }

  RegisterFormBloc() {
    addFieldBlocs(
      fieldBlocs: [username, password, verifyPassword, fullName],
    );

    verifyPassword
      ..addValidators([_verifyPassword(password)])
      ..subscribeToFieldBlocs([password]);
  }

  @override
  void onSubmitting() async {
    UsuarioRequest body = UsuarioRequest(
      username: username.value,
      password: password.value,
      verifyPassword: verifyPassword.value,
      fullName: fullName.value
    );

    var response = await _authenticationService.registrarUsuario(body, file!);
    if (response.statusCode == 400) {
      ApiError error = ApiError.fromJson(jsonDecode(response.body));
      print(error);
      error.subErrors?.forEach((subError) {
        switch (subError.field) {
          case "username":
            username.addFieldError(subError.message.toString());
            break;
          case "password":
            password.addFieldError(subError.message.toString());
            break;
          case "verifyPassword":
            verifyPassword.addFieldError(subError.message.toString());
            break;
          case "fullName":
            fullName.addFieldError(subError.message.toString());
        }
      });
      await Future<void>.delayed(const Duration(seconds: 1));
      emitFailure(failureResponse: 'Fallo al registrarse');
    } else {
      await Future<void>.delayed(const Duration(seconds: 1));
      emitSuccess(successResponse: "");
    }
  }
}
