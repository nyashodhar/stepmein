USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[Message]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Message](
	[CheckINOUTID] [int] NOT NULL,
	[Message] [xml] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK_Message_CheckINOUT] FOREIGN KEY([CheckINOUTID])
REFERENCES [dbo].[CheckINOUT] ([CheckINOUTID])
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK_Message_CheckINOUT]
GO
