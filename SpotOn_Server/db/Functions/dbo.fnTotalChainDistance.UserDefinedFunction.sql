USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnTotalChainDistance]    Script Date: 3/22/2013 11:07:30 PM ******/
SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER OFF
GO






CREATE  FUNCTION [dbo].[fnTotalChainDistance]
	(
		
		@FromLng decimal(9,6),
		@FromLat decimal(9,6),
 		@Long1 decimal(9,6),
		@Lat1 decimal(9,6),
		@Long2 decimal(9,6),
		@Lat2 decimal(9,6),
		@Long3 decimal(9,6),
		@Lat3 decimal(9,6),
		@Long4 decimal(9,6),
		@Lat4 decimal(9,6),
		@Long5 decimal(9,6),
		@Lat5 decimal(9,6),
		@Long6 decimal(9,6),
		@Lat6 decimal(9,6),
		@Long7 decimal(9,6),
		@Lat7 decimal(9,6),
		@Long8 decimal(9,6),
		@Lat8 decimal(9,6),
		@Long9 decimal(9,6),
		@Lat9 decimal(9,6),
		@Long10 decimal(9,6),
		@Lat10 decimal(9,6),
		@ToLng decimal(9,6),
		@ToLat decimal(9,6)
	)

RETURNS @Distance  TABLE
	(
	TotalLong decimal(9,6), 
	TotalLat decimal(9,6)
	)
AS
BEGIN

	declare @latTotal decimal(9,6)
	declare @lngTotal decimal(9,6)
	set @latTotal = 0.0
	set @lngTotal = 0.0

	declare @lastLat decimal(9,6)
	declare @lastLng decimal(9,6)

	--Buffer vars so we know what to subtract with
	set @lastLat = @FromLat
	set @lastLng = @FromLng
	
	--we've got to do this 10 friggin times
	if (@Lat1 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat1 - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long1  - @lastLng))
		set @lastLat = @Lat1
		set @lastLng = @Long1
	end
	if (@Lat2 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat2  - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long2  - @lastLng))
		set @lastLat = @Lat2
		set @lastLng = @Long2
	end
	if (@Lat3 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat3  - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long3  - @lastLng))
		set @lastLat = @Lat3
		set @lastLng = @Long3
	end
	if (@Lat4 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat4  - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long4  - @lastLng))
		set @lastLat = @Lat4
		set @lastLng = @Long4
	end
	if (@Lat5 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat5  - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long5  - @lastLng))
		set @lastLat = @Lat5
		set @lastLng = @Long5
	end
	if (@Lat6 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat6  - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long6  - @lastLng))
		set @lastLat = @Lat6
		set @lastLng = @Long6
	end
	if (@Lat7 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat7  - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long7 - @lastLng))
		set @lastLat = @Lat7
		set @lastLng = @Long7
	end
	if (@Lat8 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat8  - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long8  - @lastLng))
		set @lastLat = @Lat8
		set @lastLng = @Long8
	end
	if (@Lat9 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat9 - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long9  - @lastLng))
		set @lastLat = @Lat9
		set @lastLng = @Long9
	end
	if (@Lat10 <> 0) 
	begin
		set @latTotal = @latTotal + Abs((@Lat10  - @lastLat))
		set @lngTotal = @lngTotal + Abs((@Long10  - @lastLng))
		set @lastLat = @Lat10
		set @lastLng = @Long10
	end
	
	set @latTotal = @latTotal + Abs((@ToLat - @lastLat))
	set @lngTotal = @lngTotal + Abs((@ToLng  - @lastLng))

	insert into @Distance
		select @lngTotal, @latTotal
	RETURN 
END







GO
