import 'package:client_super_chollo/models/models.dart';
import 'package:client_super_chollo/pages/home_page.dart';
import 'package:flutter/material.dart';

import 'package:client_super_chollo/pages/pages.dart';

class BottomNavigation extends StatefulWidget {
  final Usuario user;

  const BottomNavigation({Key? key, required this.user}) : super(key: key);


  @override
  _BottomNavigationState createState() =>
      _BottomNavigationState();
}

class _BottomNavigationState extends State {
  int _selectedTab = 0; 

  final List<Widget> _pages = [

    const HomePage(key: key, usuario: user),
    const ProductosPage(),
    const ProductosPage(),
    const ProductosPage(),
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
          canvasColor: Colors.blueGrey,
        ),
        child: BottomNavigationBar(
          currentIndex: _selectedTab,
          onTap: (index) => _changeTab(index),
          selectedItemColor: Colors.white,
          unselectedItemColor: Colors.black,
          showSelectedLabels: false,
          showUnselectedLabels: false,
          items: [
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