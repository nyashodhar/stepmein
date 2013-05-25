USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_BigTable_Main]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <11-Mar-2013>
-- Description:	<Inserting Data into [BigTable_Main] Table. 
--				All the data will be provided from the application except [BigTableID]
-- =============================================

CREATE Proc [dbo].[Prc_Insert_BigTable_Main]
	   (
	   @ToID int = NULL
      ,@FromID int = NULL
      ,@Message varchar(max) = NULL
      ,@CheckINTime datetime = NULL
	  ,@Reservation_DateTime datetime = NULL
	  )

AS

Set Nocount on

BEGIN
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

Declare @BigTableID bigint

Set @BigTableID = @Random


INSERT INTO [SpotOn_DB].[dbo].[BigTable_Main]
	  ( 
	   [BigTableID]
      ,[ToID]
      ,[FromID]
      ,[Message]
      ,[CheckINTime]
	  ,[Reservation_DateTime]
	  )
Values
 (
 		 @BigTableID
		,@ToID
		,@FromID
		,@Message
		,@CheckINTime
		,@Reservation_DateTime

)


Select 0

END
GO
