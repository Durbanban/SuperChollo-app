part of 'home_bloc.dart';



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
  List<Object> get props => [];
}

class HomeInitial extends HomeState {}
