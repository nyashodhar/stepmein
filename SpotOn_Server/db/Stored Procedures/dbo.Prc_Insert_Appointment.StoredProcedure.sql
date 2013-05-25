USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Appointment]    Script Date: 3/22/2013 11:04:47 PM ******/
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


Set nocount ON

DECLARE @Random INT;
DECLARE @Upper INT;
DECLARE @Lower INT, @AppointmentID int 
DECLARE @validation int
--- This will create a 7 digit random number 
SET @Lower = 10000000 ---- The lowest random number
SET @Upper = 99999999 ---- The highest random number
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
--RETURN (@Random)
set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[Business] with(nolock) where BusinessID = @Random)

WHILE ((@validation IS NOT NULL) OR (@validation = 1))
BEGIN
SELECT @Random = ROUND(((@Upper - @Lower -1) * RAND() + @Lower), 0)
set @validation = (SELECT 1 from [SpotOn_DB].[dbo].[CheckINOUT] with(nolock) where CheckINOUTID = @Random)
END

Set @AppointmentID = @Random

BEGIN

INSERT INTO [SpotOn_DB].[dbo].[Appointment]
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

Select 0

END

GO
