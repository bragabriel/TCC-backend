--TABLE Artefato
SET IDENTITY_INSERT Artefato ON

--Insert Alimento
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [tipo_artefato], [ativo], [data_cadastro], [id_usuario])
VALUES(1, 'Salgada', 'Calabresa, Frango com requeijão ou Pizza.', 'ALIMENTO', 1, convert(datetime,'2001-12-12 10:34:09 PM'), 1)

--Insert Transporte
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [tipo_artefato], [ativo], [data_cadastro], [id_usuario])
VALUES(2, 'Ofereco transporte para FHO período noturno', 'Transporte para a FHO saindo de Pirassununga. Com rotação de motorista', 'TRANSPORTE', 1, convert(datetime,'2001-12-12 10:34:09 PM'), 1)

SET IDENTITY_INSERT Artefato OFF


