
/*-----------------------------------------------------------------------------------------
Version: 3.0
Author: Sharath Dudala
Content: Database scripts(tables and stored procedures)
Stored procedures:
		Prc_Insert_User
		Prc_UserDetails
		Prc_CheckIN_History
		Prc_Active_CheckINs
		Prc_UserCheckINs
		Prc_Insert_Session
		Prc_Session_Check
		Prc_Insert_Block
		Prc_Insert_Business_Provider_CheckIn
		Prc_Insert_CheckINOUT
		Prc_Ack_Business_Provider_CheckIn
		Prc_Insert_Business
		Prc_Insert_Appointment
		Prc_insert_Message
		Prc_Insert_Review
		Prc_Active_User_Session
		Prc_User_Login
		Prc_Check_User_Registered
		Prc_User_Session_Check
		Prc_Insert_User_Session
Tables: 
		Message
		Appointment
		CheckINOUT
		Block
		Session
		User
		Business_Provider_CheckIn
		Business
		Review
		UserSession		

-----------------------------------------------------------------------------------------*/

USE [master]
GO
/****** Object:  Database [BusinessDB]    Script Date: 02/05/2012 21:24:36 ******/
CREATE DATABASE [BusinessDB] ON  PRIMARY 
( NAME = N'BusinessDB', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\BusinessDB.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'BusinessDB_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\BusinessDB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [BusinessDB] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BusinessDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BusinessDB] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [BusinessDB] SET ANSI_NULLS OFF
GO
ALTER DATABASE [BusinessDB] SET ANSI_PADDING OFF
GO
ALTER DATABASE [BusinessDB] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [BusinessDB] SET ARITHABORT OFF
GO
ALTER DATABASE [BusinessDB] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [BusinessDB] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [BusinessDB] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [BusinessDB] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [BusinessDB] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [BusinessDB] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [BusinessDB] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [BusinessDB] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [BusinessDB] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [BusinessDB] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [BusinessDB] SET  DISABLE_BROKER
GO
ALTER DATABASE [BusinessDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [BusinessDB] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [BusinessDB] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [BusinessDB] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [BusinessDB] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [BusinessDB] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [BusinessDB] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [BusinessDB] SET  READ_WRITE
GO
ALTER DATABASE [BusinessDB] SET RECOVERY SIMPLE
GO
ALTER DATABASE [BusinessDB] SET  MULTI_USER
GO
ALTER DATABASE [BusinessDB] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [BusinessDB] SET DB_CHAINING OFF
GO
USE [BusinessDB]
GO
/****** Object:  Table [dbo].[User]    Script Date: 02/05/2012 21:24:37 ******/
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
	[PhoneNumber] [int] NULL,
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
CREATE NONCLUSTERED INDEX [IX_Email] ON [dbo].[User] 
(
	[FirstName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX [IX_Phone] ON [dbo].[User] 
(
	[FirstName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CheckINOUT]    Script Date: 02/05/2012 21:24:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CheckINOUT](
	[CheckINOUTID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
	[FacebookID] [varchar](20) NULL,
	[BusinessID] [int] NOT NULL,
	[Check_IN_DateTime] [datetime] NULL,
	[Check_OUT_DateTime] [datetime] NULL,
	[ProviderID] [int] NULL,
	[PartySize] [int] NULL,
	[CurrentDateTime] [datetime] NULL,
	[TypeOfCheckIN] [varchar](50) NULL,
 CONSTRAINT [PK_CheckINOUT] PRIMARY KEY CLUSTERED 
(
	[CheckINOUTID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Business_Provider_CheckIn]    Script Date: 02/05/2012 21:24:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Business_Provider_CheckIn](
	[EmployerID] [int] NOT NULL,
	[ProviderID] [int] NOT NULL,
	[BusinessID] [int] NULL,
	[Check_IN_DateTime] [datetime] NULL,
	[Check_OUT_DateTime] [datetime] NULL,
	[CheckInAckState] [char](10) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Business]    Script Date: 03/06/2012 18:27:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Business](
	[BusinessID] [int] NOT NULL,
	[BusinessName] [char](50) NULL,
	[Address1] [varchar](50) NULL,
	[Address2] [varchar](50) NULL,
	[Zip] [int] NULL,
	[BusinessAffiliationID] [int] NOT NULL,
	[Longitude] [geography] NULL,
	[Latitude] [geography] NULL,
	[Phone] [bigint] NULL,
	[CreateDateTime] [datetime] NULL,
	[BusinessType] [varchar](50) NULL,
	[City] [varchar](50) NULL,
	[State] [varchar](50) NULL,
        [Rating] [int] NULL,
	[Email] [varchar](50) NULL,
	[Password] [varchar](100) NULL,
 CONSTRAINT [PK_Business] PRIMARY KEY CLUSTERED 
(
	[BusinessID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
CREATE NONCLUSTERED INDEX [IX_BusinessPhone] ON [dbo].[Business] 
(
	[BusinessID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX [IX_Zip] ON [dbo].[Business] 
(
	[BusinessID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Block]    Script Date: 02/05/2012 21:24:37 ******/
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
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Appointment]    Script Date: 02/05/2012 21:24:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Appointment](
	[AppointmentID] [int] NOT NULL,
	[CheckINOUTID] [int] NOT NULL,
	[StartDtTime] [datetime] NULL,
	[EndDtTime] [datetime] NULL,
	[CurrentDateTime] [datetime] NULL,
	[Reminder] [int] NULL,
	[Alert] [int] NULL,
 CONSTRAINT [PK_Appointment] PRIMARY KEY CLUSTERED 
(
	[AppointmentID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Session]    Script Date: 02/05/2012 21:24:37 ******/
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
CREATE NONCLUSTERED INDEX [IX_SessionBusinessID] ON [dbo].[Session] 
(
	[UserID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX [IX_SessionUserID] ON [dbo].[Session] 
(
	[UserID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Review]    Script Date: 02/05/2012 21:24:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Review](
	[UserID] [int] NOT NULL,
	[BusinessID] [int] NOT NULL,
	[CurrentDateTime] [datetime] NULL,
	[Notes] [varchar](max) NULL,
	[Rating] [varchar](50) NULL,
	[ReviewID] [int] NOT NULL,
	[ProviderID] [int] NULL,
	[Review_String] [varchar](2000) NULL,
	[ReviewCategory_String] [varchar](2000) NULL,
 CONSTRAINT [PK_Review] PRIMARY KEY CLUSTERED 
(
	[ReviewID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
CREATE NONCLUSTERED INDEX [IX_ReviewBusinessID] ON [dbo].[Review] 
(
	[UserID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX [IX_ReviewUserID] ON [dbo].[Review] 
(
	[UserID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Ack_Business_Provider_CheckIn]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create Proc [dbo].[Prc_Ack_Business_Provider_CheckIn]
           (@EmployerID int
           ,@ProviderID int
           ,@BusinessID int = NULL
           ,@CheckInAckState Char(10) = NULL)

AS


UPDATE  [BusinessDB].[dbo].[Business_Provider_CheckIn]
       SET    [CheckInAckState] = @CheckInAckState
   
        WHERE  [EmployerID] = @EmployerID
           AND [ProviderID] = @ProviderID
           AND [BusinessID] = @BusinessID
GO
/****** Object:  Table [dbo].[Message]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Message](
	[CheckINOUTID] [int] NOT NULL,
	[Message] [xml] NULL
) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX [IX_MessageCheckINoutID] ON [dbo].[Message] 
(
	[CheckINOUTID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserSession]    Script Date: 02/11/2012 09:50:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UserSession](
	[UserSessionID] [uniqueidentifier] NOT NULL,
	[UserID] [int] NOT NULL,
	[IPAddress] [char](16) NULL,
	[LastActivity_Datetime] [datetime] NULL,
 CONSTRAINT [PK_UserSession] PRIMARY KEY CLUSTERED 
(
	[UserSessionID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[UserSession]  WITH CHECK ADD  CONSTRAINT [FK_UserSession_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[UserSession] CHECK CONSTRAINT [FK_UserSession_User]
GO

/****** Object:  StoredProcedure [dbo].[Prc_CheckIN_History]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	User Check-in History:
-- Get the list of user check-ins where there is both check-in and check-out dates.
-- =============================================

CREATE Proc [dbo].[Prc_CheckIN_History]
( 
@UserID int
)
AS

BEGIN
SELECT 
	CheckINOUTID = C.CheckINOUTID,
	BusinessID = B.BusinessID,
	Address = B.Address1 + ISNULL(B.Address2,''),
	City = B.City,
	State = B.State,
	Zip = B.Zip,
	Longitude = B.Longitude,
	Latitude = B.Latitude,
	Check_IN_DateTime = C.Check_IN_DateTime,
	Check_IN_DateTime = C.Check_OUT_DateTime
INTO #Temp_History
FROM [BusinessDB].[dbo].[User] U with(nolock)
INNER JOIN [BusinessDB].dbo.CheckINOUT C with(nolock)
ON U.UserID = C.UserID
Left outer JOIN [BusinessDB].dbo.Business B with(nolock)
ON B.BusinessID = C.BusinessID
Where (C.Check_OUT_DateTime <> '' OR C.Check_OUT_DateTime IS NOT NULL)
and (C.FacebookID IS NULL or C.FacebookID <> '')

Declare @i int
Set @i = (Select COUNT(CheckINOUTID) from #Temp_History)

Select TotalCheckINs = @i , T.* from #Temp_History T

END
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_CheckINOUT]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <03-Feb-2012>
-- Description:	<User Sign up:Inserting Data into CheckINOUT Table. 
--				All the data will be provided from the application except CheckINOUTID>
-- =============================================

CREATE Proc  [dbo].[Prc_Insert_CheckINOUT]  
		  (
            @UserID int = NULL
           ,@FacebookID varchar(20) = NULL
           ,@BusinessID int = NULL
           ,@Check_IN_DateTime datetime = NULL
           ,@Check_OUT_DateTime datetime = NULL
           ,@ProviderID int = NULL
           ,@PartySize int = NULL
           ,@CurrentDateTime datetime = NULL
           ,@TypeOfCheckIN varchar(50) = NULL
           )
AS

DECLARE @Random BIGINT;
DECLARE @Upper bigINT;
DECLARE @Lower bigINT, @CheckINOUTID bigint 
DECLARE @validation int
--- This will create a 9 digit random number 
SET @Lower = 100000000 ---- The lowest random number
SET @Upper = 999999999 ---- The highest random number
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
--RETURN (@Random)

set @validation = (SELECT 1 from [BusinessDB].[dbo].[CheckINOUT] with(nolock) where CheckINOUTID = @Random)


WHILE ((@validation IS NOT NULL) OR (@validation = 1))
BEGIN
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
set @validation = (SELECT 1 from [BusinessDB].[dbo].[CheckINOUT] with(nolock) where CheckINOUTID = @Random)
END

Set @CheckINOUTID = @Random

INSERT INTO [BusinessDB].[dbo].[CheckINOUT]
           ([CheckINOUTID]
           ,[UserID]
           ,[FacebookID]
           ,[BusinessID]
           ,[Check_IN_DateTime]
           ,[Check_OUT_DateTime]
           ,[ProviderID]
           ,[PartySize]
           ,[CurrentDateTime]
           ,[TypeOfCheckIN])
SELECT
			@CheckINOUTID 
           ,@UserID 
           ,@FacebookID 
           ,@BusinessID 
           ,@Check_IN_DateTime 
           ,@Check_OUT_DateTime 
           ,@ProviderID 
           ,@PartySize 
           ,@CurrentDateTime 
           ,@TypeOfCheckIN
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Business_Provider_CheckIn]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create Proc [dbo].[Prc_Insert_Business_Provider_CheckIn]
           (@EmployerID int
           ,@ProviderID int
           ,@BusinessID int = NULL
           ,@Check_IN_DateTime datetime = NULL
           ,@Check_OUT_DateTime datetime = NULL
           ,@CheckInAckState char(10) = NULL)

AS


INSERT INTO [BusinessDB].[dbo].[Business_Provider_CheckIn]
           ([EmployerID]
           ,[ProviderID]
           ,[BusinessID]
           ,[Check_IN_DateTime]
           ,[Check_OUT_DateTime]
           ,[CheckInAckState])
  
  SELECT
           @EmployerID
           ,@ProviderID 
           ,@BusinessID 
           ,@Check_IN_DateTime 
           ,@Check_OUT_DateTime 
           ,@CheckInAckState
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Business]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <03-Feb-2012>
-- Description:	<User Sign up:Inserting Data into Business Table. 
--				All the data will be provided from the application, except for the BusinessID and TimeStamp>
-- =============================================
CREATE Proc [dbo].[Prc_Insert_Business]
(

            @BusinessName char(50) = NULL
           ,@Address1 varchar(50)= NULL
           ,@Address2 varchar(50)= NULL
           ,@Zip int = NULL
           ,@BusinessAffiliationID int = NULL   ---BusinessAffliation ID will be passed from application
           ,@Longitude geography = NULL  -- The default value passed should always be NULL and not ''
           ,@Latitude geography = NULL -- The default value passed should always be NULL and not ''
           ,@Phone bigint = NULL
           ,@CreateDateTime datetime = NULL
           ,@BusinessType varchar(50) = NULL
           ,@City varchar(50) = NULL
           ,@State varchar(50) = NULL
           ,@Email varchar(50) = NULL
           ,@Password varchar(100) = NULL
           )

AS

DECLARE @Random INT;
DECLARE @Upper INT;
DECLARE @Lower INT, @BusinessID int 
DECLARE @validation int
--- This will create a 6 digit random number 
SET @Lower = 100000 ---- The lowest random number
SET @Upper = 999999 ---- The highest random number
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
--RETURN (@Random)
set @validation = (SELECT 1 from [BusinessDB].[dbo].[Business] with(nolock) where BusinessID = @Random)

WHILE ((@validation IS NOT NULL) OR (@validation = 1))
BEGIN
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
set @validation = (SELECT 1 from [BusinessDB].[dbo].[CheckINOUT] with(nolock) where CheckINOUTID = @Random)
END

Set @BusinessID = @Random

/*
IF((@validation IS NULL) OR (@validation <> 1))
BEGIN
Set @BusinessID = @Random
END
ELSE
BEGIN
SELECT @BusinessID = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
END
*/
INSERT INTO [BusinessDB].[dbo].[Business]
           ([BusinessID]
           ,[BusinessName]
           ,[Address1]
           ,[Address2]
           ,[Zip]
           ,[BusinessAffiliationID]
           ,[Longitude]
           ,[Latitude]
           ,[Phone]
           ,[CreateDateTime]
           ,[BusinessType]
           ,[City]
           ,[State]
           ,[Email]
           ,[Password])
     VALUES
     (      @BusinessID
		   ,@BusinessName 
           ,@Address1 
           ,@Address2 
           ,@Zip 
           ,@BusinessAffiliationID 
           ,@Longitude 
           ,@Latitude 
           ,@Phone 
           ,@CreateDateTime 
           ,@BusinessType 
           ,@City
           ,@State 
           ,@Email
           ,@Password
      )
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_User]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	<User Sign up:Inserting Data into User Table along with User Type. 
--				All the data will be provided from the application, except for the GUID and TimeStamp>
-- =============================================

CREATE Proc [dbo].[Prc_Insert_User]
(
@FirstName	char(50) = NULL,
@MiddleName	char(50)= NULL,
@LastName	char(50)= NULL,
@Email	varchar(50),
@PhoneNumber	int= NULL,
@Password	varchar(50)= NULL,
@TypeOfPlatform	char(4)= NULL,
@FacebookID	varchar(20)= NULL,
@Usertype	varchar(50)= NULL,
@PushNotification	char(1)= NULL,
	@DateOfBirth [datetime]= NULL,
	@Address1 [varchar](100) = NULL,
	@Address2 [varchar](100) = NULL,
	@City [varchar](100) = NULL,
	@Zip [int] = NULL,
	@State [varchar](50) =  NULL
)

AS

DECLARE @Random INT;
DECLARE @Upper INT;
DECLARE @Lower INT
DECLARE @UserID INT
DECLARE @validation int
--- This will create a 8 digit random number 
SET @Lower = 10000000 ---- The lowest random number
SET @Upper = 99999999 ---- The highest random number
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
--RETURN (@Random)
set @validation = (SELECT 1 from [BusinessDB].[dbo].[User] with(nolock) where userID = @Random)

WHILE ((@validation IS NOT NULL) OR (@validation = 1))
BEGIN
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
set @validation = (SELECT 1 from [BusinessDB].[dbo].[User] with(nolock) where userID = @Random)
END

Set @UserID = @Random

Declare @validation2 int
Set @validation2 = (SELECT count(Email) from [BusinessDB].[dbo].[User] with(nolock) where Email = @Email)


IF((@Email IS NOT NULL) OR (@Email <> ''))
BEGIN
IF((@validation2 IS NULL) OR (@validation2 < 1)) 
BEGIN
INSERT INTO [BusinessDB].[dbo].[User](
FirstName,
MiddleName,
LastName,
Email,
PhoneNumber,
Password,
TypeOfPlatform,
FacebookID,
UserID,
Usertype,
PushNotification,
DateOfBirth,
Address1,
Address2,
City,
Zip,
State)

VALUES
(
@FirstName,
@MiddleName,
@LastName,
@Email,
@PhoneNumber,
@Password,
@TypeOfPlatform,
@FacebookID,
@UserID,
@Usertype,
@PushNotification,
@DateOfBirth,
@Address1,
@Address2,
@City,
@Zip,
@State)
END
END
GO
/****** Object:  StoredProcedure [dbo].[Prc_UserCheckINs]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	User List of Check-ins:
-- Get all the check-ins by the user despite check-out date
-- =============================================


Create Proc [dbo].[Prc_UserCheckINs]
(
@UserID int
)
AS


SELECT 
	Name = U.FirstName + U.LastName,
	Email = U.Email,
	PhoneNumber = U.PhoneNumber,
	BusinessName = B.BusinessName,
	CheckINTime = C.Check_IN_DateTime,
	CheckOUTTime = C.Check_OUT_DateTime
FROM [BusinessDB].[dbo].[User] U with(nolock)
Inner join [BusinessDB].dbo.CheckINOUT C with(nolock)
ON C.UserID = U.userID
Inner join [BusinessDB].dbo.Business B with(nolock)
ON B.BusinessID = C.BusinessID
WHERE U.UserID = @UserID
Order by CheckINTime desc
GO
/****** Object:  StoredProcedure [dbo].[Prc_Session_Check]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	User Login Check:
-- Check if the User is online or not with the state property and last activity is less than one min.
-- =============================================

CREATE Proc [dbo].[Prc_Session_Check]
(
@UserID int
)
AS

SELECT 
	Status = CASE S.Status WHEN 1 THEN 'Active' 
			ELSE 'Inactive'
			end
FROM [BusinessDB].dbo.Session S WITH(NOLOCK)
WHERE  datediff(mm,GETDATE(),LastActivity_Datetime)> 1
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Session]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	User Login:
-- Entry into the Login-Table: User ID, Time Stamp, Last Activity = present Time and online state = true
-- =============================================

CREATE Proc [dbo].[Prc_Insert_Session]
( @UserID Int, 
  @Status nchar(10) = NULL,
  @LastActivity_Datetime datetime = NULL,
  @BusinessID int = NULL
  )
  AS
  INSERT INTO [BusinessDB].dbo.Session(UserID,Status,LastActivity_Datetime,BusinessID)
  SELECT @UserID, @Status, @LastActivity_Datetime, @BusinessID
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Review]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <03-Feb-2012>
-- Description:	<User Sign up:Inserting Data into Review Table. 
--				All the data will be provided from the application except ReviewID>
-- =============================================
CREATE Proc [dbo].[Prc_Insert_Review]
           (@UserID int = NULL
           ,@BusinessID int = NULL
           ,@CurrentDateTime datetime = NULL
           ,@Notes varchar = NULL
           ,@Rating varchar(50) = NULL
           ,@ProviderID int
           ,@Review_String varchar(2000)
           ,@ReviewCategory_String varchar(2000) )
AS
DECLARE @Random INT;
DECLARE @Upper INT;
DECLARE @Lower INT, @ReviewID int 
DECLARE @validation int
--- This will create a 6 digit random number 
SET @Lower = 1000000 ---- The lowest random number
SET @Upper = 9999999 ---- The highest random number
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
--RETURN (@Random)
set @validation = (SELECT 1 from [BusinessDB].[dbo].[Review] with(nolock) where ReviewID = @Random)

WHILE ((@validation IS NOT NULL) OR (@validation = 1))
BEGIN
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
set @validation = (SELECT 1 from [BusinessDB].[dbo].[Review] with(nolock) where ReviewID = @Random)
END

Set @ReviewID = @Random


INSERT INTO [BusinessDB].[dbo].[Review]
           ([UserID]
           ,[BusinessID]
           ,[CurrentDateTime]
           ,[Notes]
           ,[Rating]
           ,[ReviewID]
           ,[ProviderID]
           ,[Review_String]
           ,[ReviewCategory_String])
     VALUES
			(@UserID
           ,@BusinessID 
           ,@CurrentDateTime 
           ,@Notes 
           ,@Rating
           ,@ReviewID 
           ,@ProviderID 
           ,@Review_String
           ,@ReviewCategory_String  )
GO
/****** Object:  StoredProcedure [dbo].[Prc_insert_Message]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <03-Feb-2012>
-- Description:	<User Sign up:Inserting Data into Message Table. 
--				All the data will be provided from the application>
-- =============================================
Create Proc [dbo].[Prc_insert_Message]
(
@CheckINOUTID int,
@Message XML = NULL
)
AS
INSERT INTO [BusinessDB].[dbo].[Message]
           ([CheckINOUTID]
           ,[Message])
     VALUES
           (@CheckINOUTID
           ,@Message)
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Block]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	Block Customer/Business
-- Business Blocks Customer
-- Customer Blocks Business
-- =============================================

CREATE PROC [dbo].[Prc_Insert_Block]
(
@UserID int,
@BusinessID Int,
@Reason varchar(max) = NULL,
@TypeOfBlock	char(10) = NULL,
@WhoBlocks char(10) = NULL
)
AS

INSERT INTO dbo.Block (UserID,BusinessID, Reason, TypeOfBlock, WhoBlocks)
SELECT @UserID, @BusinessID, @Reason, @TypeOfBlock, @WhoBlocks
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Appointment]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <03-Feb-2012>
-- Description:	<User Sign up:Inserting Data into Appointment Table. 
--				All the data will be provided from the application>
-- =============================================
CREATE Proc [dbo].[Prc_Insert_Appointment]
(
		    @CheckINOUTID int
           ,@StartDtTime datetime
           ,@EndDtTime datetime
           ,@CurrentDateTime datetime
           ,@Reminder int
           ,@Alert int
)

AS

DECLARE @Random INT;
DECLARE @Upper INT;
DECLARE @Lower INT, @AppointmentID int 
DECLARE @validation int
--- This will create a 7 digit random number 
SET @Lower = 10000000 ---- The lowest random number
SET @Upper = 99999999 ---- The highest random number
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
--RETURN (@Random)
set @validation = (SELECT 1 from [BusinessDB].[dbo].[Business] with(nolock) where BusinessID = @Random)

WHILE ((@validation IS NOT NULL) OR (@validation = 1))
BEGIN
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
set @validation = (SELECT 1 from [BusinessDB].[dbo].[CheckINOUT] with(nolock) where CheckINOUTID = @Random)
END

Set @AppointmentID = @Random


INSERT INTO [BusinessDB].[dbo].[Appointment]
           ([AppointmentID]
           ,[CheckINOUTID]
           ,[StartDtTime]
           ,[EndDtTime]
           ,[CurrentDateTime]
           ,[Reminder]
           ,[Alert])
     VALUES
           (
           @AppointmentID
           ,@CheckINOUTID
           ,@StartDtTime
           ,@EndDtTime
           ,@CurrentDateTime
           ,@Reminder
           ,@Alert
         )
GO
/****** Object:  StoredProcedure [dbo].[Prc_Active_CheckINs]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	User Active Check-ins:
-- Get the whole list of user check-ins where there is only check-in date
-- =============================================

CREATE Proc [dbo].[Prc_Active_CheckINs]
(
  @UserID int = NULL
)
AS

BEGIN

SELECT 
	BusinessName = B.BusinessName,
	BusinessID = B.BusinessID,
	Address = B.Address1 + ISNULL(B.Address2,''),
	City = B.City,
	State = B.State,
	Zip = B.Zip,
	Longitude = B.Longitude,
	Latitude = B.Latitude
FROM [BusinessDB].[dbo].[User] U with(nolock)
INNER JOIN [BusinessDB].dbo.CheckINOUT C with(nolock)
ON U.UserID = C.UserID
INNER JOIN [BusinessDB].dbo.Session S with(nolock)
ON U.userid = s.userid
Left outer join [BusinessDB].dbo.Business B with(nolock)
ON B.BusinessID = C.BusinessID
WHERE  S.status = 1 -- 1 is active, 0 is inactive,   
AND C.BusinessID NOT IN (Select distinct BusinessID from dbo.Block where userID = @UserID)

END
GO
/****** Object:  StoredProcedure [dbo].[Prc_UserDetails]    Script Date: 02/05/2012 21:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	<User Profile:
-- Get User details like name, email, phone number, address, age, along with number of check-ins, number of reviews.>
-- =============================================

CREATE Proc [dbo].[Prc_UserDetails]
(
@UserID int
)
AS

Declare @TotalNoOfCheckIns int
Set @TotalNoOfCheckIns = ( Select Count(C.CheckINOUTID) From [BusinessDB].[dbo].CheckINOUT C with(nolock)
						WHERE C.UserID = @UserID
							)
							
Declare @TotalNoOfReviews int
Set @TotalNoOfReviews = ( Select COUNT(R.ReviewID) FROM 	[BusinessDB].[dbo].Review R	with(nolock)
					WHERE R.UserID = @UserID)					

SELECT 
	Name = U.FirstName + U.LastName,
	Email = U.Email,
	PhoneNumber = U.PhoneNumber,
	Address = U.Address1 + ISNULL(U.Address2,'')+ U.City,
	Zip = U.Zip,
	Age = Datediff(yy,U.DateOfBirth,getdate()),
	TotalNoOfCheckIns = @TotalNoOfCheckIns,
	TotalNoOfReviews = @TotalNoOfReviews
FROM [BusinessDB].[dbo].[User] U with(nolock)
WHERE U.UserID = @UserID
GO
/****** Object:  Default [CURRENT_TIMESTAMP_697]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [CURRENT_TIMESTAMP_697]  DEFAULT (getdate()) FOR [CreateDateTime]
GO
/****** Object:  Default [CURRENT_TIMESTAMP_69700]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[CheckINOUT] ADD  CONSTRAINT [CURRENT_TIMESTAMP_69700]  DEFAULT (getdate()) FOR [CurrentDateTime]
GO
/****** Object:  Default [CURRENT_TIMESTAMP_12]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Block] ADD  CONSTRAINT [CURRENT_TIMESTAMP_12]  DEFAULT (getdate()) FOR [CurrentDateTime]
GO
/****** Object:  Default [CURRENT_TIMESTAMP_123]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Appointment] ADD  CONSTRAINT [CURRENT_TIMESTAMP_123]  DEFAULT (getdate()) FOR [CurrentDateTime]
GO
/****** Object:  Default [CURRENT_TIMESTAMP_1234]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Session] ADD  CONSTRAINT [CURRENT_TIMESTAMP_1234]  DEFAULT (getdate()) FOR [CurrentDatetime]
GO
/****** Object:  ForeignKey [FK_Block_Business]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Block]  WITH CHECK ADD  CONSTRAINT [FK_Block_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[Block] CHECK CONSTRAINT [FK_Block_Business]
GO
/****** Object:  ForeignKey [FK_Block_User]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Block]  WITH CHECK ADD  CONSTRAINT [FK_Block_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Block] CHECK CONSTRAINT [FK_Block_User]
GO
/****** Object:  ForeignKey [FK_Appointment_CheckINOUT]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Appointment]  WITH CHECK ADD  CONSTRAINT [FK_Appointment_CheckINOUT] FOREIGN KEY([CheckINOUTID])
REFERENCES [dbo].[CheckINOUT] ([CheckINOUTID])
GO
ALTER TABLE [dbo].[Appointment] CHECK CONSTRAINT [FK_Appointment_CheckINOUT]
GO
/****** Object:  ForeignKey [FK_Session_Business]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_Business]
GO
/****** Object:  ForeignKey [FK_Session_User]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_User]
GO
/****** Object:  ForeignKey [FK_Review_Business]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FK_Review_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FK_Review_Business]
GO
/****** Object:  ForeignKey [FK_Review_User]    Script Date: 02/05/2012 21:24:37 ******/
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FK_Review_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FK_Review_User]
GO
/****** Object:  ForeignKey [FK_Message_CheckINOUT]    Script Date: 02/05/2012 21:24:40 ******/
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK_Message_CheckINOUT] FOREIGN KEY([CheckINOUTID])
REFERENCES [dbo].[CheckINOUT] ([CheckINOUTID])
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK_Message_CheckINOUT]
GO

/****** Object:  StoredProcedure [dbo].[Prc_Insert_User_Session]    Script Date: 02/11/2012 11:16:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Feb-2012>
-- Description:	User Login Session:
-- Entry into the User Session - Table: User ID, Last Activity = present Time and IPAddress
-- =============================================
CREATE Proc [dbo].[Prc_Insert_User_Session]
( @SessionID uniqueidentifier,
  @UserID Int, 
  @IPAddress char(16) = NULL,
  @LastActivity_Datetime datetime = NULL
  )
  AS
  INSERT INTO [BusinessDB].dbo.UserSession(UserSessionID, UserID,IPAddress,LastActivity_Datetime)
  SELECT @SessionID, @UserID, @IPAddress, @LastActivity_Datetime

GO

/****** Object:  StoredProcedure [dbo].[Prc_User_Session_Check]    Script Date: 02/11/2012 10:01:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Feb-2012>
-- Description:	User Login Check:
-- Check if the User is online or not with the state property and last activity is less than one min.
-- =============================================
CREATE Proc [dbo].[Prc_User_Session_Check]
(
@UserID int
)
AS
SELECT 
	Status = 'Active'
FROM [BusinessDB].dbo.UserSession S WITH(NOLOCK)
WHERE  datediff(mm,GETDATE(),LastActivity_Datetime)> 1
GO
/****** Object:  StoredProcedure [dbo].[Prc_Check_User_Registered]    Script Date: 02/11/2012 10:08:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Jan-2012>
-- Description:	Check if the user has registered
-- =============================================
CREATE Proc [dbo].[Prc_Check_User_Registered]
(
@Email varchar(50)
)
AS
SELECT Result = case COUNT(*) when 1 then 'Registered'
				else 'Not'
				end
from [User] with(nolock) where Email = @Email;
GO
/****** Object:  StoredProcedure [dbo].[Prc_User_Login]    Script Date: 02/11/2012 10:08:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Jan-2012>
-- Description:	Check if the user table
-- for correct username and password
-- =============================================
CREATE Proc [dbo].[Prc_User_Login]
(
@Email varchar(50),
@Password varchar(50)
)
AS
SELECT UserID
from [User] with(nolock) where Email = @Email and 
Password = @Password;
GO

/****** Object:  StoredProcedure [dbo].[Prc_Active_User_Session]    Script Date: 02/11/2012 10:08:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Jan-2012>
-- Description:	Check if the session is active
-- in the UserSession table.
-- =============================================
CREATE Proc [dbo].[Prc_Active_User_Session]
(
@Email varchar(50),
@SessionID uniqueidentifier
)
AS
SELECT *
FROM [UserSession]
WHERE [UserSessionID] = @SessionID
AND UserID in (
SELECT UserID
FROM [User] where Email = @Email);
GO

USE [BusinessDB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Business_Login]    Script Date: 03/07/2012 23:04:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Proc [dbo].[Prc_Business_Login]
(
@Email varchar(50),
@Password varchar(100)
)
AS
SELECT BusinessID
from [Business] with(nolock) where Email = @Email and 
Password = @Password;
GO

USE [BusinessDB]
GO
/****** Object:  Table [dbo].[BusinessSession]    Script Date: 03/07/2012 23:13:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BusinessSession](
	[BusinessSessionID] [uniqueidentifier] NOT NULL,
	[BusinessID] [int] NOT NULL,
	[IPAddress] [char](16) NULL,
	[LastActivity_Datetime] [datetime] NULL,
 CONSTRAINT [PK_BusinessSession] PRIMARY KEY CLUSTERED 
(
	[BusinessSessionID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[BusinessSession]  WITH CHECK ADD  CONSTRAINT [FK_BusinessSession_Business] FOREIGN KEY([BusinessID])
REFERENCES [dbo].[Business] ([BusinessID])
GO
ALTER TABLE [dbo].[BusinessSession] CHECK CONSTRAINT [FK_BusinessSession_Business]
GO

USE [BusinessDB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Business_Session]    Script Date: 03/07/2012 23:17:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Proc [dbo].[Prc_Insert_Business_Session]
( @SessionID uniqueidentifier,
  @BusinessID Int, 
  @IPAddress char(16) = NULL,
  @LastActivity_Datetime datetime = NULL
  )
  AS
  INSERT INTO [BusinessDB].dbo.BusinessSession(BusinessSessionID, BusinessID,IPAddress,LastActivity_Datetime)
  SELECT @SessionID, @BusinessID, @IPAddress, @LastActivity_Datetime
GO
USE [BusinessDB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Active_Business_Session]    Script Date: 03/07/2012 23:22:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Proc [dbo].[Prc_Active_Business_Session]
(
@Email varchar(50),
@SessionID uniqueidentifier
)
AS
select * from BusinessSession
inner join Business on Business.BusinessID = businessSession.BusinessID
where Business.Email = @Email;
GO

USE [BusinessDB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Check_Business_Registered]    Script Date: 03/07/2012 23:38:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Jan-2012>
-- Description:	Check if the Business has registered
-- =============================================
CREATE Proc [dbo].[Prc_Check_Business_Registered]
(
@Email varchar(50)
)
AS
SELECT Result = case COUNT(*) when 1 then 'Registered'
				else 'Not'
				end
from [Business] with(nolock) where Email = @Email;
GO