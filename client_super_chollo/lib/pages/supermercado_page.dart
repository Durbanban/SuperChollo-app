import 'package:client_super_chollo/blocs/blocs.dart';
import 'package:client_super_chollo/views/views.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class SupermercadoPage extends StatelessWidget {

  const SupermercadoPage({super.key});
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Lista de supermercados")),
      body: BlocProvider(
        create: (_) => SupermercadoBloc()..add(SupermercadoFetched()),
        child: const SupermercadoList(),
      ),
    );
  }


}
