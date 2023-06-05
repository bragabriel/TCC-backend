--TABLE Alimento
SET IDENTITY_INSERT Alimento ON
INSERT INTO [Alimento] ([id_alimento], [titulo_alimento], [descricao_alimento], [tipo_alimento], [sabor_alimento], [oferta_alimento], [unidade_alimento], [preco_alimento], [id_usuario])
VALUES(1, 'Pizza', 'Pizza Deliciosa!', 'Salgada', 'Calabresa, Frango com requeijão ou Pizza.', '3 fatias por 13,50R$', 'Fatia', '5', 1)

INSERT INTO [Alimento] ([id_alimento], [titulo_alimento], [descricao_alimento], [tipo_alimento], [sabor_alimento], [oferta_alimento], [unidade_alimento], [preco_alimento], [id_usuario])
VALUES(2, 'Trufa', 'Trufa Deliciosa!', 'Doce', 'Nutela, Brigadeiro ou Leite Ninho', '3 trufas por 10,00R$', 'Trufa', '3.50', 1)
SET IDENTITY_INSERT [Alimento] OFF
