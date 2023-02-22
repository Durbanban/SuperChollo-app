import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:client_super_chollo/blocs/authentication/authentication.dart';
import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/services/services.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/widgets/widgets.dart';

class HomePage extends StatelessWidget {
  final Usuario usuario;

  const HomePage({super.key, required this.usuario});

  @override
  Widget build(BuildContext context) {
    final authBloc = BlocProvider.of<AuthenticationBloc>(context);
    JwtAuthenticationService service = getIt<JwtAuthenticationService>();

    return Scaffold(
      appBar: AppBar(
        title: Text('Home Page'),
      ),
      body: SafeArea(
        minimum: const EdgeInsets.all(16),
        child: Center(
          child: Column(
            children: <Widget>[
              Text(
                'Bienvenido, ${usuario.fullName}',
                style: TextStyle(
                  fontSize: 24
                ),
              ),
              Image.network("http://localhost:8080/file/download/${usuario.avatar}"),
              const SizedBox(
                height: 12,
              ),
              ElevatedButton(
                //textColor: Theme.of(context).primaryColor,
                /*style: TextButton.styleFrom(
                  primary: Theme.of(context).primaryColor,
                ),*/
                child: Text('Logout'),
                onPressed: (){
                  authBloc.add(UserLoggedOut());
                },
              ),
              ElevatedButton(onPressed: () async {
                await service.getCurrentUser();
              }
              , child: Text('Probar')
              )
            ],
          ),
        ),
      ),
      bottomNavigationBar: BottomNavigation(),
    );
  }
}
