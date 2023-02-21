import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

import 'producto_event.dart';
import 'producto_state.dart';

class ProductoBloc extends Bloc<ProductoEvent, ProductoState> {
  ProductoBloc() : super(ProductoInitial()) {
    on<ProductoEvent>((event, emit) {
      // TODO: implement event handler
    });
  }
}
