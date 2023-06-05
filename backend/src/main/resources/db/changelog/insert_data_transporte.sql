--TABLE CARONA
SET IDENTITY_INSERT Transporte ON
INSERT INTO [Transporte] ([id_transporte], [titulo_transporte], [descricao_transporte], [cidade_transporte], [periodo_transporte], [informacoesVeiculo_transporte], [informacoesCondutor_transporte], [qtdAssentosTotal_transporte], [qtdAssentosPreenchidos_transporte], [id_usuario])
VALUES(1, 'Ofereco transporte para FHO período noturno', 'Transporte para a FHO saindo de Pirassununga. Com rotação de motorista', 'Pirassununga, SP', 'Noturno', 'Documentos em ordem, veiculo licenciado', 'Condutor habilitado', 5, 3, 1)
SET IDENTITY_INSERT [Transporte] OFF
