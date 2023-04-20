import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:client_super_chollo/config/locator.dart';
import 'package:client_super_chollo/services/services.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/widgets/widgets.dart';
import 'package:client_super_chollo/blocs/blocs.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocProvider<HomeBloc>(
      create: (context) => HomeBloc()..add(TraerUsuario()),
      child: const HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {

  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<HomeBloc, HomeState>(
      builder: (context, state) {
        switch(state.status) {
          case HomeStatus.initial:
            return Text("ESTADO INICIAL");
          case HomeStatus.success:
            return Scaffold(
          appBar: AppBar(
            title: Text('Inicio'),
          ),
          body: SafeArea(
            minimum: const EdgeInsets.all(16),
            child: Center(
              child: Column(
                children: <Widget>[
                  Text(
                    'Bienvenido, ${state.usuario!.fullName}',
                    style: TextStyle(
                      fontSize: 24
                    ),
                  ),
                  Image.network("http://localhost:6868/file/download/${state.usuario!.avatar}"),
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
                      context.read<AuthenticationBloc>().add(UserLoggedOut());
                    },
                  ),
                  Spacer(),
                ],
              ),
            ),
          ),
        );
          case HomeStatus.failure:
            return Text("Fallo al cargar el usuario");
        }
      },
    );   
    
  }
}
