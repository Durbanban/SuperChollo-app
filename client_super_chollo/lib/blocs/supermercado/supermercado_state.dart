import 'package:equatable/equatable.dart';

abstract class SupermercadoState extends Equatable {
  const SupermercadoState();
  
  @override
  List<Object> get props => [];
}

class SupermercadoInitial extends SupermercadoState {}
