import 'package:equatable/equatable.dart';

abstract class UsuarioState extends Equatable {
  const UsuarioState();
  
  @override
  List<Object> get props => [];
}

class UsuarioInitial extends UsuarioState {}
