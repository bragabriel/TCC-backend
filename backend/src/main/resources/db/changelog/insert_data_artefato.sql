--TABLE Artefato
SET IDENTITY_INSERT Artefato ON

--Insert Alimento
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [tipo_artefato], [ativo], [data_cadastro], [id_usuario])
VALUES(1, 'Deliciosa Pizza', 'Sabores de: Calabresa, Frango com requeijão ou Pizza.', 'ALIMENTO', 1, convert(datetime,'2001-12-12 10:34:09 PM'), 1)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [tipo_artefato], [ativo], [data_cadastro], [id_usuario])
VALUES(7, 'Trufa Maravilhosa', 'A melhor trufa de Nutella que você vai provar na vida!', 'ALIMENTO', 1, convert(datetime,'2001-12-12 10:34:09 PM'), 1)

--Insert Moradia
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [tipo_artefato], [ativo], [data_cadastro], [id_usuario])
VALUES(2, 'Ofereço vaga em apê', '5 quartos, 3 banheiros, vaga na garagem para 4 carros, piscina, alarme, cerca elétrica, cozinha e sala de estar', 'MORADIA', 1, convert(datetime,'2001-12-12 10:34:09 PM'), 1)

--Insert Transporte
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [tipo_artefato], [ativo], [data_cadastro], [id_usuario])
VALUES(3, 'Ofereco transporte para FHO período noturno', 'Transporte para a FHO saindo de Pirassununga. Com rotação de motorista', 'TRANSPORTE', 1, convert(datetime,'2001-12-12 10:34:09 PM'), 1)

--Insert Emprego
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [tipo_artefato], [ativo], [data_cadastro], [id_usuario])
VALUES(4, 'Pl/Sr Software Engineer', 'Engenheiro de Software BackEnd; vaga única. Interessados, falar com Thomas, shelbinho@yahoo.com', 'EMPREGO', 1, convert(datetime,'2001-12-12 10:34:09 PM'), 1)

--Insert Festa
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [tipo_artefato], [ativo], [data_cadastro], [id_usuario])
VALUES(5, 'Festa muito top', 'Open bar, open food, idade minima: 18, venda dos ingressos em: www.festatop.com.br', 'FESTA', 1, convert(datetime,'2001-12-12 10:34:09 PM'), 1)

--Insert Objeto
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [tipo_artefato], [ativo], [data_cadastro], [id_usuario])
VALUES(6, 'Moletom Nike', 'Moletom Nike preto e cinza', 'OBJETO', 1, convert(datetime,'2001-12-12 10:34:09 PM'), 1)

SET IDENTITY_INSERT Artefato OFF