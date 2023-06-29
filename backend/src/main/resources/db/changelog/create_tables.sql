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
	[dataNascimento_usuario] [date] NOT NULL,
	[url_imageUsuario] [varchar](255) NULL,
	[sequence_imageUsuario] [int] NULL,
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

/****** Object:  Table [dbo].[Artefato]    Script Date: 6/28/2023 7:43:09 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Artefato](
	[id_artefato] [int] IDENTITY(1,1) NOT NULL,
	[titulo_artefato] [varchar](255) NOT NULL,
	[descricao_artefato] [varchar](255) NOT NULL,
	[tipo_artefato] [varchar](50) NOT NULL,
	[ativo] [bit] NOT NULL,
	[data_cadastro] [date] NOT NULL,
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

/****** Object:  Table [dbo].[Alimento]    Script Date: 6/28/2023 7:43:40 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Alimento](
	[id_artefato] [int] IDENTITY(1,1) NOT NULL,
	[tipo_alimento] [varchar](255) NOT NULL,
	[preco_alimento] [varchar](255) NOT NULL,
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