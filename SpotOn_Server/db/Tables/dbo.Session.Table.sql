USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[Session]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Session](
	[UserID] [int] NOT NULL,
	[Status] [nchar](10) NULL,
	[LastActivity_Datetime] [datetime] NULL,
	[BusinessID] [int] NOT NULL,
	[CurrentDatetime] [datetime] NULL
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Session] ADD  CONSTRAINT [CURRENT_TIMESTAMP_1234]  DEFAULT (getdate()) FOR [CurrentDatetime]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_Business]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_User]
GO
