//StateLess = Estatico -> Não pode ser modificado

import 'package:flutter/material.dart';
import 'package:spotted/app/view/cadastro_view.dart';

import '../controller/app_controller.dart';
import '../view/home_view.dart';
import '../view/login_view.dart'; 

class AppWidget extends StatelessWidget {
   
  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
      animation: AppController.instance,
      builder: (context, child){
        return MaterialApp(
          theme: ThemeData(
            primarySwatch: Colors.red,
            
            //Se for true, retorna o dark. 
            //Se for false, retorna o light
            brightness: AppController.instance.isDartTheme ? Brightness.dark : Brightness.light,
          ),
          initialRoute: '/',
          routes: {
            '/': (context) => LoginPage(),
            '/home': (context) => HomePage(),
            '/cadastro': (context) => CadastroPage(),
          },
        );
      },
    );
  }
}