import 'dart:convert';
import 'package:http/http.dart';
import '../view/food_page/post_model.dart';

class FoodRepository {
  
  final String postsURL = "https://jsonplaceholder.typicode.com/posts";

  Future<List<Post>> getPosts() async {

    Response res = await get(
      Uri.parse(postsURL),
      headers: {"Accept": "application/json"}
    );

    if (res.statusCode == 200) {

      //Se o servidor retornar um response OK, vamos fazer o parse no JSON
      List<dynamic> body = jsonDecode(res.body);

      List<Post> posts = body
        .map(
          (dynamic item) => Post.fromJson(item),
        )
        .toList();

      return posts;
    } else {
      //Se a resposta não for OK , lançamos um erro
      throw "Não foi possível recuperar as comidas.";
    }
  }
}