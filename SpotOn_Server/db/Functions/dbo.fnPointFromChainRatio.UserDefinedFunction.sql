USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnPointFromChainRatio]    Script Date: 3/22/2013 11:07:30 PM ******/
SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER OFF
GO





/*
© 2005 John Sample

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

*/
CREATE FUNCTION [dbo].[fnPointFromChainRatio]
	(
		@TotalLength decimal(9,6),
		@Ratio decimal(9,6),
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

RETURNS @LongLat  TABLE
	(
	Longitude decimal(9,6), 
	Latitude decimal(9,6)
	)
AS
BEGIN
	
	--buffer for last x,y coords
	declare @lastLng decimal(9,6)
	declare @lastLat decimal(9,6)

	set @lastLng = @fromLng
	set @lastLat = @fromLat	

	declare @found int
	set @found = 0

	declare @totalRatio decimal(9,6)--Tracks the total percentage of the line 
	declare @totalTravel decimal(9,6)--Tracks the total distance we have travelled
	declare @travelTarget decimal(9,6)
	set @totalRatio = 0.0
	set @totalTravel = 0.0
	set @travelTarget = @ratio * @TotalLength

	---For each lng/lat pair that isn't empty, calculate distance from last non empty pair.
	---Is the totalRatio + ratio of this segment < the ratio we are looking for?
	---If yes, add the ratio of this distance to the total ratio
	---If no, trim the ratio so that it applies only to this segment and return calculated point.

	--buffer vars to keep the code a bit shorter
	declare @thisLen decimal(9,6)
	declare @thisRatio decimal(20,6)

	declare @useStartLong decimal(9,6)
	declare @useStartLat decimal(9,6)
	declare @useEndLong decimal(9,6)
	declare @useEndLat decimal(9,6)

	declare @useRatio decimal(9,6)
	
	
	--we've got to do this 10 times
	if ((@Lat1 <> 0) and (@found = 0) )
	begin
		set @thisLen = dbo.fnLineDistance(@long1, @lat1, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong = @lastLng
		  set @useStartLat = @lastLat
		  set @useEndLong  = @Long1
		  set @useEndLat  = @Lat1
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long1
		 set @lastLat = @Lat1
		 set @totalTravel = @totalTravel + @thisLen
		end
	end



	if (@Lat2 <> 0 and @found = 0) 
	begin		
		set @thisLen = dbo.fnLineDistance(@long2, @lat2, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong = @Long2
		  set @useEndLat  = @Lat2
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long2
		 set @lastLat = @Lat2
		 set @totalTravel = @totalTravel + @thisLen
		end
	end



	if (@Lat3 <> 0 and @found = 0) 
	begin
		set @thisLen = dbo.fnLineDistance(@long3, @lat3, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong  = @Long3
		  set @useEndLat = @Lat3
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long3
		 set @lastLat = @Lat3
		 set @totalTravel = @totalTravel + @thisLen
		end	
	end



	if (@Lat4 <> 0 and @found = 0) 
	begin
		set @thisLen = dbo.fnLineDistance(@long4, @lat4, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong  = @Long4
		  set @useEndLat  = @Lat4
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long4
		 set @lastLat = @Lat4
		 set @totalTravel = @totalTravel + @thisLen
		end
	end


	if (@Lat5 <> 0 and @found = 0) 
	begin
		set @thisLen = dbo.fnLineDistance(@long5, @lat5, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong  = @Long5
		  set @useEndLat  = @Lat5
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long5
		 set @lastLat = @Lat5
		 set @totalTravel = @totalTravel + @thisLen
		end	
	end


	if (@Lat6 <> 0 and @found = 0) 
	begin
		set @thisLen = dbo.fnLineDistance(@long6, @lat6, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong  = @Long6
		  set @useEndLat  = @Lat6
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long6
		 set @lastLat = @Lat6
		 set @totalTravel = @totalTravel + @thisLen
		end
	end



	if (@Lat7 <> 0 and @found = 0) 
	begin
		set @thisLen = dbo.fnLineDistance(@long7, @lat7, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong  = @Long7
		  set @useEndLat  = @Lat7
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long7
		 set @lastLat = @Lat7
		 set @totalTravel = @totalTravel + @thisLen
		end
	end




	if (@Lat8 <> 0 and @found = 0) 
	begin
		set @thisLen = dbo.fnLineDistance(@long8, @lat8, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong  = @Long8
		  set @useEndLat  = @Lat8
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long8
		 set @lastLat = @Lat8
		 set @totalTravel = @totalTravel + @thisLen
		end
	end




	if (@Lat9 <> 0 and @found = 0) 
	begin
		set @thisLen = dbo.fnLineDistance(@long9, @lat9, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong  = @Long9
		  set @useEndLat  = @Lat9
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long9
		 set @lastLat = @Lat9
		 set @totalTravel = @totalTravel + @thisLen
		end
	end




	if (@Lat10 <> 0 and @found = 0) 
	begin
		set @thisLen = dbo.fnLineDistance(@long2, @lat2, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong  = @Long10
		  set @useEndLat  = @Lat10
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @Long10
		 set @lastLat = @Lat10
		 set @totalTravel = @totalTravel + @thisLen
		end
	end
	

	if (@found = 0)
	begin
		set @thisLen = dbo.fnLineDistance(@tolng, @tolat, @lastLng, @lastLat)
		set @thisRatio = @thisLen / @TotalLength

		if (@thisLen + @totalTravel >= @travelTarget)
		begin
		  set @useStartLong  = @lastLng
		  set @useStartLat  = @lastLat
		  set @useEndLong  = @tolng
		  set @useEndLat  = @tolat
		  
		  set @useRatio = @thisRatio
		  set @found = 1
		end
		else
		begin
		 set @totalRatio = @totalRatio + @thisRatio
		 set @lastLng = @tolng
		 set @lastLat = @tolat
		 set @totalTravel = @totalTravel + @thisLen
		end
	end

	declare @lon decimal(9,6)
	declare @lat decimal(9,6)
	declare @rel decimal(9,6)	

	set @rel = (@Ratio - @totalRatio) / @useRatio
		
	declare @lonDist decimal(9,6) 
	declare @latDist decimal(9,6)	set @lonDist = @useEndLong - @useStartLong
	set @latDist = @useEndLat - @useStartLat
/*
	set @lon = @useStartLong + (@rel * @lonDist)
	set @lat = @useStartLat + (@rel * @latDist)
*/
	set @lon = @useEndLong - (@rel * @lonDist)
	set @lat = @useEndLat - (@rel * @latDist)
	
	insert into @LongLat	
		select @lon, @lat
	RETURN 
END







GO
