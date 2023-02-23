import 'package:client_super_chollo/models/models.dart';
import 'package:equatable/equatable.dart';


enum HomeStatus {initial, success, failure}
class HomeState extends Equatable {
  const HomeState({
    this.status = HomeStatus.initial,
    this.usuario = null,




  });

  final HomeStatus status;
  final Usuario? usuario;


  HomeState copyWith({HomeStatus? status, Usuario? usuario}) {
    return HomeState(
      status: status ?? this.status,
      usuario: usuario ?? this.usuario,

    );
  }
  
  @override
  List<Object> get props => [status, usuario!];
}

class HomeInitial extends HomeState {}
