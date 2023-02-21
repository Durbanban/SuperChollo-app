import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

import 'supermercado_event.dart';
import 'supermercado_state.dart';

class SupermercadoBloc extends Bloc<SupermercadoEvent, SupermercadoState> {
  SupermercadoBloc() : super(SupermercadoInitial()) {
    on<SupermercadoEvent>((event, emit) {
      // TODO: implement event handler
    });
  }
}
