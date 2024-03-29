USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_User_Session]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Feb-2012>
-- Description:	User Login Session:
-- Entry into the User Session - Table: User ID, Last Activity = present Time and IPAddress

-- V001   Sharath D	 13-March-2013	Insert data into [dbo].[UserDevices]
-- =============================================
CREATE Proc [dbo].[Prc_Insert_User_Session]
( @SessionID uniqueidentifier,
  @UserID Int, 
  @IPAddress char(16) = NULL,
  @LastActivity_Datetime datetime = NULL,
  @AppName nvarchar(50),
  @OS nvarchar(50),
  @DeviceID nvarchar(50)
     )
  AS
  Set nocount on

BEGIN
  INSERT INTO [SpotOn_DB].dbo.UserSession(UserSessionID, UserID,IPAddress,LastActivity_Datetime)
  SELECT @SessionID, @UserID, @IPAddress, @LastActivity_Datetime

  INSERT INTO [SpotOn_DB].[dbo].[UserDevices](UserID,AppName,OS,DeviceID)
  Select @UserID,@AppName,@OS,@DeviceID 

  Select 0
END
GO
