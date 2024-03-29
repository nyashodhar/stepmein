USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[RestaurantTbl]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RestaurantTbl](
	[BusinessID] [int] NULL,
	[Cuisine] [varchar](100) NULL,
	[Waittime] [datetime] NULL,
	[Dollarvalue] [varchar](50) NULL,
	[TakeOut] [char](1) NULL,
	[FullBar] [char](1) NULL,
	[BYOB] [char](1) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[RestaurantTbl]  WITH CHECK ADD  CONSTRAINT [FK_RestaurantTbl_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[RestaurantTbl] CHECK CONSTRAINT [FK_RestaurantTbl_Business]
GO
