USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnLineRatio]    Script Date: 3/22/2013 11:07:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE FUNCTION [dbo].[fnLineRatio]
	(
	
	@TotalLength decimal(9,6),
	@SegmentLength decimal(9,6)
	
	)
RETURNS decimal(9,6)
AS
	BEGIN
		
	RETURN @SegmentLength / @TotalLength
	
	END





GO
