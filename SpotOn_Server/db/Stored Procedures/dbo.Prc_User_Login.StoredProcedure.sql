USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_User_Login]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Kunal Jain>
-- Create date: <11-Jan-2012>
-- Description:	Check if the user table
-- for correct username and password
-- =============================================
CREATE Proc [dbo].[Prc_User_Login]
(
@Email varchar(50),
@Password varchar(50)
)
AS
SELECT UserID
from [User] with(nolock) where Email = @Email and 
Password = @Password;

GO
