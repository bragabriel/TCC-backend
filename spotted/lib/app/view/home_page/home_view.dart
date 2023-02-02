//StatefulWidget = Dinamico -> Pode ser modificado

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../controller/app_controller.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

//Criando uma classe para ser retornada e funcionar como um estado
class HomePageState extends State<HomePage> {
  int counter = 0;

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      drawer: Drawer(
        child: Column(children: [
          UserAccountsDrawerHeader(
              currentAccountPicture:
                  //ClipRRect(
                  ClipOval(
                      //borderRadius: BorderRadius.circular(30),
                      child: Image.asset('assets/images/user.png')),
              accountName: Text('Gabriel Braga'),
              accountEmail: Text('teste@teste.com')),
          ListTile(
              leading: Icon(Icons.home),
              title: Text('Inicio'),
              subtitle: Text('tela de inicio'),
              onTap: () {
                print('home');
              }),
          ListTile(
              leading: Icon(Icons.verified_user),
              title: Text('Perfil'),
              subtitle: Text('seu perfil'),
              onTap: () {
                Navigator.of(context).pushNamed('/perfil');
              }),
          ListTile(
              leading: Icon(Icons.logout),
              title: Text('Logout'),
              subtitle: Text('Finalizar sessão'),
              onTap: () {
                Navigator.of(context).pushReplacementNamed('/');
              }),
        ]),
      ),
      appBar: AppBar(
        title: Text('Home Page'),

        //botões que ficam na lateral direita
        actions: [
          CustomSwitch(),
        ],
      ),
      body: Container(
        width: double.infinity,
        height: double.infinity,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text('Contador: $counter'),
            Container(
              height: 10,
            ),
            Container(
              height: 50,
            ),
            Padding(
              padding: EdgeInsets.all(16.0),
              child: Text('Seção de blablabla'),
            ),
            Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Container(
                    width: 50,
                    height: 50,
                    color: Color.fromARGB(255, 250, 149, 149),
                    child: Text('Crush'),
                  ),
                  Container(
                    width: 50,
                    height: 50,
                    color: Color.fromARGB(255, 250, 149, 149),
                    child: Text('Doces'),
                  ),
                  Container(
                    width: 50,
                    height: 50,
                    color: Color.fromARGB(255, 250, 149, 149),
                    child: Text('Apê'),
                  ),
                ]),
            Padding(
              padding: EdgeInsets.all(16.0),
              child: Text('Seção de vendas de blablabla'),
            ),
            Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Container(
                    width: 50,
                    height: 50,
                    color: Color.fromARGB(255, 250, 149, 149),
                    child: Text('Crush'),
                  ),
                  Container(
                    width: 50,
                    height: 50,
                    color: Color.fromARGB(255, 250, 149, 149),
                    child: Text('Doces'),
                  ),
                  Container(
                    width: 50,
                    height: 50,
                    color: Color.fromARGB(255, 250, 149, 149),
                    child: Text('Apê'),
                  ),
                ]),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: () {
          setState(() {
            counter++; //necessario dizer que o estado vai ser modificado e precisa ser reconstuido
          });
        },
      ),
    );
  }
}

class CustomSwitch extends StatelessWidget {
  const CustomSwitch({super.key});

  @override
  Widget build(BuildContext context) {
    return Switch(
        value: AppController.instance.isDartTheme,
        onChanged: (value) {
          AppController.instance.changeTheme();
        });
  }
}
