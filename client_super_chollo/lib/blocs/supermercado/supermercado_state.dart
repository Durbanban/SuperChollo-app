part of 'supermercado_bloc.dart';

abstract class SupermercadoState extends Equatable {
  const SupermercadoState();
  
  @override
  List<Object> get props => [];
}

class SupermercadoInitial extends SupermercadoState {}
