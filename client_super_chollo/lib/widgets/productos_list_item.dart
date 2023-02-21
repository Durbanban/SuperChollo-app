

import 'package:flutter/material.dart';
import 'package:client_super_chollo/models/models.dart';

class ProductoListItem extends StatelessWidget {

  const ProductoListItem({super.key, required this.producto});

  final Producto producto;
  
  @override
  Widget build(BuildContext context) {
    final TextTheme = Theme.of(context).textTheme;
    return Material(
      child: ListTile(
        leading: Image.network("http://localhost:8080/file/download/${producto.imagen}"),
        title: Text('${producto.nombre}'),
        subtitle: Text('${producto.generico}'),
        trailing: Text('${producto.precio}'),
      ),
    );
  }


}