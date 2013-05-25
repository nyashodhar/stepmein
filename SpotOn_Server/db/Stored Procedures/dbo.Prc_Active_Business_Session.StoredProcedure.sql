USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Active_Business_Session]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Proc [dbo].[Prc_Active_Business_Session]
(
@Email varchar(50),
@SessionID uniqueidentifier
)
AS
SELECT * from [Session]
inner join Business on Business.BusinessID = [Session].[BusinessID]
Inner join UserSession ON [UserSession].[UserID] = [Session].[UserID]
where Business.Email = @Email
and UserSession.UserSessionID = @SessionID
GO
