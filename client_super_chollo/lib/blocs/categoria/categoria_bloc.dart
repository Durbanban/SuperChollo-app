import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

import 'categoria_event.dart';
import 'categoria_state.dart';



class CategoriaBloc extends Bloc<CategoriaEvent, CategoriaState> {
  CategoriaBloc() : super(CategoriaInitial()) {
    on<CategoriaEvent>((event, emit) {
      // TODO: implement event handler
    });
  }
}
