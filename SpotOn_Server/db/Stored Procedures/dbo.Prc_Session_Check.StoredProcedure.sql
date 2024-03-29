USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Session_Check]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	User Login Check:
-- Check if the User is online or not with the state property and last activity is less than one min.
-- =============================================

CREATE Proc [dbo].[Prc_Session_Check]
(
@UserID int
)
AS

SELECT 
	Status = CASE S.Status WHEN 1 THEN 'Active' 
			ELSE 'Inactive'
			end
FROM [SpotOn_DB].dbo.Session S WITH(NOLOCK)
WHERE  datediff(mm,GETDATE(),LastActivity_Datetime)> 1

GO
