import 'package:flutter/material.dart';

void main(List<String> args) => runApp(const Principal());

class Principal extends StatelessWidget {
  const Principal({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
        title: "Prueba",
        home: Row(
          children: [
            Icon(
              Icons.favorite,
              size: 120,
              color: Colors.lightBlue,
            ),
            Icon(
              Icons.star,
              size: 120,
              color: Colors.yellow,
            ),
            Icon(
              Icons.cancel,
              size: 120,
              color: Colors.red,
            )
          ],
        )

        /* home: Center(
          child: Text(
        "Prueba para cambiar fuentes",
        style: TextStyle(
          color: Colors.pink[300],
          decoration: TextDecoration.none,
          fontSize: 40,
          
        ),
      )), */
        );
  }
}
