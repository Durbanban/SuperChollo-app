

import 'package:flutter/material.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/rest/rest.dart';

class ProductoListItem extends StatelessWidget {

  const ProductoListItem({super.key, required this.producto});

  final Producto producto;
  
  @override
  Widget build(BuildContext context) {

    final TextTheme = Theme.of(context).textTheme;
    return Material(
      child: ListTile(
        leading: Image.network("${ApiConstants.baseUrl}/file/download/${producto.imagen}",
         errorBuilder: (context, error, stackTrace) => Image.network("https://dinahosting.com/blog/cont/uploads/2021/03/error-404.jpg"),
        ),
        title: Text('${producto.nombre}'),
        subtitle: Text('${producto.precio} €'),
        trailing: Text('${producto.generico}'),
      ),
    );
  }


}