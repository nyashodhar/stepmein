USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Session]    Script Date: 3/22/2013 11:04:47 PM ******/
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

Set nocount on

BEGIN
  INSERT INTO [SpotOn_DB].dbo.Session(UserID,Status,LastActivity_Datetime,BusinessID)
  SELECT @UserID, @Status, @LastActivity_Datetime, @BusinessID

  Select 0
END
GO
