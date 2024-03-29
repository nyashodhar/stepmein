USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_CheckIN_History]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	User Check-in History:
-- Get the list of user check-ins where there is both check-in and check-out dates.
-- =============================================

-- Exec [Prc_CheckIN_History] '10108390'

CREATE Proc [dbo].[Prc_CheckIN_History]
( 
@UserID int
)
AS

BEGIN
SELECT 
	CheckINOUTID = C.CheckINOUTID,
	BusinessID = B.BusinessID,
	BusinessName = B.BusinessName,
	BusinessType = B.BusinessType,
	Check_IN_DateTime = C.Check_IN_DateTime,
	Check_OUT_DateTime = C.Check_OUT_DateTime
FROM [SpotOn_DB].[dbo].[User] U with(nolock)
INNER JOIN [SpotOn_DB].dbo.CheckINOUT C with(nolock)
ON U.UserID = C.UserID
Left outer JOIN [SpotOn_DB].dbo.Business B with(nolock)
ON B.BusinessID = C.BusinessID
Where (C.Check_OUT_DateTime <> '' OR C.Check_OUT_DateTime IS NOT NULL)
and (C.FacebookID IS NULL or C.FacebookID <> '')
and U.UserID = @UserID
/*
Declare @i int
Set @i = (Select COUNT(CheckINOUTID) from #Temp_History)

Select TotalCheckINs = @i , T.* from #Temp_History T
*/

END

GO
