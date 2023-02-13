USE [TCC]
GO

--TABLE USUARIO
/****** Object:  Table [dbo].[Usuario]    Script Date: 12/02/2023 22:06:36 ******/
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
	[imagem_usuario] [varbinary](max) NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

--TABLE COMIDA
USE [TCC]
GO

/****** Object:  Table [dbo].[Comida]    Script Date: 12/02/2023 22:07:01 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Comida](
	[id_comida] [int] IDENTITY(1,1) NOT NULL,
	[titulo_comida] [varchar](255) NOT NULL,
	[descricao_comida] [varchar](255) NOT NULL,
	[tipo_comida] [varchar](255) NOT NULL,
	[sabor_comida] [varchar](255) NULL,
	[oferta_comida] [varchar](255) NULL,
	[marca_comida] [varchar](255) NULL,
	[unidade_comida] [varchar](255) NULL,
	[preco_comida] [varchar](255) NOT NULL,
	[imagem_comida] [varbinary](max) NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_Comida] PRIMARY KEY CLUSTERED
(
	[id_comida] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[Comida]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Comida] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO

ALTER TABLE [dbo].[Comida] CHECK CONSTRAINT [FK_Usuario_Comida]
GO


