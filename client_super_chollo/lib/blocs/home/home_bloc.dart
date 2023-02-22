import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/services/services.dart';
import 'package:equatable/equatable.dart';

part 'home_event.dart';
part 'home_state.dart';

class HomeBloc extends Bloc<HomeEvent, HomeState> {

  late final AuthenticationService _authenticationService;

  HomeBloc() : super(HomeInitial()) {
    _authenticationService = getIt<JwtAuthenticationService>();
    on<TraerUsuario>(fetchUsuario);
  }

  FutureOr<void> fetchUsuario(TraerUsuario event, Emitter<HomeState> emit) async {
    emit(state.copyWith(status: HomeStatus.initial));
    try {

      final respuesta = await _authenticationService.getCurrentUser();
      emit(state.copyWith(status: HomeStatus.success, usuario: respuesta));

    }catch(_) {
      emit(state.copyWith(status: HomeStatus.failure));
    }


  }
}
