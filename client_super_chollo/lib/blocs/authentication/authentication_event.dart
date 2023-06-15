import 'package:equatable/equatable.dart';

import '../../models/models.dart';

abstract class AuthenticationEvent extends Equatable {
  const AuthenticationEvent();

  @override
  List<Object> get props => [];
}

class AppLoaded extends AuthenticationEvent {}

class UserLoggedIn extends AuthenticationEvent {
  final Usuario usuario;

  UserLoggedIn({required this.usuario});

  @override
  List<Object> get props => [usuario];
}

class UserLoggedOut extends AuthenticationEvent {}


class SessionExpiredEvent extends AuthenticationEvent {}