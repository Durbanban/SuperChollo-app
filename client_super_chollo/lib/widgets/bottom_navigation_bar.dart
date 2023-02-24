import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/pages/home_page.dart';
import 'package:client_super_chollo/pages/supermercado_page.dart';
import 'package:flutter/material.dart';

import 'package:client_super_chollo/pages/pages.dart';

class BottomNavigation extends StatefulWidget {

  const BottomNavigation({Key? key}) : super(key: key);


  @override
  _BottomNavigationState createState() =>
      _BottomNavigationState();
}

class _BottomNavigationState extends State {
  int _selectedTab = 0; 

  final List<Widget> _pages = [

    const HomeScreen(),
    const ProductosPage(),
    const ProductosPage(),
    const SupermercadoPage(),
    const ProductosPage()
    
  ];

  _changeTab(int index) {
    setState(() {
      _selectedTab = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _pages[_selectedTab],
      bottomNavigationBar: Theme(
        data: ThemeData(
          canvasColor: Colors.red,
        ),
        child: BottomNavigationBar(
          currentIndex: _selectedTab,
          onTap: (index) => _changeTab(index),
          selectedItemColor: Colors.white,
          unselectedItemColor: Colors.black,
          showSelectedLabels: false,
          showUnselectedLabels: false,
          items: const [
            BottomNavigationBarItem(icon: Icon(Icons.home), label: "Inicio"),
            BottomNavigationBarItem(icon: Icon(Icons.person), label: "Mi perfil"),
            BottomNavigationBarItem(
                icon: Icon(Icons.price_check_outlined), label: "Productos"),
            BottomNavigationBarItem(
                icon: Icon(Icons.shopping_cart), label: "Supermercados"),
            BottomNavigationBarItem(
                icon: Icon(Icons.category), label: "Categorías"),
          ],
        ),
      )
    );
  }
}