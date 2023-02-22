import 'package:client_super_chollo/blocs/blocs.dart';
import 'package:client_super_chollo/views/views.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class ProductosPage extends StatelessWidget {

  const ProductosPage({super.key});
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Lista de productos")),
      body: BlocProvider(
        create: (_) => ProductoBloc()..add(ProductoFetched()),
        child: const ProductoList(),
      ),
    );
  }


}
