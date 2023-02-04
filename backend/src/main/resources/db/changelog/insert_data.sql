--TABLE USUARIO
SET IDENTITY_INSERT Usuario ON
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento], [descricaoUsuario])VALUES(1,'Gabriel', 'Braga', 'gabriel@gmail.com',  CONVERT(VARCHAR(32), HashBytes('MD5', 'senha'), 2), '19912341234', convert(datetime,'2001-12-12 10:34:09 PM'), 'O mais bonito do Brasil')
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento], [descricaoUsuario])VALUES(2,'Herica', 'Cadoni', 'herica@gmail.com',  CONVERT(VARCHAR(32), HashBytes('MD5', 'dotnet'), 2), '19900000000', convert(datetime,'2002-02-06 10:34:09 PM'), 'A Maria-Sem-Vergonha')
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento], [descricaoUsuario])VALUES(3,'Diego', 'Negretto', 'diego@gmail.com', CONVERT(VARCHAR(32), HashBytes('MD5', 'professor'), 2), '19911111111', convert(datetime,'1990-01-01 10:34:09 PM'), 'Melhor orientador de TCC | Inimigo da Homeopatia')
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento], [descricaoUsuario])VALUES(4,'Victor', 'Seila', 'victor@gmail.com', CONVERT(VARCHAR(32), HashBytes('MD5', 'seila'), 2), '19922222222', convert(datetime,'1993-02-02 10:34:09 PM'), 'Desenvolvedor Flutter')
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento], [descricaoUsuario])VALUES(5,'Aurora', 'Boreal', 'aurora@gmail.com', CONVERT(VARCHAR(32), HashBytes('MD5', 'boreal'), 2), '19933333333', convert(datetime,'2000-03-03 10:34:09 PM'), 'Ih, nasceu')
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento], [descricaoUsuario])VALUES(6,'Helliot', 'Haha', 'helliot@gmail.com', CONVERT(VARCHAR(32), HashBytes('MD5', 'helloworld'), 2), '19944444444', convert(datetime,'2025-11-24 10:34:09 PM'), 'Vai programar em java quando crescer!')
SET IDENTITY_INSERT [Usuario] OFF
GO

--TABLE COMIDA
SET IDENTITY_INSERT Comida ON
INSERT INTO [Comida] ([idComida], [nomeComida], [tipoComida], [imagemComida], [idUsuario])VALUES(1,'Pizza', 'Salgada', null,  1)
SET IDENTITY_INSERT [Comida] OFF

GO