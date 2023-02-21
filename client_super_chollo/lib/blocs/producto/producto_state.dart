import 'package:equatable/equatable.dart';

abstract class ProductoState extends Equatable {
  const ProductoState();
  
  @override
  List<Object> get props => [];
}

class ProductoInitial extends ProductoState {}