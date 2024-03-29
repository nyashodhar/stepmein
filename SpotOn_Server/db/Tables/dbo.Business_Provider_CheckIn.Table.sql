USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[Business_Provider_CheckIn]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Business_Provider_CheckIn](
	[EmployerID] [int] NOT NULL,
	[ProviderID] [int] NOT NULL,
	[BusinessID] [int] NULL,
	[Check_IN_DateTime] [datetime] NULL,
	[Check_OUT_DateTime] [datetime] NULL,
	[CheckInAckState] [char](10) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
