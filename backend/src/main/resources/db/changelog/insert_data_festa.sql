--TABLE Festa
SET IDENTITY_INSERT Festa ON
INSERT INTO [Festa] ([id_festa], [titulo_festa], [descricao_festa], [localizacao_festa], [id_usuario])
VALUES(1, 'Festa muito top', 'Open bar, open food, idade minima: 18, venda dos ingressos em: www.festatop.com.br', 'Araras, SP', 1)
SET IDENTITY_INSERT [Festa] OFF
