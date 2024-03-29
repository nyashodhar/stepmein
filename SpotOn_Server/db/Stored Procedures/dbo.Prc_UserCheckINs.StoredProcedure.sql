USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_UserCheckINs]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	User List of Check-ins:
-- Get all the check-ins by the user despite check-out date
-- =============================================


CREATE Proc [dbo].[Prc_UserCheckINs]
(
@UserID int
)
AS


SELECT 
	Name = U.FirstName + U.LastName,
	Email = U.Email,
	PhoneNumber = U.PhoneNumber,
	BusinessName = B.BusinessName,
	CheckINTime = C.Check_IN_DateTime,
	CheckOUTTime = C.Check_OUT_DateTime
FROM [SpotOn_DB].[dbo].[User] U with(nolock)
Inner join [SpotOn_DB].dbo.CheckINOUT C with(nolock)
ON C.UserID = U.userID
Inner join [SpotOn_DB].dbo.Business B with(nolock)
ON B.BusinessID = C.BusinessID
WHERE U.UserID = @UserID
Order by CheckINTime desc
GO
