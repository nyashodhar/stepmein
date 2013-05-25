USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_UserDetails]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	<User Profile:
-- Get User details like name, email, phone number, address, age, along with number of check-ins, number of reviews.>
-- =============================================

CREATE Proc [dbo].[Prc_UserDetails]
(
@UserID int
)
AS

Declare @TotalNoOfCheckIns int
Set @TotalNoOfCheckIns = ( Select Count(C.CheckINOUTID) From [SpotOn_DB].[dbo].CheckINOUT C with(nolock)
						WHERE C.UserID = @UserID
							)
							
Declare @TotalNoOfReviews int
Set @TotalNoOfReviews = ( Select COUNT(R.ReviewID) FROM 	[SpotOn_DB].[dbo].Review R	with(nolock)
					WHERE R.UserID = @UserID)					

SELECT 
	U.FirstName, U.LastName,
	U.Email,
	PhoneNumber = U.PhoneNumber,
	U.Address1, U.Address2, U.City,
	U.Zip,
	U.DateOfBirth,
	U.State,
	TotalNoOfCheckIns = @TotalNoOfCheckIns,
	TotalNoOfReviews = @TotalNoOfReviews
FROM [SpotOn_DB].[dbo].[User] U with(nolock)
WHERE U.UserID = @UserID

GO
