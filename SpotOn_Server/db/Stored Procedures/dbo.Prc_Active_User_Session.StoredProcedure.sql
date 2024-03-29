USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Active_User_Session]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Jan-2012>
-- Description:	Check if the session is active
-- in the UserSession table.
-- =============================================
CREATE Proc [dbo].[Prc_Active_User_Session]
(
@UserID varchar(50),
@SessionID uniqueidentifier
)
AS

IF (@UserID IS NOT NULL) OR (@UserID <>'')
BEGIN
	IF (@SessionID IS NOT NULL) OR (@SessionID <> '')
	BEGIN
	SELECT *
	FROM [UserSession] UsrS with(nolock) -- avoids blocking
	Inner join [User]Usr with(nolock)
	ON UsrS.UserID = Usr.UserID
	WHERE UsrS.UserSessionID = @SessionID
	AND Usr.UserID = @UserID

	END
END

GO
