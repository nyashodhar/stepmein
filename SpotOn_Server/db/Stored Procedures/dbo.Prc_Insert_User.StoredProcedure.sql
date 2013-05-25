USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_User]    Script Date: 3/22/2013 11:04:47 PM ******/
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
@PhoneNumber	bigint = NULL,
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

Set nocount on

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
set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[User] with(nolock) where userID = @Random)

WHILE ((@validation IS NOT NULL) OR (@validation = 1))
BEGIN
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[User] with(nolock) where userID = @Random)
END

Set @UserID = @Random

Declare @validation2 int
Set @validation2 = (SELECT count(Email) from [SpotOn_DB].[dbo].[User] with(nolock) where Email = @Email)


IF((@Email IS NOT NULL) OR (@Email <> ''))
BEGIN
IF((@validation2 IS NULL) OR (@validation2 < 1)) 
BEGIN
INSERT INTO [SpotOn_DB].[dbo].[User](
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

Select 0

END
END


GO
