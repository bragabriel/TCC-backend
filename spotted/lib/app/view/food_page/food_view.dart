import 'package:flutter/material.dart';
import '../../controller/food_controller.dart';
import '../../repository/http_service.dart';
import 'post_model.dart';

class PostsPage extends StatelessWidget {

  final FoodRepository foodRepository = FoodRepository();

  _success(){
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.orange,
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home: Scaffold(
          appBar: AppBar(
            title: Text("Comidas"),
          ),
          body: FutureBuilder(
            future: foodRepository.getPosts(),
            builder:
                (BuildContext context, AsyncSnapshot<List<Post>> snapshot) {
              if (snapshot.hasData) {
                List<Post>? posts = snapshot.data;
                return ListView(
                  children: posts!
                      .map(
                        (Post post) => ListTile(
                          title: Text(post.title),
                          subtitle: Text("${post.userId}"),
                        ),
                      )
                      .toList(),
                );
              } else {
                return Center(child: CircularProgressIndicator());
              }
            },
          ),
        ));
  }

  _error(){
    return Center(
      child: ElevatedButton(onPressed: () {
        
      },
      child: Text('Tente novamente'),),
    );
  }

  _loading(){
    return Center(
      child: CircularProgressIndicator(),
    );
  }

  _start(){
    return Container();
  }

  //Método para a troca de estado
  stateManagement(HomeState state){
      switch (state) {
      case HomeState.start:
        return _start();
      case HomeState.loading:
        return _loading();
      case HomeState.error:
        return _error();
      case HomeState.success:
        return _success();
      default: _start();
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.orange,
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home: Scaffold(
          appBar: AppBar(
            title: Text("Comidas"),
          ),
          body: stateManagement(HomeState.start),
    ));
  }
}
