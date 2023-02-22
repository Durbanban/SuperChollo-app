import 'package:flutter/material.dart';

class BottomNavigation extends StatefulWidget {
  const BottomNavigation({Key? key}) : super(key: key);

  @override
  _BottomNavigationState createState() =>
      _BottomNavigationState();
}

class _BottomNavigationState extends State {
  int _selectedTab = 0; 

  List _pages = [
    Center(
      child: Text("Inicio"),
    ),
    Center(
      child: Text("Mi perfil"),
    ),
    Center(
      child: Text("Productos"),
    ),
    Center(
      child: Text("Supermercados"),
    ),
    Center(
      child: Text("Categorías"),
    ),
  ];

  _changeTab(int index) {
    setState(() {
      _selectedTab = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
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