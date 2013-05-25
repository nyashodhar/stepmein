USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[User]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[User](
	[FirstName] [char](50) NULL,
	[MiddleName] [char](50) NULL,
	[LastName] [char](50) NULL,
	[Email] [varchar](50) NULL,
	[PhoneNumber] [bigint] NULL,
	[Password] [varchar](50) NULL,
	[TypeOfPlatform] [char](4) NULL,
	[FacebookID] [varchar](20) NULL,
	[UserID] [int] NOT NULL,
	[CreateDateTime] [datetime] NULL,
	[Usertype] [varchar](50) NULL,
	[PushNotification] [char](1) NULL,
	[DateOfBirth] [datetime] NULL,
	[Address1] [varchar](100) NULL,
	[Address2] [varchar](100) NULL,
	[City] [varchar](100) NULL,
	[Zip] [int] NULL,
	[State] [varchar](50) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [CURRENT_TIMESTAMP_697]  DEFAULT (getdate()) FOR [CreateDateTime]
GO
