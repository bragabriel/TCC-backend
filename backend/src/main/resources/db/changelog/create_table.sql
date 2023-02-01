USE [TCC]
GO

/****** Object:  Table [dbo].[Usuario]    Script Date: 01/02/2023 11:07:18 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Usuario]') AND type in (N'U'))
DROP TABLE [dbo].[Usuario]
GO

/****** Object:  Table [dbo].[Usuario]    Script Date: 01/02/2023 11:07:18 ******/
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
	[imagemUsuario] [varbinary](max) NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED
(
	[idUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
