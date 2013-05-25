USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[BigTable_Main]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BigTable_Main](
	[BigTableID] [bigint] NOT NULL,
	[ToID] [int] NULL,
	[FromID] [int] NULL,
	[Message] [varchar](max) NULL,
	[CheckINTime] [datetime] NULL,
	[Reservation_DateTime] [datetime] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
