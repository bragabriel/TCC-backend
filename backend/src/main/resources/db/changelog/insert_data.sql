SET IDENTITY_INSERT Usuario ON
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento])VALUES(1,'Gabriel', 'Braga', 'gabriel@gmail.com',  CONVERT(VARCHAR(32), HashBytes('MD5', 'senha'), 2), '19912341234', convert(datetime,'2001-12-12 10:34:09 PM'))
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento])VALUES(2,'Herica', 'Cadoni', 'herica@gmail.com',  CONVERT(VARCHAR(32), HashBytes('MD5', 'dotnet'), 2), '19900000000', convert(datetime,'2002-02-06 10:34:09 PM'))
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento])VALUES(3,'Diego', 'Negretto', 'diego@gmail.com', CONVERT(VARCHAR(32), HashBytes('MD5', 'professor'), 2), '19911111111', convert(datetime,'1990-01-01 10:34:09 PM'))
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento])VALUES(4,'Victor', 'Seila', 'victor@gmail.com', CONVERT(VARCHAR(32), HashBytes('MD5', 'seila'), 2), '19922222222', convert(datetime,'1993-02-02 10:34:09 PM'))
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento])VALUES(5,'Aurora', 'Boreal', 'aurora@gmail.com', CONVERT(VARCHAR(32), HashBytes('MD5', 'boreal'), 2), '19933333333', convert(datetime,'2000-03-03 10:34:09 PM'))
INSERT INTO [Usuario] ([idUsuario],[nomeUsuario], [sobrenomeUsuario], [emailUsuario], [senhaUsuario], [telefoneUsuario], [dataNascimento])VALUES(6,'Helliot', 'Haha', 'helliot@gmail.com', CONVERT(VARCHAR(32), HashBytes('MD5', 'helloworld'), 2), '19944444444', convert(datetime,'2025-11-24 10:34:09 PM'))
SET IDENTITY_INSERT [Usuario] OFF