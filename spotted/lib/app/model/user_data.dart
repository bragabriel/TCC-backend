import 'dart:convert';

import 'package:spotted/app/model/usuario_model.dart';
import 'package:shared_preferences/shared_preferences.dart';

class UserData {
  static late SharedPreferences _preferences;
  static const _keyUser = 'user';

  static UsuarioModel myUser = UsuarioModel(
    image:
        "https://upload.wikimedia.org/wikipedia/en/0/0b/Darth_Vader_in_The_Empire_Strikes_Back.jpg",
    name: 'puxar-da-api',
    email: 'puxar-da-api',
    phone: '1999999 puxar da api',
    aboutMeDescription:
        'puxar-da-api puxar-da-api puxar-da-api puxar-da-api puxar-da-api puxar-da-api puxar-da-api puxar-da-api puxar-da-api',
  );

  static Future init() async =>
      _preferences = await SharedPreferences.getInstance();

  static Future setUser(UsuarioModel user) async {
    final json = jsonEncode(user.toJson());

    await _preferences.setString(_keyUser, json);
  }

  static UsuarioModel getUser() {
    final json = _preferences.getString(_keyUser);

    return json == null ? myUser : UsuarioModel.fromJson(jsonDecode(json));
  }
}
