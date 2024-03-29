USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[Business]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Business](
	[BusinessID] [int] NOT NULL,
	[BusinessName] [char](50) NULL,
	[Address1] [varchar](50) NULL,
	[Address2] [varchar](50) NULL,
	[Zip] [int] NULL,
	[BusinessAffiliationID] [int] NOT NULL,
	[Phone] [bigint] NULL,
	[CreateDateTime] [datetime] NULL,
	[BusinessType] [varchar](50) NULL,
	[City] [varchar](50) NULL,
	[State] [varchar](50) NULL,
	[Longitude] [float] NULL,
	[Latitude] [float] NULL,
	[Rating] [float] NULL,
	[Email] [varchar](50) NULL,
	[Password] [varchar](100) NULL,
 CONSTRAINT [PK_Business] PRIMARY KEY CLUSTERED 
(
	[BusinessID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
