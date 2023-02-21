import 'package:client_super_chollo/models/models.dart';
import 'package:equatable/equatable.dart';


enum ProductoStatus {initial, success, failure}
class ProductoState extends Equatable {
  const ProductoState({
    this.status = ProductoStatus.initial,
    this.productos = const <Producto>[],
    this.page = 0,
    this.hasReachedMax = false



  });

  final ProductoStatus status;
  final List<Producto> productos;
  final int page;
  final bool hasReachedMax;

  ProductoState copyWith({ProductoStatus? status, List<Producto>? productos, bool? hasReachedMax, int? page}) {
    return ProductoState(
      status: status ?? this.status,
      productos: productos ?? this.productos,
      page: page ?? this.page,
      hasReachedMax: hasReachedMax ?? this.hasReachedMax
    );
  }

  @override
  String toString() {
    return '''ProductoState { status: $status, hasReachedMax: $hasReachedMax, productos: ${productos.length}, page: $page  }''';
  }
  
  @override
  List<Object> get props => [status, productos, page, hasReachedMax];
}

class ProductoInitial extends ProductoState {}
