import 'package:client_super_chollo/models/models.dart';
import 'package:equatable/equatable.dart';


enum SupermercadoStatus {initial, success, failure}
class SupermercadoState extends Equatable {
  const SupermercadoState({
    this.status = SupermercadoStatus.initial,
    this.supermercados = const <Supermercado>[],
    this.page = 0,
    this.hasReachedMax = false



  });

  final SupermercadoStatus status;
  final List<Supermercado> supermercados;
  final int page;
  final bool hasReachedMax;

  SupermercadoState copyWith({SupermercadoStatus? status, List<Supermercado>? supermercados, bool? hasReachedMax, int? page}) {
    return SupermercadoState(
      status: status ?? this.status,
      supermercados: supermercados ?? this.supermercados,
      page: page ?? this.page,
      hasReachedMax: hasReachedMax ?? this.hasReachedMax
    );
  }

  @override
  String toString() {
    return '''SupermercadoState { status: $status, hasReachedMax: $hasReachedMax, productos: ${supermercados.length}, page: $page  }''';
  }
  
  @override
  List<Object> get props => [status, supermercados, page, hasReachedMax];
}

class SupermercadoInitial extends SupermercadoState {}
