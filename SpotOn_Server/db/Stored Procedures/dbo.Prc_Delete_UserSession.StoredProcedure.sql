USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Delete_UserSession]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Proc [dbo].[Prc_Delete_UserSession]
(
@SessionID uniqueidentifier,
@UserID Varchar(50)
)
AS

Set nocount on
BEGIN

Delete from  [SpotOn_DB].[dbo].[UserSession]
from  [SpotOn_DB].[dbo].[UserSession] UserSession
INNER JOIN [dbo].[User] Usr
ON UserSession.UserID = Usr.UserID
Where UserSession.UserSessionID = @SessionID
and Usr.UserID = @UserID

Select 0
END
GO
