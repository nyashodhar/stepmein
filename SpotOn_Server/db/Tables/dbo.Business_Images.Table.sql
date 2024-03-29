USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[Business_Images]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Business_Images](
	[BusinessID] [int] NOT NULL,
	[Image_Path] [varchar](max) NULL,
 CONSTRAINT [PKI_Business] PRIMARY KEY CLUSTERED 
(
	[BusinessID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Business_Images]  WITH CHECK ADD  CONSTRAINT [FK_BusinessImagesTbl_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[Business_Images] CHECK CONSTRAINT [FK_BusinessImagesTbl_Business]
GO
