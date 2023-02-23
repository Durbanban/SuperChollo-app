import 'package:client_super_chollo/models/models.dart';
import 'package:equatable/equatable.dart';

enum DetallesProductoStatus {initial, success, failure}

class DetallesProductoState extends Equatable {

  DetallesProductoState({
    this.producto = null,
    this.id = '',
    this.status = DetallesProductoStatus.initial
  });

  final String id;
  final DetallesProductoStatus status;
  ProductoDetails? producto;

  DetallesProductoState copyWith({DetallesProductoStatus? status, String? id, ProductoDetails? producto}) {
    return DetallesProductoState(
      status: status ?? this.status,
      id: id ?? this.id,
      producto: producto ?? this.producto

    );
  }
  
  @override
  List<Object> get props => [id, status, producto!];
}

class DetallesProductoInitial extends DetallesProductoState {}
