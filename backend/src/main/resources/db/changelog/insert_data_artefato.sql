--TABLE Artefato
SET IDENTITY_INSERT Artefato ON

--Insert Alimento
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(1, 'Deliciosa Pizza', 'Sabores de: Calabresa, Frango com requeijão ou Pizza.', 1, 'ALIMENTO', convert(datetime,'2023-12-12 10:34:09 PM'), 1)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(7, 'Trufa Maravilhosa', 'A melhor trufa de Nutella que você vai provar na vida!', 1, 'ALIMENTO', convert(datetime,'2023-12-12 10:34:09 PM'), 1)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(9, 'Bolo de Cenoura', 'Bolo delicioso de cenoura!', 1, 'ALIMENTO', convert(datetime,'2023-12-12 10:34:09 PM'), 2)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(10, 'Cone trufado', 'Cone trufado com diferentes sabores, venha experimentar', 1, 'ALIMENTO', convert(datetime,'2023-10-12 10:34:09 PM'), 3)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(11, 'Palha Italiana', 'Deliciosa, feita com muito carinho', 1, 'ALIMENTO', convert(datetime,'2023-12-12 10:34:09 PM'), 4)

--Insert Moradia
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(2, 'Ofereço vaga em apê', '5 quartos, 3 banheiros, vaga na garagem para 4 carros, piscina, alarme, cerca elétrica, cozinha e sala de estar', 1, 'MORADIA', convert(datetime,'2023-12-12 10:34:09 PM'), 1)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(8, 'Apartamento para Alugar', 'Apartamento aconchegante de 2 quartos, ótima localização, mobiliado.', 1, 'MORADIA', convert(datetime,'2023-12-12 10:34:09 PM'), 1)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(12, 'Casa Compartilhada', 'Quarto em casa compartilhada, ambiente amigável, todas as contas incluídas.', 1, 'MORADIA', convert(datetime,'2023-12-12 10:34:09 PM'), 2)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(13, 'Quarto para Alugar', 'Quarto espaçoso com banheiro privativo, ideal para estudantes.', 1, 'MORADIA', convert(datetime,'2023-12-12 10:34:09 PM'), 3)

--Insert Transporte
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(3, 'Ofereco transporte para FHO período noturno', 'Transporte para a FHO saindo de Pirassununga. Com rotação de motorista', 1, 'TRANSPORTE', convert(datetime,'2023-11-12 10:34:09 PM'), 1)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(14, 'Vagas de Van', 'Van de Leme à Araras', 1, 'TRANSPORTE', convert(datetime,'2023-11-12 10:34:09 PM'), 2)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(15, 'Vagas - VAN', 'Van de Rio Claro para Araras', 1, 'TRANSPORTE', convert(datetime,'2023-11-12 10:34:09 PM'), 3)

--Insert Emprego
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(4, 'Pl/Sr Software Engineer', 'Engenheiro de Software BackEnd; vaga única. Interessados, falar com Thomas, shelbinho@yahoo.com', 1, 'EMPREGO', convert(datetime,'2023-11-12 10:34:09 PM'), 1)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(16, 'Estágio como Desenvolvedor C', 'Desenvolvedor estagiário; 5 vagas. Interessados, contatar: rh123@gmail.com', 1, 'EMPREGO', convert(datetime,'2023-11-12 10:34:09 PM'), 2)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(17, 'Programa de estágio T.I', 'Você poderá atuar como Dev Backend, Frontend, QA ou DevOps!', 1, 'EMPREGO', convert(datetime,'2023-11-12 10:34:09 PM'), 4)

--Insert Festa
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(5, 'Festa muito top', 'Open bar, open food, idade minima: 18, venda dos ingressos em: www.festatop.com.br', 1, 'FESTA', convert(datetime,'2023-12-12 10:34:09 PM'), 1)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(18, 'Exposição de Arte Contemporânea', 'Venha explorar a criatividade contemporânea na nossa exposição de arte.', 1, 'FESTA', convert(datetime,'2023-12-12 10:34:09 PM'), 2)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(19, 'Curso de Fotografia', 'Curso intensivo de fotografia digital para iniciantes. Inscrições abertas!', 1, 'FESTA', convert(datetime,'2023-12-12 10:34:09 PM'), 3)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(20, 'Grupo de Estudos de Ciência Política', 'Junte-se a nós para discussões fascinantes sobre política e governança.', 1, 'FESTA', convert(datetime,'2023-12-12 10:34:09 PM'), 4)

--Insert Objeto
INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(6, 'Moletom Nike', 'Moletom Nike preto e cinza', 1, 'OBJETO', convert(datetime,'2023-12-12 10:34:09 PM'), 1)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(21, 'Livro de Física Quântica', 'Livro avançado sobre física quântica, edição atualizada.', 1, 'OBJETO', convert(datetime,'2023-12-12 10:34:09 PM'), 2)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(22, 'Carteirinha de estudante', 'Carteirinha de estudante', 1, 'OBJETO', convert(datetime,'2023-12-12 10:34:09 PM'), 3)

INSERT INTO [Artefato] ([id_artefato], [titulo_artefato], [descricao_artefato], [ativo], [tipo_artefato], [data_cadastro], [id_usuario])
VALUES(23, 'Câmera Canon', 'Câmera digital Canon EOS com lente de 18-55mm e acessórios.', 1, 'OBJETO', convert(datetime,'2023-12-12 10:34:09 PM'), 4)

SET IDENTITY_INSERT Artefato OFF