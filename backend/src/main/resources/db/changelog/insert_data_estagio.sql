--TABLE ESTAGIO
SET IDENTITY_INSERT Estagio ON
INSERT INTO [Estagio] ([id_estagio], [titulo_estagio], [descricao_estagio], [localizacao_estagio], [requisitos_estagio], [beneficios_estagio], [salario_estagio], [id_usuario])
VALUES(1, 'Pl/Sr Software Engineer', 'Engenheiro de Software BackEnd; vaga única. Interessados, falar com Thomas, shelbinho@yahoo.com', 'Shelby Company LTDA',
 'Java 8/11, Maven, Spring Framework, JPA, Testes unitários, Git e GitFlow, APIs RESTful, Banco de Dados, AWS; Conhecimentos extras que podem ajudar ainda mais na sua contratação? Usar boina; Saber usar canivete; Preferir Whisky Irlândes ao Escocês.',
 'Você se torna um Peaky Blinder', 'Condutor habilitado', 50000.00, 1)
SET IDENTITY_INSERT [Estagio] OFF
