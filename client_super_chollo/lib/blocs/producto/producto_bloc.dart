import 'package:bloc/bloc.dart';
import 'package:client_super_chollo/config/locator.dart';
import 'package:equatable/equatable.dart';
import 'package:client_super_chollo/services/services.dart';
import 'package:bloc_concurrency/bloc_concurrency.dart';
import 'package:stream_transform/stream_transform.dart';



import 'producto_event.dart';
import 'producto_state.dart';


const throttleDuration = Duration(milliseconds: 100);

EventTransformer<E> throttleDroppable<E>(Duration duration) {
  return (events, mapper) {
    return droppable<E>().call(events.throttle(duration), mapper);
  };
}

class ProductoBloc extends Bloc<ProductoEvent, ProductoState> {

  late final ProductoService _productoService;

  ProductoBloc() : super(ProductoInitial()) {

    _productoService = getIt<ProductoService>();
    
    on<ProductoFetched>(
      _onProductoFetched,
    );
  }

  Future<void> _onProductoFetched(ProductoFetched evento, Emitter<ProductoState> emitter) async {
    if(state.hasReachedMax) return;
    try {
      if(state.status == ProductoStatus.initial) {
        final respuesta = await _productoService.getAllProductos(0);
        return emitter(state.copyWith(
          status: ProductoStatus.success,
          productos: respuesta.contenido,
          hasReachedMax: respuesta.paginaActual! + 1 >= respuesta.paginasTotales!,
          page: respuesta.paginaActual,
        ));
      }
      final respuesta = await _productoService.getAllProductos(state.page + 1);
      emitter(respuesta.contenido!.isEmpty
       ? state.copyWith(hasReachedMax: true)
       : state.copyWith(
        status: ProductoStatus.success,
        productos: List.of(state.productos)..addAll(respuesta.contenido!),
        page: respuesta.paginaActual! + 1,
        hasReachedMax: false,
       ));
    }catch(_) {
      emitter(state.copyWith(status: ProductoStatus.failure));
    }
  }

}
