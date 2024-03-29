USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Get_Businesses_By_User]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Yashodhar Narvaneni>
-- Create date: <12-Feb-2012>
-- Description:	Retrieving Businesses by Business Name
-- =============================================
--Exec [spotOn_DB].dbo.[Prc_Get_Businesses_By_User] 69288167

CREATE Proc [dbo].[Prc_Get_Businesses_By_User]
( 
@UserID int
)
AS

/*
	

	CREATE TABLE #BlockedBusinesses
	(
	[BusinessID] int
	)
	

	INSERT INTO #BlockedBusinesses([BusinessID])
	SELECT a.BusinessID
	FROM
	Block a
	WHERE UserID = @UserID

	*/
	SELECT   a.BusinessID, a.BusinessName, a.Address1, a.Address2, a.Zip, 
			a.Phone, a.BusinessType, a.City, a.State --, a.Rating
	FROM
		Business a

	WHERE a.BusinessID NOT IN ( 	SELECT a.BusinessID
										FROM
										Block a
										WHERE UserID = @UserID
								)

/*
	LEFT OUTER JOIN
		#BlockedBusinesses b
	ON
		a.BusinessID = b.BusinessID
	WHERE
		b.BusinessID IS NULL
	
	*/


GO
