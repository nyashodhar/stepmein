USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_insert_Message]    Script Date: 3/22/2013 11:04:47 PM ******/
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
CREATE Proc [dbo].[Prc_insert_Message]
(
@CheckINOUTID int,
@Message XML = NULL
)
AS

Set nocount on

BEGIN

INSERT INTO [SpotOn_DB].[dbo].[Message]
           ([CheckINOUTID]
           ,[Message])
     VALUES
           (@CheckINOUTID
           ,@Message)

Select 0
END
GO
