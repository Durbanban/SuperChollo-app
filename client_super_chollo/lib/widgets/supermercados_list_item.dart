

import 'package:flutter/material.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/rest/rest.dart';

class SupermercadoListItem extends StatelessWidget {

  const SupermercadoListItem({super.key, required this.supermercado});

  final Supermercado supermercado;
  
  @override
  Widget build(BuildContext context) {

    final TextTheme = Theme.of(context).textTheme;
    return Material(
      child: ListTile(
        title: Text('${supermercado.nombre}'),
        subtitle: Text('${supermercado.address}'),
      ),
    );
  }


}