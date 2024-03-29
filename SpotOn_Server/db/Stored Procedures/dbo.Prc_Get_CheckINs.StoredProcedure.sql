USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Get_CheckINs]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Prc_Get_CheckINs]
(
@UserID int,
@BusinessID int,
@PartySize int
 )          

AS


DECLARE @Upper2 bigINT
DECLARE @Lower2 bigINT 
--- This will create a 8 digit random number 
SET @Lower2 = 10000000 ---- The lowest random number
SET @Upper2 = 99999999 ---- The highest random number

Declare @BigTableID_val bigint   
SELECT @BigTableID_val = ROUND(((@Upper2 - @Lower2 -1) * RAND() + @Lower2), 0)

Declare @ISBlocked int
Set @ISBlocked = (Select COUNT(*) 
				  FROM [SpotOn_DB].[dbo].[Block] Block with(nolock)
				  Where Block.BusinessID = @BusinessID
				  AND Block.UserID = @UserID)

IF(@ISBlocked <= 1)
BEGIN
Print 'abc'
Select 	
	BigTableID = @BigTableID_val,
	ToID = CIO.BusinessID,
	FromID = CIO.UserID,
	Name = RTRIM(Usr.FirstName)+' '+ RTRIM(Usr.LastName),
	PhoneNumber = Usr.PhoneNumber,
	Age = DATEDIFF(yy,Usr.DateOfBirth,GETDATE()),
	DateOfBirth = Usr.DateOfBirth,
	Address = ISNULL(Address1,'') + ISNULL(Address2,''),
	City = Usr.City,
	Zip = Usr.Zip,
	State = Usr.State,
	PartySize2 =  @PartySize,
	CheckinTime = CIO.Check_IN_DateTime
FROM [SpotOn_DB].[dbo].[CheckINOUT] CIO with(nolock)
Inner Join [SpotOn_DB].dbo.[User]  Usr with(nolock)
ON Usr.UserID = CIO.UserID
Where CIO.UserID = @UserID
AND CIO.BusinessID = @BusinessID

END

GO
