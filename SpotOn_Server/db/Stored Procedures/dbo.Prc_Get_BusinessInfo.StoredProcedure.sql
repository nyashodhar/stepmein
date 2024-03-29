USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Get_BusinessInfo]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE Proc [dbo].[Prc_Get_BusinessInfo]
(@BusinessID int 
)
AS
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <01-Mar-2012>
-- Description:	Retreiving information of the business based on businessID
-- =============================================

Select 
B.BusinessID,
B.BusinessName,
B.Address1,
B.Address2,
B.City,
B.State,
B.Zip,
B.Phone,
B.BusinessType,
B.Rating
 from Business B with(nolock)
where B.BusinessID = @BusinessID
GO
