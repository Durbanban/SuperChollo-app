import 'package:bloc/bloc.dart';
import 'package:client_super_chollo/rest/rest_client.dart';
import 'login_event.dart';
import 'login_state.dart';
import '../authentication/authentication.dart';
import '../../services/services.dart';

class LoginBloc extends Bloc<LoginEvent, LoginState> {
  final AuthenticationBloc _authenticationBloc;
  final AuthenticationService _authenticationService;

  LoginBloc(AuthenticationBloc authenticationBloc, AuthenticationService authenticationService)
      : assert(authenticationBloc != null),
        assert(authenticationService != null),
        _authenticationBloc = authenticationBloc,
        _authenticationService = authenticationService,
        super(LoginInitial()) {
          on<LoginInWithEmailButtonPressed>(__onLogingInWithEmailButtonPressed);
        }


  __onLogingInWithEmailButtonPressed(
    LoginInWithEmailButtonPressed event,
    Emitter<LoginState> emit,
  ) async {
    emit(LoginLoading());
    try {
      final usuario = await _authenticationService.signInWithEmailAndPassword(event.email, event.password);
      if (usuario != null) {
        _authenticationBloc.add(UserLoggedIn(usuario: usuario));
        emit(LoginSuccess());
      } else {
        emit(LoginFailure(error: 'Something very weird just happened'));
      }
    } on AuthenticationException catch (e) {
      emit(LoginFailure(error: e.message));
    } on CustomException catch (err) {
      emit(LoginFailure(error:'An unknown error occurred ${err.message}'));
    }
  }

  
}
