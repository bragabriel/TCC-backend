USE [TCC]
GO


--TABLE USUARIO
USE [TCC]
GO

/****** Object:  Table [dbo].[Usuario]    Script Date: 6/28/2023 7:42:15 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Usuario](
	[id_usuario] [int] IDENTITY(1,1) NOT NULL,
	[nome_usuario] [varchar](255) NOT NULL,
	[sobrenome_usuario] [varchar](255) NOT NULL,
	[email_usuario] [varchar](255) NOT NULL,
	[senha_usuario] [varchar](255) NOT NULL,
	[telefone_usuario] [varchar](255) NOT NULL,
	[url_imageUsuario] [varchar](255) NULL,
	[fileName_imageUsuario] [varchar](255) NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


--TABLE ARTEFATO
USE [TCC]
GO

/****** Object:  Table [dbo].[Artefato]    Script Date: 7/2/2023 8:01:53 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Artefato](
	[id_artefato] [int] IDENTITY(1,1) NOT NULL,
	[titulo_artefato] [varchar](255) NOT NULL,
	[descricao_artefato] [varchar](255) NOT NULL,
	[ativo] [bit] NOT NULL,
	[tipo_artefato] [varchar] (255) NOT NULL,
	[data_cadastro] [date] NOT NULL,
	[data_inativo] [date] NULL,
	[data_atualizacao] [date] NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Artefato] PRIMARY KEY CLUSTERED
(
	[id_artefato] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Artefato]  WITH CHECK ADD  CONSTRAINT [FK_Artefato_Usuario] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO

ALTER TABLE [dbo].[Artefato] CHECK CONSTRAINT [FK_Artefato_Usuario]
GO


--TABLE ALIMENTO
USE [TCC]
GO

/****** Object:  Table [dbo].[Alimento]    Script Date: 7/4/2023 9:59:50 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Alimento](
	[id_artefato] [int] NOT NULL,
	[tipo_alimento] [varchar](255) NULL,
	[preco_alimento] [varchar](255) NULL,
	[oferta_alimento] [varchar](255) NULL,
	[sabor_alimento] [varchar](255) NULL,
	[marca_alimento] [varchar](255) NULL,
	[unidade_alimento] [varchar](255) NULL,
 CONSTRAINT [PK_Alimento] PRIMARY KEY CLUSTERED
(
	[id_artefato] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Alimento]  WITH CHECK ADD  CONSTRAINT [FK_Artefato_Alimento] FOREIGN KEY([id_artefato])
REFERENCES [dbo].[Artefato] ([id_artefato])
GO

ALTER TABLE [dbo].[Alimento] CHECK CONSTRAINT [FK_Artefato_Alimento]
GO


--TABLE MORADIA
USE [TCC]
GO

/****** Object:  Table [dbo].[Moradia]    Script Date: 7/26/2023 10:46:20 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Moradia](
	[id_artefato] [int] NOT NULL,
	[qtdMoradoresPermitido_moradia] [int] NULL,
	[qtdMoradoresAtuais_moradia] [int] NULL,
	[precoAluguelTotal_moradia] [float] NULL,
	[precoAluguelPorPessoa_moradia] [float] NULL,
	[estado_moradia] [varchar](255) NOT NULL,
	[cidade_moradia] [varchar](255) NOT NULL,
	[bairro_moradia] [varchar](255) NULL,
	[cep_moradia] [varchar](255) NULL,
	[vagaGaragem_moradia] [varchar](255) NULL,
	[animaisEstimacao_moradia] [varchar](255) NULL,
 CONSTRAINT [PK_Moradia] PRIMARY KEY CLUSTERED
(
	[id_artefato] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Moradia]  WITH CHECK ADD  CONSTRAINT [FK_Artefato_Moradia] FOREIGN KEY([id_artefato])
REFERENCES [dbo].[Artefato] ([id_artefato])
GO

ALTER TABLE [dbo].[Moradia] CHECK CONSTRAINT [FK_Artefato_Moradia]
GO


--TABLE TRANSPORTE
USE [TCC]
GO

/****** Object:  Table [dbo].[Transporte]    Script Date: 7/3/2023 8:54:41 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Transporte](
	[id_artefato] [int] NOT NULL,
	[cidade_transporte] [varchar](255) NULL,
	[periodo_transporte] [varchar](255) NULL,
	[informacoesVeiculo_transporte] [varchar](255) NULL,
	[informacoesCondutor_transporte] [varchar](255) NULL,
	[qtdAssentosTotal_transporte] [int] NULL,
	[qtdAssentosPreenchidos_transporte] [int] NULL,
	[valor_transporte] [varchar] (255) NULL,
 CONSTRAINT [PK_Transporte] PRIMARY KEY CLUSTERED
(
	[id_artefato] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Transporte]  WITH CHECK ADD  CONSTRAINT [FK_Artefato_Transporte] FOREIGN KEY([id_artefato])
REFERENCES [dbo].[Artefato] ([id_artefato])
GO

ALTER TABLE [dbo].[Transporte] CHECK CONSTRAINT [FK_Artefato_Transporte]
GO


--TABLE EMPREGO
USE [TCC]
GO

/****** Object:  Table [dbo].[Emprego]    Script Date: 7/11/2023 8:28:49 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Emprego](
	[id_artefato] [int] NOT NULL,
	[localizacao_emprego] [varchar](255) NULL,
	[requisitos_emprego] [varchar](255) NULL,
	[beneficios_emprego] [varchar](255) NULL,
	[salario_emprego] [float] NULL,
	[contato_emprego] [varchar](255) NULL,
	[linkVaga_emprego] [varchar](255) NULL,
	[empresa_emprego] [varchar](255) NULL,
	[cidade_emprego] [varchar](255) NULL,
	[estado_emprego] [varchar](255) NULL,
	[experiencia_emprego] [varchar](255) NULL,
	[tipoVaga_emprego] [varchar](255) NULL,
	[presencial_emprego] [varchar](255) NULL,
 CONSTRAINT [PK_Emprego] PRIMARY KEY CLUSTERED
(
	[id_artefato] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Emprego]  WITH CHECK ADD  CONSTRAINT [FK_Artefato_Emprego] FOREIGN KEY([id_artefato])
REFERENCES [dbo].[Artefato] ([id_artefato])
GO

ALTER TABLE [dbo].[Emprego] CHECK CONSTRAINT [FK_Artefato_Emprego]
GO


--TABLE EVENTO
USE [TCC]
GO

/****** Object:  Table [dbo].[Evento]    Script Date: 7/3/2023 8:48:44 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Evento](
	[id_artefato] [int] NOT NULL,
	[localizacao_evento] [varchar](255) NULL,
 CONSTRAINT [PK_Evento] PRIMARY KEY CLUSTERED
(
	[id_artefato] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Evento]  WITH CHECK ADD  CONSTRAINT [FK_Artefato_Evento] FOREIGN KEY([id_artefato])
REFERENCES [dbo].[Artefato] ([id_artefato])
GO

ALTER TABLE [dbo].[Evento] CHECK CONSTRAINT [FK_Artefato_Evento]
GO


--TABLE OBJETO
USE [TCC]
GO

/****** Object:  Table [dbo].[Objeto]    Script Date: 7/3/2023 9:12:30 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Objeto](
	[id_artefato] [int] NOT NULL,
	[localizacaoAchado_objeto] [varchar](255) NOT NULL,
	[localizacaoAtual_objeto] [varchar](255) NOT NULL,
 CONSTRAINT [PK_Objeto] PRIMARY KEY CLUSTERED
(
	[id_artefato] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Objeto]  WITH CHECK ADD  CONSTRAINT [FK_Artefato_Objeto] FOREIGN KEY([id_artefato])
REFERENCES [dbo].[Artefato] ([id_artefato])
GO

ALTER TABLE [dbo].[Objeto] CHECK CONSTRAINT [FK_Artefato_Objeto]
GO


--TABLE IMAGEM
USE [TCC]
GO

USE [TCC]
GO

/****** Object:  Table [dbo].[Imagem]    Script Date: 7/25/2023 11:47:47 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Imagem](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[url] [varchar](255) NOT NULL,
	[sequence] [int] NULL,
	[fileName] [varchar](255) NOT NULL,
	[id_artefato] [int] NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Imagem]  WITH CHECK ADD FOREIGN KEY([id_artefato])
REFERENCES [dbo].[Artefato] ([id_artefato])
GO