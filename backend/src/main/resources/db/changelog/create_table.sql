USE [TCC]
GO

--TABLE USUARIO
/****** Object:  Table [dbo].[Usuario]    Script Date: 02/02/2023 21:19:31 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Usuario]') AND type in (N'U'))
DROP TABLE [dbo].[Usuario]
GO

/****** Object:  Table [dbo].[Usuario]    Script Date: 02/02/2023 21:19:31 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Usuario](
	[idUsuario] [int] IDENTITY(1,1) NOT NULL,
	[nomeUsuario] [varchar](255) NOT NULL,
	[sobrenomeUsuario] [varchar](255) NOT NULL,
	[emailUsuario] [varchar](255) NOT NULL,
	[senhaUsuario] [varchar](255) NOT NULL,
	[telefoneUsuario] [varchar](255) NOT NULL,
	[dataNascimento] [date] NOT NULL,
	[descricaoUsuario] [varchar](255) NULL,
	[imagemUsuario] [varbinary](max) NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED
(
	[idUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

--TABLE COMIDA
/****** Object:  Table [dbo].[Comida]    Script Date: 03/02/2023 21:52:46 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Comida]') AND type in (N'U'))
DROP TABLE [dbo].[Comida]
GO

/****** Object:  Table [dbo].[Comida]    Script Date: 03/02/2023 21:52:46 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Comida](
	[idComida] [int] IDENTITY(1,1) NOT NULL,
	[nomeComida] [varchar](255) NOT NULL,
	[tipoComida] [varchar](255) NOT NULL,
	[imagemComida] [varbinary](max) NULL,
	[idUsuario] [int] NOT NULL,
 CONSTRAINT [PK_Comida] PRIMARY KEY CLUSTERED
(
	[idComida] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


