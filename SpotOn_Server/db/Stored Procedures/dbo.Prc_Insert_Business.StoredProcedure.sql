USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Business]    Script Date: 3/22/2013 11:04:47 PM ******/
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
           ,@Longitude Float = NULL  -- The default value passed should always be NULL and not ''
           ,@Latitude Float = NULL -- The default value passed should always be NULL and not ''
           ,@Phone bigint = NULL
           ,@CreateDateTime datetime = NULL
           ,@BusinessType varchar(50) = NULL
           ,@City varchar(50) = NULL
           ,@State varchar(50) = NULL
           ,@Rating float = NULL
		   ,@Email Varchar(50) = NULL
		   ,@Password varchar(100) = NULL
		   )

AS
  Set nocount on
DECLARE @Random INT;
DECLARE @Upper INT;
DECLARE @Lower INT, @BusinessID int 
DECLARE @validation int
--- This will create a 6 digit random number 
SET @Lower = 100000 ---- The lowest random number
SET @Upper = 999999 ---- The highest random number
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
--RETURN (@Random)
set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[Business] with(nolock) where BusinessID = @Random)

WHILE ((@validation IS NOT NULL) OR (@validation = 1))
BEGIN
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[CheckINOUT] with(nolock) where CheckINOUTID = @Random)
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
BEGIN
INSERT INTO [SpotOn_DB].[dbo].[Business]
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
		   ,[Rating]
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
		   ,@Rating
		   ,@Email
		   ,@Password 
      )

  Select 0
END
GO
