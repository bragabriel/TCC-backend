USE [TCC]
GO

--TABLE USUARIO
USE [TCC]
GO

/****** Object:  Table [dbo].[Usuario]    Script Date: 5/28/2023 12:18:37 PM ******/
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
	[dataNascimento_usuario] [date] NOT NULL,
	[descricao_usuario] [varchar](255) NULL,
	[url_imageUsuario] [varchar](255) NULL,
	[sequence_imageUsuario] [int] NULL,
	[fileName_imageUsuario] [varchar](255) NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


--TABLE ALIMENTO
USE [TCC]
GO

/****** Object:  Table [dbo].[Alimento]    Script Date: 5/31/2023 10:23:01 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Alimento](
	[id_alimento] [int] IDENTITY(1,1) NOT NULL,
	[titulo_alimento] [varchar](255) NOT NULL,
	[descricao_alimento] [varchar](255) NOT NULL,
	[tipo_alimento] [varchar](255) NOT NULL,
	[sabor_alimento] [varchar](255) NULL,
	[oferta_alimento] [varchar](255) NULL,
	[marca_alimento] [varchar](255) NULL,
	[unidade_alimento] [varchar](255) NULL,
	[preco_alimento] [varchar](255) NOT NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Alimento] PRIMARY KEY CLUSTERED
(
	[id_alimento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Alimento]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Alimento] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO

ALTER TABLE [dbo].[Alimento] CHECK CONSTRAINT [FK_Usuario_Alimento]
GO


--TABLE APE
USE [TCC]
GO

/****** Object:  Table [dbo].[Ape]    Script Date: 4/7/2023 6:16:11 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Ape](
	[id_ape] [int] IDENTITY(1,1) NOT NULL,
	[qtdMoradoresPermitido_ape] [int] NOT NULL,
	[qtdMoradoresAtuais_ape] [int] NOT NULL,
	[precoAluguelTotal_ape] [float] NULL,
	[precoAluguelPorPessoa_ape] [float] NOT NULL,
	[localizacao_ape] [varchar](255) NOT NULL,
	[vagaGaragem_ape] [varchar](255) NOT NULL,
	[animaisEstimacao_ape] [varchar](255) NOT NULL,
	[titulo_ape] [varchar](255) NOT NULL,
	[descricao_ape] [varchar](255) NOT NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Ape] PRIMARY KEY CLUSTERED
(
	[id_ape] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Ape]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Ape] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO

ALTER TABLE [dbo].[Ape] CHECK CONSTRAINT [FK_Usuario_Ape]
GO


--TABLE CARONA
USE [TCC]
GO

/****** Object:  Table [dbo].[Carona]    Script Date: 14/02/2023 20:58:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Carona](
	[id_carona] [int] IDENTITY(1,1) NOT NULL,
	[titulo_carona] [varchar](255) NOT NULL,
	[descricao_carona] [varchar](255) NULL,
	[cidade_carona] [varchar](255) NOT NULL,
	[periodo_carona] [varchar](255) NOT NULL,
	[informacoesVeiculo_carona] [varchar](255) NOT NULL,
	[informacoesCondutor_carona] [varchar](255) NOT NULL,
	[qtdAssentosAtual_carona] [int] NOT NULL,
	[qtdAssentosTotal_carona] [int] NOT NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Carona] PRIMARY KEY CLUSTERED
(
	[id_carona] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Carona]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Carona] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO

ALTER TABLE [dbo].[Carona] CHECK CONSTRAINT [FK_Usuario_Carona]
GO


--TABLE CRUSH
USE [TCC]
GO

/****** Object:  Table [dbo].[Crush]    Script Date: 14/02/2023 20:58:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Crush](
	[id_crush] [int] IDENTITY(1,1) NOT NULL,
	[titulo_crush] [varchar](255) NOT NULL,
	[descricao_crush] [varchar](255) NOT NULL,
	[curso_crush] [varchar](255) NULL,
	[localizacao_crush] [varchar](255) NULL,
	[periodo_crush] [varchar](255) NOT NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Crush] PRIMARY KEY CLUSTERED
(
	[id_crush] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Crush]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Crush] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO

ALTER TABLE [dbo].[Crush] CHECK CONSTRAINT [FK_Usuario_Crush]
GO


--TABLE ESTAGIO
USE [TCC]
GO

/****** Object:  Table [dbo].[Estagio]    Script Date: 14/02/2023 20:59:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Estagio](
	[id_estagio] [int] IDENTITY(1,1) NOT NULL,
	[titulo_estagio] [varchar](255) NOT NULL,
	[descricao_estagio] [varchar](255) NOT NULL,
	[localizacao_estagio] [varchar](255) NULL,
	[requisitos_estagio] [varchar](255) NULL,
	[beneficios_estagio] [varchar](255) NULL,
	[salario_estagio] [float] NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Estagio] PRIMARY KEY CLUSTERED
(
	[id_estagio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Estagio]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Estagio] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO

ALTER TABLE [dbo].[Estagio] CHECK CONSTRAINT [FK_Usuario_Estagio]
GO


--TABLE FESTA
USE [TCC]
GO

/****** Object:  Table [dbo].[Festa]    Script Date: 14/02/2023 20:59:47 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Festa](
	[id_festa] [int] IDENTITY(1,1) NOT NULL,
	[titulo_festa] [varchar](255) NOT NULL,
	[descricao_festa] [varchar](255) NOT NULL,
	[localizacao_festa] [varchar](255) NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Festa] PRIMARY KEY CLUSTERED
(
	[id_festa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Festa]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Festa] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO

ALTER TABLE [dbo].[Festa] CHECK CONSTRAINT [FK_Usuario_Festa]
GO


--TABLE OBJETO
USE [TCC]
GO

/****** Object:  Table [dbo].[Objeto]    Script Date: 14/02/2023 20:59:59 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Objeto](
	[id_objeto] [int] IDENTITY(1,1) NOT NULL,
	[titulo_objeto] [varchar](255) NOT NULL,
	[descricao_objeto] [varchar](255) NULL,
	[localizacaoAchado_objeto] [varchar](255) NOT NULL,
	[localizacaoAtual_objeto] [varchar](255) NOT NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Objeto] PRIMARY KEY CLUSTERED
(
	[id_objeto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Objeto]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Objeto] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO

ALTER TABLE [dbo].[Objeto] CHECK CONSTRAINT [FK_Usuario_Objeto]
GO

--IMAGES
--TABLE APE IMAGES
/****** Object:  Table [dbo].[ApeImage]    Script Date: 4/21/2023 6:47:21 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ApeImage](
	[id_imageApe] [int] IDENTITY(1,1) NOT NULL,
	[url_imageApe] [varchar](255) NOT NULL,
	[sequence_imageApe] [int] NULL,
	[fileName_imageApe] [varchar](255) NOT NULL,
	[id_ape] [int] NOT NULL,
 CONSTRAINT [PK_ApeImage] PRIMARY KEY CLUSTERED
(
	[id_imageApe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ApeImage]  WITH CHECK ADD  CONSTRAINT [FK_ApeImage_Ape] FOREIGN KEY([id_ape])
REFERENCES [dbo].[Ape] ([id_ape])
GO

ALTER TABLE [dbo].[ApeImage] CHECK CONSTRAINT [FK_ApeImage_Ape]
GO



--TABLE CARONA IMAGES
/****** Object:  Table [dbo].[CaronaImage]    Script Date: 4/21/2023 6:47:51 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CaronaImage](
	[id_imageCarona] [int] IDENTITY(1,1) NOT NULL,
	[url_imageCarona] [varchar](255) NOT NULL,
	[sequence_imageCarona] [int] NULL,
	[fileName_imageCarona] [varchar](255) NOT NULL,
	[id_carona] [int] NOT NULL,
 CONSTRAINT [PK_CaronaImage] PRIMARY KEY CLUSTERED
(
	[id_imageCarona] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[CaronaImage]  WITH CHECK ADD  CONSTRAINT [FK_CaronaImage_Carona] FOREIGN KEY([id_carona])
REFERENCES [dbo].[Carona] ([id_carona])
GO

ALTER TABLE [dbo].[CaronaImage] CHECK CONSTRAINT [FK_CaronaImage_Carona]
GO


--TABLE ALIMENTO IMAGES
USE [TCC]
GO

/****** Object:  Table [dbo].[AlimentoImage]    Script Date: 5/31/2023 10:23:57 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[AlimentoImage](
	[id_imageAlimento] [int] IDENTITY(1,1) NOT NULL,
	[url_imageAlimento] [varchar](255) NOT NULL,
	[sequence_imageAlimento] [int] NULL,
	[fileName_imageAlimento] [varchar](255) NOT NULL,
	[id_alimento] [int] NOT NULL,
 CONSTRAINT [PK_Images] PRIMARY KEY CLUSTERED
(
	[id_imageAlimento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[AlimentoImage]  WITH CHECK ADD  CONSTRAINT [FK_AlimentoImage_Alimento] FOREIGN KEY([id_alimento])
REFERENCES [dbo].[Alimento] ([id_alimento])
GO

ALTER TABLE [dbo].[AlimentoImage] CHECK CONSTRAINT [FK_AlimentoImage_Alimento]
GO


--TABLE CRUSH IMAGES
/****** Object:  Table [dbo].[CrushImage]    Script Date: 4/21/2023 6:48:27 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CrushImage](
	[id_imageCrush] [int] IDENTITY(1,1) NOT NULL,
	[url_imageCrush] [varchar](255) NOT NULL,
	[sequence_imageCrush] [int] NULL,
	[fileName_imageCrush] [varchar](255) NOT NULL,
	[id_crush] [int] NOT NULL,
 CONSTRAINT [PK_CrushImage] PRIMARY KEY CLUSTERED
(
	[id_imageCrush] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[CrushImage]  WITH CHECK ADD  CONSTRAINT [FK_CrushImage_Crush] FOREIGN KEY([id_crush])
REFERENCES [dbo].[Crush] ([id_crush])
GO

ALTER TABLE [dbo].[CrushImage] CHECK CONSTRAINT [FK_CrushImage_Crush]
GO


--TABLE ESTAGIO IMAGES
/****** Object:  Table [dbo].[EstagioImage]    Script Date: 4/21/2023 6:48:31 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[EstagioImage](
	[id_imageEstagio] [int] IDENTITY(1,1) NOT NULL,
	[url_imageEstagio] [varchar](255) NOT NULL,
	[sequence_imageEstagio] [int] NULL,
	[fileName_imageEstagio] [varchar](255) NOT NULL,
	[id_estagio] [int] NOT NULL,
 CONSTRAINT [PK_EstagioImage] PRIMARY KEY CLUSTERED
(
	[id_imageEstagio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[EstagioImage]  WITH CHECK ADD  CONSTRAINT [FK_EstagioImage_Estagio] FOREIGN KEY([id_estagio])
REFERENCES [dbo].[Estagio] ([id_estagio])
GO

ALTER TABLE [dbo].[EstagioImage] CHECK CONSTRAINT [FK_EstagioImage_Estagio]
GO


--TABLE FESTA IMAGES
/****** Object:  Table [dbo].[FestaImage]    Script Date: 4/21/2023 6:48:34 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[FestaImage](
	[id_imageFesta] [int] IDENTITY(1,1) NOT NULL,
	[url_imageFesta] [varchar](255) NOT NULL,
	[sequence_imageFesta] [int] NULL,
	[fileName_imageFesta] [varchar](255) NOT NULL,
	[id_festa] [int] NOT NULL,
 CONSTRAINT [PK_FestaImage] PRIMARY KEY CLUSTERED
(
	[id_festa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[FestaImage]  WITH CHECK ADD  CONSTRAINT [FK_FestaImage_Festa] FOREIGN KEY([id_imageFesta])
REFERENCES [dbo].[Festa] ([id_festa])
GO

ALTER TABLE [dbo].[FestaImage] CHECK CONSTRAINT [FK_FestaImage_Festa]
GO


--TABLE OBJETO IMAGES
/****** Object:  Table [dbo].[ObjetoImage]    Script Date: 4/21/2023 6:48:37 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ObjetoImage](
	[id_imageObjeto] [int] IDENTITY(1,1) NOT NULL,
	[url_imageObjeto] [varchar](255) NOT NULL,
	[sequence_imageObjeto] [int] NULL,
	[fileName_imageObjeto] [varchar](255) NOT NULL,
	[id_objeto] [int] NOT NULL,
 CONSTRAINT [PK_ObjetoImage] PRIMARY KEY CLUSTERED
(
	[id_objeto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ObjetoImage]  WITH CHECK ADD  CONSTRAINT [FK_ObjetoImage_Objeto] FOREIGN KEY([id_imageObjeto])
REFERENCES [dbo].[Objeto] ([id_objeto])
GO

ALTER TABLE [dbo].[ObjetoImage] CHECK CONSTRAINT [FK_ObjetoImage_Objeto]
GO