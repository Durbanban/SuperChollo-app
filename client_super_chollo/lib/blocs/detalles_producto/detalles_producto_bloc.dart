import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/services/services.dart';
import 'package:equatable/equatable.dart';

import 'detalles_producto_event.dart';
import 'detalles_producto_state.dart';

class DetallesProductoBloc extends Bloc<DetallesProductoEvent, DetallesProductoState> {

  late final ProductoService _productoService;

  DetallesProductoBloc() : super(DetallesProductoInitial()) {
    
    _productoService = getIt<ProductoService>();

    on<fetchDetailsProducto>(onFetchDetailsProducto);
  }

  FutureOr<void> onFetchDetailsProducto(fetchDetailsProducto evento, Emitter<DetallesProductoState> emitter) async {

    try {
      if(state.status == DetallesProductoStatus.initial) {
        final respuesta = await _productoService.getById(evento.productoId);
        return emitter(state.copyWith(
          status: DetallesProductoStatus.success,
          producto: respuesta
        ));

      }
    }catch(_) {
      emitter(state.copyWith(status: DetallesProductoStatus.failure));
    }

  }
}
