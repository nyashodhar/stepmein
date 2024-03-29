USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Review]    Script Date: 3/22/2013 11:04:47 PM ******/
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
           ,@Notes varchar(max) = NULL
           ,@Rating varchar(50) = NULL
           ,@ProviderID int
           ,@Review_String varchar(2000)
           ,@ReviewCategory_String varchar(2000) )
AS

Set nocount on

DECLARE @Random INT;
DECLARE @Upper INT;
DECLARE @Lower INT, @ReviewID int 
DECLARE @validation int
--- This will create a 7 digit random number 
SET @Lower = 100000000 ---- The lowest random number
SET @Upper = 999999999 ---- The highest random number
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
--RETURN (@Random)
set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[Review] with(nolock) where ReviewID = @Random)

WHILE ((@validation IS NOT NULL) OR (@validation = 1))
BEGIN
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[Review] with(nolock) where ReviewID = @Random)
END

Set @ReviewID = @Random
Declare @Count int
	SELECT @Count = Count(*)
	FROM Review R2
	WHERE R2.BusinessID = @BusinessID
	AND R2.UserID = @UserID
	
BEGIN

IF (@Count <=1)
BEGIN
	INSERT INTO [SpotOn_DB].[dbo].[Review]
			   ([UserID]
			   ,[BusinessID]
			   ,[CurrentDateTime]
			   ,[Rating]
			   ,[ReviewID]
			   ,[ProviderID]
			   )
		 VALUES
				(@UserID
			   ,@BusinessID 
			   ,@CurrentDateTime 
			   ,@Rating
			   ,@ReviewID 
			   ,@ProviderID 
				 )
END
ELSE
BEGIN
		UPDATE [SpotOn_DB].[dbo].[Review]
		SET 
		 [CurrentDateTime] = @CurrentDateTime
		,[Rating] = @Rating
		,[ReviewID]  = @ReviewID
		,[ProviderID] = @ProviderID	
		WHERE 
		 [UserID] = @UserID
		AND [BusinessID] = @BusinessID
END

BEGIN
INSERT INTO [SpotOn_DB].[dbo].[Review_Details]
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
END

Select 0
END
GO
