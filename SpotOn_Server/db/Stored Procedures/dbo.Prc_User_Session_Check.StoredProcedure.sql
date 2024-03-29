USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_User_Session_Check]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Feb-2012>
-- Description:	User Login Check:
-- Check if the User is online or not with the state property and last activity is less than one min.
-- =============================================
CREATE Proc [dbo].[Prc_User_Session_Check]
(
@UserID int
)
AS
SELECT 
	Status = 'Active'
FROM [SpotOn_DB].dbo.UserSession S WITH(NOLOCK)
WHERE  datediff(mm,GETDATE(),LastActivity_Datetime)> 1

GO
