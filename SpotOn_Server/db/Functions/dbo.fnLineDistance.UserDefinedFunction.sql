USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnLineDistance]    Script Date: 3/22/2013 11:07:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO






CREATE  FUNCTION [dbo].[fnLineDistance]
	(
	
	@x1 decimal(9,6),
	@y1 decimal(9,6),
	@x2 decimal(9,6),
	@y2 decimal(9,6)
	)
RETURNS decimal(20,6)
AS
	BEGIN
		


	declare @dx decimal(9,6)
	declare @dy decimal(9,6)
	declare @temp decimal(20,6)
		
		set @dx = @x2 - @x1
		set @dy = @y2 - @y1
	 set @temp = sqrt((@dx*@dx) + (@dy*@dy))
	RETURN @temp

	END







GO
