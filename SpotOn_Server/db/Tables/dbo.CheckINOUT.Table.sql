USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[CheckINOUT]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CheckINOUT](
	[CheckINOUTID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
	[FacebookID] [varchar](20) NULL,
	[BusinessID] [int] NOT NULL,
	[Check_IN_DateTime] [datetime] NULL,
	[Check_OUT_DateTime] [datetime] NULL,
	[ProviderID] [int] NULL,
	[PartySize] [int] NULL,
	[CurrentDateTime] [datetime] NULL,
	[TypeOfCheckIN] [varchar](50) NULL,
 CONSTRAINT [PK_CheckINOUT] PRIMARY KEY CLUSTERED 
(
	[CheckINOUTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[CheckINOUT] ADD  CONSTRAINT [CURRENT_TIMESTAMP_69700]  DEFAULT (getdate()) FOR [CurrentDateTime]
GO
