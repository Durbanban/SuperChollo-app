import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/blocs/blocs.dart';
import 'package:client_super_chollo/widgets/widgets.dart';

class SupermercadoList extends StatefulWidget {
  const SupermercadoList({super.key});
  
  @override
  State<StatefulWidget> createState() => _SupermercadoListState();
}

class _SupermercadoListState extends State<SupermercadoList> {
  
  final _scrollController = ScrollController();

  
  @override
  void initState() {
    super.initState();
    _scrollController.addListener(_onScroll);
    
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<SupermercadoBloc, SupermercadoState>(
      builder: (context, state) {
        switch (state.status) {
          case SupermercadoStatus.failure:            
            return const Center(child: Text("Error al cargar los productos"));
          case SupermercadoStatus.success:
            if(state.supermercados.isEmpty) {
              return const Center(child: Text("No existen productos"),);
            }
            return ListView.builder(
              itemBuilder: (BuildContext context, int index) {
                return index >= state.supermercados.length ? const BottomLoader() : SupermercadoListItem(supermercado: state.supermercados[index]);
              },
              itemCount: state.hasReachedMax ? state.supermercados.length : state.supermercados.length + 1,
              controller: _scrollController,
            );
          case SupermercadoStatus.initial:
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
    if (_isBottom) context.read<SupermercadoBloc>().add(SupermercadoFetched());
  }

  bool get _isBottom {
    if (!_scrollController.hasClients) return false;
    final maxScroll = _scrollController.position.maxScrollExtent;
    final currentScroll = _scrollController.offset;
    return currentScroll >= (maxScroll * 0.9);
  }
}