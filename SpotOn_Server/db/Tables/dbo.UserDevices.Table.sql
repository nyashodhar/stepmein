USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[UserDevices]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserDevices](
	[UserID] [int] NOT NULL,
	[AppName] [nvarchar](50) NOT NULL,
	[OS] [nvarchar](50) NOT NULL,
	[DeviceID] [nvarchar](50) NOT NULL
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[UserDevices]  WITH CHECK ADD  CONSTRAINT [FK_UserDevices_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[UserDevices] CHECK CONSTRAINT [FK_UserDevices_User]
GO
