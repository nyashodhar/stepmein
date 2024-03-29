USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_CheckOutUser]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <27-Apr-2012>
-- Update Date: 12-Mar-2013
-- Description:	
-- This Procedure takes UserID, BusinessID and CheckoutFlag and updates the checkINOut table to value the Check_OUT_DateTime
-- =============================================
CREATE Proc [dbo].[Prc_CheckOutUser]
(
@UserID int,
@BusinessID int,
@CheckINOUTID int,
@ProviderID int, 
@Checkoutflag int
)
AS

Set nocount on

IF (@ProviderID = -1)
BEGIN
Update dbo.CheckINOUT
Set Check_OUT_DateTime = (Select Getdate())
Where UserID = @UserID
and BusinessID = @BusinessID
and @Checkoutflag = 1
Select 0
END
ELSE
BEGIN
Update dbo.CheckINOUT
Set Check_OUT_DateTime = (Select Getdate())
Where UserID = @UserID
and BusinessID = @BusinessID
and ProviderID = @ProviderID
and @Checkoutflag = 1
Select 0
END




GO
