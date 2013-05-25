USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnPointDistanceFromLine]    Script Date: 3/22/2013 11:07:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO







CREATE  FUNCTION [dbo].[fnPointDistanceFromLine]
	(
	
	@x1 decimal(9,6),
	@y1 decimal(9,6),
	@x2 decimal(9,6),
	@y2 decimal(9,6),
	@PointX decimal(9,6),
	@PointY decimal(9,6)
	)
RETURNS decimal(20,6)
AS
	BEGIN
		


	declare @dx decimal(9,6)
	declare @dy decimal(9,6)
	declare @temp decimal(20,6)
	declare @distance decimal(9,6)	

		set @dx = @x2 - @x1
		set @dy = @y2 - @y1
	 set @temp = sqrt((@dx*@dx) + (@dy*@dy))
	
	set @distance =  (@dx*(@PointY - @y1) - @dy*(@PointX - @x1))/@temp

	RETURN @distance

	END








GO
