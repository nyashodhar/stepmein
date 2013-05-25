USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnGeocode]    Script Date: 3/22/2013 11:07:30 PM ******/
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


CREATE    FUNCTION [dbo].[fnGeocode] (@DirPrefix varchar(2) = '', @Number int, @StreetName varchar(50), @StreetType varchar(50), @DirSuffix varchar(2) = '', @ZipCode int =-1 , @FipsStateCode int=-1, @FipsCountyCode int =-1)
RETURNS @Location TABLE 
	(
	Longitude Decimal(9,6), 
	Latitude Decimal(9,6),
	ZIP int,
	TLID varchar(50)
	)
AS
BEGIN


declare @FromLat decimal(9,6)
declare @ToLat decimal(9,6)
declare @FromLng decimal(9,6)
declare @ToLng decimal(9,6)

declare @TLID varchar(50)

declare @FromAddR int
declare @ToAddR int
declare @FromAddL int
declare @ToAddL int

declare @zipL int
declare @zipR int

--we need 10 lat/long pairs for type 2 records
declare @Long1 decimal(9,6)
declare @Lat1 decimal(9,6)
declare @Long2 decimal(9,6)
declare @Lat2 decimal(9,6)
declare @Long3 decimal(9,6)
declare @Lat3 decimal(9,6)
declare @Long4 decimal(9,6)
declare @Lat4 decimal(9,6)
declare @Long5 decimal(9,6)
declare @Lat5 decimal(9,6)
declare @Long6 decimal(9,6)
declare @Lat6 decimal(9,6)
declare @Long7 decimal(9,6)
declare @Lat7 decimal(9,6)
declare @Long8 decimal(9,6)
declare @Lat8 decimal(9,6)
declare @Long9 decimal(9,6)
declare @Lat9 decimal(9,6)
declare @Long10 decimal(9,6)
declare @Lat10 decimal(9,6)

declare @querypicked int
set @querypicked = 0

declare @todecimal decimal(9,6)
set @todecimal = 0.000001

-----------------------------------------------------------------------
---which query do we use?----------------------------------------------
/*
This section is responsible for finding a suitable tiger record.
If a hit is found its passed off to the geocoding function
*/
--zip query
if (@ZipCode <> -1 and @querypicked = 0)
begin
	set @querypicked = 1
	
	DECLARE c1 CURSOR FOR
		select * from fnGeoQueryZIP(@DirPrefix, @Number, @StreetName,@StreetType, @DirSuffix, @ZipCode)

end

--county query
if (@FipsStateCode <> -1 and @FipsCountyCode <> -1 and @querypicked = 0)
begin
	set @querypicked = 1
	DECLARE c1 CURSOR FOR
	select * from fnGeoQueryCounty(@DirPrefix, @Number, @StreetName,@StreetType, @DirSuffix, @FipsStateCode, @FipsCountyCode)

end

--state query
if (@FipsStateCode <> -1 and @querypicked = 0)
begin
	set @querypicked = 1
	DECLARE c1 CURSOR FOR
	select * from fnGeoQueryState(@DirPrefix, @Number, @StreetName,@StreetType, @DirSuffix, @FipsStateCode)
end
-----------------------------------------------------------------------


OPEN c1

FETCH NEXT FROM c1 INTO 
	@FromAddR, @FromAddL, @ToAddR, @ToAddL, @zipL, @zipR, @ToLat, @ToLng, @FromLng, @FromLat,@Long1, @Lat1,@Long2, @Lat2,@Long3, @Lat3,@Long4, @Lat4,@Long5, @Lat5,@Long6, @Lat6,@Long7, @Lat7,@Long8, @Lat8,@Long9, @Lat9, @Long10, @Lat10, @TLID

if (@@FETCH_STATUS = 0)
BEGIN
	
	/*
		We've got a hit. pass the info off to the geocoding function. We should be guaranteed a lng/lat.
	*/

	insert into @Location
		select * from fnGeoFromHit(@Number,@TLID,@FromAddR,@FromAddl,@ToAddr,@ToAddL,@ZipL,@ZipR,@FromLng,@FromLat,@Long1,@Lat1,@Long2,@Lat2,@Long3,@Lat3,@Long4,@Lat4,@Long5,@Lat5,@Long6,@Lat6,@Long7,@Lat7,@Long8,@Lat8,@Long9,@Lat9,@Long10,@Lat10, @Tolng,@ToLat)
	
END


CLOSE c1
DEALLOCATE c1
RETURN 
	
END














GO
