--TABLE TRANSPORTE
SET IDENTITY_INSERT Transporte ON
INSERT INTO [Transporte] ([id_artefato],  [cidade_transporte], [periodo_transporte], [informacoesVeiculo_transporte], [informacoesCondutor_transporte], [qtdAssentosTotal_transporte], [qtdAssentosPreenchidos_transporte])
VALUES(2, 'Pirassununga, SP', 'Noturno', 'Documentos em ordem, veiculo licenciado', 'Condutor habilitado', 5, 3)
SET IDENTITY_INSERT [Transporte] OFF
