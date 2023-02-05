import 'package:flutter/cupertino.dart';
import 'package:spotted/app/repository/http_service.dart';
import 'package:spotted/app/view/food_page/post_model.dart';

class FoodController{
  List<Post> todos = [];

  final _repository = FoodRepository();

  //Setando estado inicial
  final state = ValueNotifier<HomeState>(HomeState.start);

  Future start() async {
    state.value = HomeState.loading;
    
    try {
      todos = await _repository.getPosts();

      state.value = HomeState.success;
    } catch (e) {
      state.value = HomeState.error;
    }
  }
}

enum HomeState {
  start, loading, success, error
}