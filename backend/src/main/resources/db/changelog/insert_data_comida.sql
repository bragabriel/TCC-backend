--TABLE COMIDA
SET IDENTITY_INSERT Comida ON
INSERT INTO [Comida] ([id_comida], [titulo_comida], [descricao_comida], [tipo_comida], [sabor_comida], [oferta_comida], [unidade_comida], [preco_comida], [id_usuario])
VALUES(1, 'Pizza', 'Pizza Deliciosa!', 'Salgada', 'Calabresa, Frango com requeijão ou Pizza.', '3 fatias por 13,50R$', 'Fatia', '5', 1)

INSERT INTO [Comida] ([id_comida], [titulo_comida], [descricao_comida], [tipo_comida], [sabor_comida], [oferta_comida], [unidade_comida], [preco_comida], [id_usuario])
VALUES(2, 'Trufa', 'Trufa Deliciosa!', 'Doce', 'Nutela, Brigadeiro ou Leite Ninho', '3 trufas por 10,00R$', 'Trufa', '3.50', 1)
SET IDENTITY_INSERT [Comida] OFF
