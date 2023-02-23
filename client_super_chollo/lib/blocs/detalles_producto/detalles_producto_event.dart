import 'package:equatable/equatable.dart';

abstract class DetallesProductoEvent extends Equatable {
  const DetallesProductoEvent();

  @override
  List<Object> get props => [];
}


class fetchDetailsProducto extends DetallesProductoEvent {

    fetchDetailsProducto(this.productoId);
    String productoId;
    
}
