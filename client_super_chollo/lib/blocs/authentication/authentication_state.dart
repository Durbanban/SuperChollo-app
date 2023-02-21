import 'package:equatable/equatable.dart';
import '../../models/models.dart';

abstract class AuthenticationState extends Equatable {
  const AuthenticationState();

  @override
  List<Object> get props => [];

}

class AuthenticationInitial extends AuthenticationState {}

class AuthenticationLoading extends AuthenticationState {}

class AuthenticationNotAuthenticated extends AuthenticationState {}

class AuthenticationAuthenticated extends AuthenticationState {
  final Usuario usuario;

  AuthenticationAuthenticated({required this.usuario});

  @override
  List<Object> get props => [usuario];
}

class AuthenticationFailure extends AuthenticationState {
  final String message;

  AuthenticationFailure({required this.message});

  @override
  List<Object> get props => [message];

}

class SessionExpiredState extends AuthenticationFailure {

  SessionExpiredState() : super(message: 'Session expired. Please login again');

  String get message => super.message;
  
@override
List<Object> get props => [message];

}