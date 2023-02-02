class UsuarioModel {
  int? idUsuario;
  String? nomeUsuario;
  String? sobrenomeUsuario;
  String? emailUsuario;
  String? senhaUsuario;
  String? telefoneUsuario;
  String? dataNascimento;
  Null? imagemUsuario;

  UsuarioModel(
      {this.idUsuario,
      this.nomeUsuario,
      this.sobrenomeUsuario,
      this.emailUsuario,
      this.senhaUsuario,
      this.telefoneUsuario,
      this.dataNascimento,
      this.imagemUsuario});

  UsuarioModel.fromJson(Map<String, dynamic> json) {
    idUsuario = json['idUsuario'];
    nomeUsuario = json['nomeUsuario'];
    sobrenomeUsuario = json['sobrenomeUsuario'];
    emailUsuario = json['emailUsuario'];
    senhaUsuario = json['senhaUsuario'];
    telefoneUsuario = json['telefoneUsuario'];
    dataNascimento = json['dataNascimento'];
    imagemUsuario = json['imagemUsuario'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['idUsuario'] = this.idUsuario;
    data['nomeUsuario'] = this.nomeUsuario;
    data['sobrenomeUsuario'] = this.sobrenomeUsuario;
    data['emailUsuario'] = this.emailUsuario;
    data['senhaUsuario'] = this.senhaUsuario;
    data['telefoneUsuario'] = this.telefoneUsuario;
    data['dataNascimento'] = this.dataNascimento;
    data['imagemUsuario'] = this.imagemUsuario;
    return data;
  }
}