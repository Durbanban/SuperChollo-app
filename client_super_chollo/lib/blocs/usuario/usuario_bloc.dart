import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

import 'usuario_event.dart';
import 'usuario_state.dart';

class UsuarioBloc extends Bloc<UsuarioEvent, UsuarioState> {
  UsuarioBloc() : super(UsuarioInitial()) {
    on<UsuarioEvent>((event, emit) {
      // TODO: implement event handler
    });
  }
}
