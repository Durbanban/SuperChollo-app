import 'package:client_super_chollo/blocs/blocs.dart';
import 'package:client_super_chollo/views/views.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class DetallesProductoPage extends StatelessWidget {

  DetallesProductoPage({super.key, required this.id});
  String id;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Detalles de Producto")),
      body: BlocProvider(
        create: (_) => DetallesProductoBloc()..add(fetchDetailsProducto(id)),
        child: const ProductoDetailsView(),
      ),
    );
  }


}
