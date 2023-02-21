import 'package:equatable/equatable.dart';


abstract class CategoriaState extends Equatable {
  const CategoriaState();
  
  @override
  List<Object> get props => [];
}

class CategoriaInitial extends CategoriaState {}
