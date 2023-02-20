import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

part 'categoria_event.dart';
part 'categoria_state.dart';

class CategoriaBloc extends Bloc<CategoriaEvent, CategoriaState> {
  CategoriaBloc() : super(CategoriaInitial()) {
    on<CategoriaEvent>((event, emit) {
      // TODO: implement event handler
    });
  }
}
