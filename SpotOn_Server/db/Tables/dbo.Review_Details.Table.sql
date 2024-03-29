USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[Review_Details]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Review_Details](
	[UserID] [int] NOT NULL,
	[BusinessID] [int] NOT NULL,
	[CurrentDateTime] [datetime] NULL,
	[Notes] [varchar](max) NULL,
	[Rating] [float] NULL,
	[ReviewID] [int] NOT NULL,
	[ProviderID] [int] NULL,
	[Review_String] [varchar](2000) NULL,
	[ReviewCategory_String] [varchar](2000) NULL,
 CONSTRAINT [PK_Review_Details] PRIMARY KEY CLUSTERED 
(
	[ReviewID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Review_Details]  WITH CHECK ADD  CONSTRAINT [FK_Review_Details_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[Review_Details] CHECK CONSTRAINT [FK_Review_Details_Business]
GO
ALTER TABLE [dbo].[Review_Details]  WITH CHECK ADD  CONSTRAINT [FK_Review_Details_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Review_Details] CHECK CONSTRAINT [FK_Review_Details_User]
GO
