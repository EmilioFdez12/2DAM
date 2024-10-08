import 'dart:math';
import 'package:flutter/material.dart';
import 'package:flutter_slot_machine/slot_machine.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Máquina Tragaperras'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  late SlotMachineController _controller;

  @override
  void initState() {
    super.initState();
  }

  void onButtonTap({required int index}) {
    _controller.stop(reelIndex: index);
  }

  void onStart() {
    final index = Random().nextInt(20);
    _controller.start(hitRollItemIndex: index < 5 ? index : null);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromARGB(255, 0, 0, 0), // Fondo negro
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Container(
              width: 350, // Ajusta el ancho del contenedor para hacerlo más grande
              height: 500, // Ajusta la altura del contenedor para hacerlo más grande
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(12),
                boxShadow: [
                  BoxShadow(
                    color: Colors.black.withOpacity(0.3),
                    spreadRadius: 5,
                    blurRadius: 7,
                    offset: Offset(0, 3),
                  ),
                ],
              ),
              child: SlotMachine(
                rollItems: [
                  RollItem(
                    index: 0,
                    child: SizedBox(
                      width: 80, // Ancho de cada imagen en el carrete
                      height: 80, // Alto de cada imagen en el carrete
                      child: Image.asset('assets/images/roll_item_VAR.png'),
                    ),
                  ),
                  RollItem(
                    index: 1,
                    child: SizedBox(
                      width: 80,
                      height: 80,
                      child: Image.asset('assets/images/roll_item_Seven.png'),
                    ),
                  ),
                  RollItem(
                    index: 2,
                    child: SizedBox(
                      width: 80,
                      height: 80,
                      child: Image.asset('assets/images/roll_item_tenis.png'),
                    ),
                  ),
                  RollItem(
                    index: 3,
                    child: SizedBox(
                      width: 80,
                      height: 80,
                      child: Image.asset('assets/images/roll_item_basketball.png'),
                    ),
                  ),
                  RollItem(
                    index: 4,
                    child: SizedBox(
                      width: 80,
                      height: 80,
                      child: Image.asset('assets/images/roll_item_VAR.png'),
                    ),
                  ),
                  RollItem(
                    index: 4,
                    child: SizedBox(
                      width: 80,
                      height: 80,
                      child: Image.asset('assets/images/roll_item_F1.png'),
                    ),
                  ),
                ],
                onCreated: (controller) {
                  _controller = controller;
                },
                onFinished: (resultIndexes) {
                  print('Result: $resultIndexes');
                },
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top: 16),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  SizedBox(
                    width: 72,
                    height: 44,
                    child: TextButton(
                      style: TextButton.styleFrom(
                        backgroundColor: Colors.blue,
                        foregroundColor: Colors.white,
                      ),
                      child: Text('STOP'),
                      onPressed: () => onButtonTap(index: 0),
                    ),
                  ),
                  SizedBox(width: 8),
                  SizedBox(
                    width: 72,
                    height: 44,
                    child: TextButton(
                      style: TextButton.styleFrom(
                        backgroundColor: Colors.blue,
                        foregroundColor: Colors.white,
                      ),
                      child: Text('STOP'),
                      onPressed: () => onButtonTap(index: 1),
                    ),
                  ),
                  SizedBox(width: 8),
                  SizedBox(
                    width: 72,
                    height: 44,
                    child: TextButton(
                      style: TextButton.styleFrom(
                        backgroundColor: Colors.blue,
                        foregroundColor: Colors.white,
                      ),
                      child: Text('STOP'),
                      onPressed: () => onButtonTap(index: 2),
                    ),
                  ),
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top: 24),
              child: TextButton(
                style: TextButton.styleFrom(
                  backgroundColor: Colors.green,
                  foregroundColor: Colors.white,
                  padding: EdgeInsets.symmetric(horizontal: 32, vertical: 16),
                ),
                child: Text('START'),
                onPressed: () => onStart(),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
