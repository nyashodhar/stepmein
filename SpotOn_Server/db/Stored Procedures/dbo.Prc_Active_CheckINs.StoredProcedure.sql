USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Active_CheckINs]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	User Active Check-ins:
-- Get the whole list of user check-ins where there is only check-in date
-- =============================================

CREATE Proc [dbo].[Prc_Active_CheckINs]
(
  @UserID int = NULL
)
AS

BEGIN

SELECT 
	BusinessName = B.BusinessName,
	BusinessID = B.BusinessID,
	Address = B.Address1 + ISNULL(B.Address2,''),
	City = B.City,
	State = B.State,
	Zip = B.Zip,
	Longitude = B.Longitude,
	Latitude = B.Latitude
FROM [SpotOn_DB].[dbo].[User] U with(nolock)
INNER JOIN [SpotOn_DB].dbo.CheckINOUT C with(nolock)
ON U.UserID = C.UserID
INNER JOIN [SpotOn_DB].dbo.Session S with(nolock)
ON U.userid = s.userid
Left outer join [SpotOn_DB].dbo.Business B with(nolock)
ON B.BusinessID = C.BusinessID
WHERE  S.status = 1 -- 1 is active, 0 is inactive,   
AND C.BusinessID NOT IN (Select distinct BusinessID from dbo.Block where userID = @UserID)

END

GO
