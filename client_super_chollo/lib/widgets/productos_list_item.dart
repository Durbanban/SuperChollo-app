

import 'package:client_super_chollo/pages/detalles_producto_page.dart';
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
        trailing: ElevatedButton(
          onPressed: () => Navigator.push(context, MaterialPageRoute(builder: (context) {
            return DetallesProductoPage(id: producto.id!);
          }))
          ,
          style: ElevatedButton.styleFrom(
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(50)
            )
          ),
          child: const Icon(Icons.search)
        )
      ),
    );
  }


}