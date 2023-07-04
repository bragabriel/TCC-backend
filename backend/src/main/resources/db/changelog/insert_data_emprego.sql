--TABLE ESTAGIO
SET IDENTITY_INSERT Emprego ON
INSERT INTO [Emprego] ([id_artefato], [localizacao_emprego], [requisitos_emprego], [beneficios_emprego], [salario_emprego])
VALUES(1, 'Shelby Company LTDA', 'Java 8/11, Maven, Spring Framework, JPA, Testes unitários, Git e GitFlow, APIs RESTful, Banco de Dados, AWS; Conhecimentos extras que podem ajudar ainda mais na sua contratação? Usar boina; Saber usar canivete; Preferir Whisky Irlândes ao Escocês.',
 'Você se torna um Peaky Blinder', 50000.00)
SET IDENTITY_INSERT [Emprego] OFF
