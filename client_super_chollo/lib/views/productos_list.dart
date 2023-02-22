import 'package:client_super_chollo/widgets/bottom_loader.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/blocs/blocs.dart';
import 'package:client_super_chollo/widgets/widgets.dart';

class ProductoList extends StatefulWidget {
  const ProductoList({super.key});
  
  @override
  State<StatefulWidget> createState() => _ProductoListState();
}

class _ProductoListState extends State<ProductoList> {
  
  final _scrollController = ScrollController();

  
  @override
  void initState() {
    super.initState();
    _scrollController.addListener(_onScroll);
    
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<ProductoBloc, ProductoState>(
      builder: (context, state) {
        switch (state.status) {
          case ProductoStatus.failure:            
            return const Center(child: Text("Error al cargar los productos"));
          case ProductoStatus.success:
            if(state.productos.isEmpty) {
              return const Center(child: Text("No existen productos"),);
            }
            return ListView.builder(
              itemBuilder: (BuildContext context, int index) {
                return index >= state.productos.length ? const BottomLoader() : ProductoListItem(producto: state.productos[index]);
              },
              itemCount: state.hasReachedMax ? state.productos.length : state.productos.length + 1,
              controller: _scrollController,
            );
          case ProductoStatus.initial:
            return const Center(child: CircularProgressIndicator());
        }
      }
    );
  }

  @override
  void dispose() {
    _scrollController
      ..removeListener(_onScroll)
      ..dispose();
    super.dispose();
  }

  void _onScroll() {
    if (_isBottom) context.read<ProductoBloc>().add(ProductoFetched());
  }

  bool get _isBottom {
    if (!_scrollController.hasClients) return false;
    final maxScroll = _scrollController.position.maxScrollExtent;
    final currentScroll = _scrollController.offset;
    return currentScroll >= (maxScroll * 0.9);
  }
}