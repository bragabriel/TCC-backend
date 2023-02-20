--TABLE CARONA
SET IDENTITY_INSERT Carona ON
INSERT INTO [Carona] ([id_carona], [titulo_carona], [descricao_carona], [cidade_carona], [periodo_carona], [informacoesVeiculo_carona], [informacoesCondutor_carona], [qtdAssentosAtual_carona], [qtdAssentosTotal_carona], [id_usuario])
VALUES(1, 'Ofereco carona para FHO período noturno', 'Carona para a FHO saindo de Pirassununga. Com rotação de motorista', 'Pirassununga, SP', 'Noturno', 'Documentos em ordem, veiculo licenciado', 'Condutor habilitado', 5, 3, 1)
SET IDENTITY_INSERT [Carona] OFF
