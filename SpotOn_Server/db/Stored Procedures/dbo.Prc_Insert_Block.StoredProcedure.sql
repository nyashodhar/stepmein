USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Block]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <21-Jan-2012>
-- Description:	Block Customer/Business
-- Business Blocks Customer
-- Customer Blocks Business
-- =============================================

CREATE PROC [dbo].[Prc_Insert_Block]
(
@UserID int,
@BusinessID Int,
@Reason varchar(max) = NULL,
@TypeOfBlock	char(10) = NULL,
@WhoBlocks char(10) = NULL
)
AS

SET NOCOUNT ON
BEGIN
INSERT INTO dbo.Block (UserID,BusinessID, Reason, TypeOfBlock, WhoBlocks)
SELECT @UserID, @BusinessID, @Reason, @TypeOfBlock, @WhoBlocks

SELECT 0
END
GO
