USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Get_Businesses]    Script Date: 3/22/2013 11:04:47 PM ******/
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

CREATE Proc [dbo].[Prc_Get_Businesses]

AS

	SELECT   *
	FROM
		Business



GO
