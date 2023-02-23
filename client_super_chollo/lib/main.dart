import 'package:client_super_chollo/widgets/widgets.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/blocs/blocs.dart';
import 'package:client_super_chollo/services/services.dart';
import 'package:client_super_chollo/pages/pages.dart';



void main() {
  setupAsyncDependencies();
  configureDependencies();

  
    
    runApp(BlocProvider<AuthenticationBloc>(
        create: (context) {
          //GlobalContext.ctx = context;
          final authService = getIt<JwtAuthenticationService>();
          return AuthenticationBloc(authService)..add(AppLoaded());
        },
        child: MyApp(),
      ));

}

class GlobalContext {
  
  static late BuildContext ctx;

}


class MyApp extends StatelessWidget {

  //static late  AuthenticationBloc _authBloc;

  static late MyApp _instance;

  static Route route() {
    return MaterialPageRoute<void>(builder: (context) {
      var authBloc = BlocProvider.of<AuthenticationBloc>(context);
      authBloc..add(SessionExpiredEvent());
      return _instance;
    });
  }

  MyApp() {
    _instance = this;
  }

  @override
  Widget build(BuildContext context) {
    //GlobalContext.ctx = context;
    return MaterialApp(
      title: 'Super Chollo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.red,
        secondaryHeaderColor: Colors.yellow
      ),
      home: BlocBuilder<AuthenticationBloc, AuthenticationState>(
        builder: (context, state) {
          GlobalContext.ctx = context;
          if (state is AuthenticationAuthenticated) {
            // show home page
            return BottomNavigation();
          }
          // otherwise show login page
          return LoginPage();
        },
      ),
    );
  }
}
