USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[Review]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Review](
	[UserID] [int] NOT NULL,
	[BusinessID] [int] NOT NULL,
	[CurrentDateTime] [datetime] NULL,
	[Rating] [float] NULL,
	[ReviewID] [int] NOT NULL,
	[ProviderID] [int] NULL,
 CONSTRAINT [PK_Review] PRIMARY KEY CLUSTERED 
(
	[ReviewID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FK_Review_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FK_Review_Business]
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FK_Review_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FK_Review_User]
GO
