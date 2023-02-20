import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

part 'supermercado_event.dart';
part 'supermercado_state.dart';

class SupermercadoBloc extends Bloc<SupermercadoEvent, SupermercadoState> {
  SupermercadoBloc() : super(SupermercadoInitial()) {
    on<SupermercadoEvent>((event, emit) {
      // TODO: implement event handler
    });
  }
}
