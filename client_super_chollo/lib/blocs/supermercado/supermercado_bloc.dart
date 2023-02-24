import 'package:bloc/bloc.dart';
import 'package:client_super_chollo/config/locator.dart';
import 'package:equatable/equatable.dart';
import 'package:client_super_chollo/services/services.dart';
import 'package:bloc_concurrency/bloc_concurrency.dart';
import 'package:stream_transform/stream_transform.dart';



import 'supermercado_event.dart';
import 'supermercado_state.dart';


const throttleDurationSupermarket = Duration(milliseconds: 100);

EventTransformer<E> throttleDroppableSupermarket<E>(Duration duration) {
  return (events, mapper) {
    return droppable<E>().call(events.throttle(duration), mapper);
  };
}

class SupermercadoBloc extends Bloc<SupermercadoEvent, SupermercadoState> {

  late final SupermercadoService _supermercadoService;
  late final AuthenticationService _authenticationService;

  SupermercadoBloc() : super(SupermercadoInitial()) {

    _supermercadoService = getIt<SupermercadoService>();
    _authenticationService = getIt<JwtAuthenticationService>();

    on<SupermercadoFetched>(
      _onSupermercadoFetched,
    );
  }

  Future<void> _onSupermercadoFetched(SupermercadoFetched evento, Emitter<SupermercadoState> emitter) async {
    if(state.hasReachedMax) return;
    try {
      if(state.status == SupermercadoStatus.initial) {
        final respuesta = await _supermercadoService.getAllSupermercados(0);
        return emitter(state.copyWith(
          status: SupermercadoStatus.success,
          supermercados: respuesta.contenido,
          hasReachedMax: respuesta.paginaActual! + 1 >= respuesta.paginasTotales!,
          page: respuesta.paginaActual,
        ));
      }
      final respuesta = await _supermercadoService.getAllSupermercados(state.page + 1);
      emitter(respuesta.contenido!.isEmpty
       ? state.copyWith(hasReachedMax: true)
       : state.copyWith(
        status: SupermercadoStatus.success,
        supermercados: List.of(state.supermercados)..addAll(respuesta.contenido!),
        page: respuesta.paginaActual! + 1,
        hasReachedMax: respuesta.paginaActual! + 1 >= respuesta.paginasTotales!,
       ));
    }catch(_) {
      emitter(state.copyWith(status: SupermercadoStatus.failure));
    }
  }

}
