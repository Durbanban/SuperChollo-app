import 'package:bloc/bloc.dart';
import 'package:client_super_chollo/rest/rest_client.dart';

import 'authentication_event.dart';
import 'authentication_state.dart';
import '../../services/services.dart';

class AuthenticationBloc extends Bloc<AuthenticationEvent, AuthenticationState> {
  final AuthenticationService _authenticationService;

  AuthenticationBloc(AuthenticationService authenticationService)
      : assert(authenticationService != null),
        _authenticationService = authenticationService,
        super(AuthenticationInitial()) {
          on<AppLoaded>(_onAppLoaded);
          on<UserLoggedIn>(_onUserLoggedIn);
          on<UserLoggedOut>(_onUserLoggedOut);
          on<SessionExpiredEvent>(_onSessionExpired);
        }


  _onAppLoaded(
    AppLoaded event,
    Emitter<AuthenticationState> emit,
  ) async {
      emit(AuthenticationLoading());
      try {
        await Future.delayed(Duration(milliseconds: 2500));
        final usuarioActual = await _authenticationService.getCurrentUser();

        if (usuarioActual != null) {
          emit(AuthenticationAuthenticated(usuario: usuarioActual));
        } else {
          emit(AuthenticationNotAuthenticated());
        }
      } on UnauthorizedException catch (e) {
        print(e);
        emit(AuthenticationNotAuthenticated());  
      } on Exception catch (e) {
        emit(AuthenticationFailure(message: 'An unknown error occurred: ${e.toString()}'));
      }
  }

  _onUserLoggedIn(
    UserLoggedIn event,
    Emitter<AuthenticationState> emit,
   ) async {
    emit(AuthenticationAuthenticated(usuario: event.usuario));
  }

  _onUserLoggedOut(
    UserLoggedOut event,
    Emitter<AuthenticationState> emit,
  ) async {
    await _authenticationService.signOut();
    emit(AuthenticationNotAuthenticated());
  }

  _onSessionExpired(
    SessionExpiredEvent event,
    Emitter<AuthenticationState> emit,
  ) async {
    await _authenticationService.signOut();
    emit(SessionExpiredState());
  }

}
