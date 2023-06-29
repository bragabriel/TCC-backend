--TABLE Alimento
SET IDENTITY_INSERT Alimento ON
INSERT INTO [Alimento] ([id_artefato], [tipo_alimento], [marca_alimento], [sabor_alimento], [unidade_alimento], [preco_alimento], [oferta_alimento])
VALUES(1, 'Salgado', 'Sadia',  'Frango com Catupiry', 'Fatia', '5', '3 fatias por 12reais')
SET IDENTITY_INSERT Alimento OFF