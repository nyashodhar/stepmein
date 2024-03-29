USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_CheckINOUT]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <03-Feb-2012>
-- Modified Date: 04-Mar-2013 - Yashodhar narvaneni
-- Description:	<User Sign up:Inserting Data into CheckINOUT Table. 
--				All the data will be provided from the application except CheckINOUTID>
-- =============================================
/*
Exec [Prc_Insert_CheckINOUT] 49248341, NULL,272440,NULL,NULL,NULL,5,NULL,NULL,NULL
Select * from  [SpotOn_DB].[dbo].[CheckINOUT] where UserID = 49248341 and BusinessID = 272440
Delete from  [SpotOn_DB].[dbo].[CheckINOUT] where UserID = 49248341 and BusinessID = 272440
*/
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
           ,@CheckINOUTID int = NULL OUTPUT
           )
AS

--BEGIN TRANSACTION
SET NOCOUNT ON;

IF ((@CheckINOUTID IS NULL) OR (@CheckINOUTID = '') OR (@CheckINOUTID = 0))
BEGIN 
		DECLARE @Random BIGINT;
		DECLARE @Upper bigINT;
		DECLARE @Lower bigINT, @CheckINOUTID_New bigint 
		DECLARE @validation int
		--- This will create a 9 digit random number 
		SET @Lower = 100000000 ---- The lowest random number
		SET @Upper = 999999999 ---- The highest random number
		SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
		--RETURN (@Random)

		set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[CheckINOUT] with(nolock) where CheckINOUTID = @Random)

		WHILE ((@validation IS NOT NULL) OR (@validation = 1))
		BEGIN
		SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
		set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[CheckINOUT] with(nolock) where CheckINOUTID = @Random)
		END

		Set @CheckINOUTID = @Random
		Set @CurrentDateTime = (Select GETDATE())

--IF((@CheckINOUTID IS NULL) OR (@CheckINOUTID = '')) AND ((@Check_OUT_DateTime IS NULL) OR (@Check_OUT_DateTime = ''))

--BEGIN
--Print 'abc'
INSERT INTO [SpotOn_DB].[dbo].[CheckINOUT]
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
 
-- SELECT * FROM [CheckINOUT] WHERE businessID = 341514-- UserID =  49248341    
-- COMMIT TRANSACTION

Select @CheckINOUTID --195962071

--Select * from Checkinout where CheckINOUTID =  915795839

END

ELSE
	BEGIN
	--Print 'xyz'
	UPDATE [SpotOn_DB].[dbo].[CheckINOUT] 
	SET [Check_OUT_DateTime] = @Check_OUT_DateTime
	WHERE [CheckINOUTID] = @CheckINOUTID 
	
	DECLARE @RC int
	EXECUTE @RC = [SpotOn_DB].[dbo].[Prc_Get_CheckINs] 
	   @UserID
	  ,@BusinessID
	  ,@PartySize
	END
-- TODO: Set parameter values here.


GO
