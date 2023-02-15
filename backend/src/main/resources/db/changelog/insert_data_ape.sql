--TABLE USUARIO
SET IDENTITY_INSERT Ape ON
INSERT INTO [Ape] ([id_ape], [qtdMoradoresPermitido_ape], [qtdMoradoresAtuais_ape], [precoAluguelTotal_ape], [precoAluguelPorPessoa_ape], [localizacao_ape], [vagaGaragem_ape], [animaisEstimacao_ape], [descricao_ape], [titulo_ape], [id_usuario])
VALUES(1, 5, 3, 3000.00, 600.00, 'Av. Dr. Maximiliano Baruto, 550 - Jardim Universitario, Araras', 'Temos 4 vagas no total, você ficará com uma. Podendo ser usada por você ou familiares', 'Temos 2 cachorros (Bulldog Francês) na casa, são carinhosos não se preocupe! Aceitamos animais de estimação, você pode trazer seu bichinho, contanto que cuide!',
 '5 quartos, 3 banheiros, vaga na garagem para 4 carros, piscina, alarme, cerca elétrica, cozinha e sala de estar', 'Ofereço vaga em apê', 1)
SET IDENTITY_INSERT [Ape] OFF
