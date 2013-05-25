USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[Block]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Block](
	[UserID] [int] NOT NULL,
	[BusinessID] [int] NOT NULL,
	[CurrentDateTime] [datetime] NULL,
	[Reason] [varchar](max) NULL,
	[TypeOfBlock] [char](10) NULL,
	[WhoBlocks] [char](10) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Block] ADD  CONSTRAINT [CURRENT_TIMESTAMP_12]  DEFAULT (getdate()) FOR [CurrentDateTime]
GO
ALTER TABLE [dbo].[Block]  WITH CHECK ADD  CONSTRAINT [FK_Block_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[Block] CHECK CONSTRAINT [FK_Block_Business]
GO
ALTER TABLE [dbo].[Block]  WITH CHECK ADD  CONSTRAINT [FK_Block_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Block] CHECK CONSTRAINT [FK_Block_User]
GO
