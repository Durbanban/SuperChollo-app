

import 'package:client_super_chollo/blocs/blocs.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';


class ProductoDetailsView extends StatelessWidget {
  const ProductoDetailsView({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<DetallesProductoBloc, DetallesProductoState>(

      builder: (context, state) {
        switch(state.status) {
          
          case DetallesProductoStatus.initial:
            return const Center(child: CircularProgressIndicator());
          case DetallesProductoStatus.success:
            return Card(
              child: Text("${state.producto!.autor}, ${state.producto!.categoria}"),
              
            );         
          case DetallesProductoStatus.failure:
            return const Center(
              child: Text("No se pudo cargar el producto"),
            );
        }
      }
    );
  }
}