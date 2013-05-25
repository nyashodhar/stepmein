USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Check_User_Registered]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Jan-2012>
-- Description:	Check if the user has registered
-- =============================================
CREATE Proc [dbo].[Prc_Check_User_Registered]
(
@Email varchar(50)
)
AS
SELECT Result = case COUNT(*) when 1 then 'Registered'
				else 'Not'
				end
from [User] with(nolock) where Email = @Email;

GO
