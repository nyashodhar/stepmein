USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnGeoQueryZIP]    Script Date: 3/22/2013 11:07:30 PM ******/
SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER OFF
GO






CREATE   FUNCTION [dbo].[fnGeoQueryZIP]
	(@DirPrefix varchar(2) = '', @Number int, @StreetName varchar(50), @StreetType varchar(50), @DirSuffix varchar(2) ='', @ZipCode int)

RETURNS @GEO TABLE 
(
FromAddR int,
FromAddL int,
ToAddR int,
ToAddL int,
zipL int,
zipR int,
ToLat decimal(9,6),
ToLng decimal(9,6),
FromLng decimal(9,6),
FromLat decimal(9,6),

Long1 decimal(9,6),
Lat1 decimal(9,6),
Long2 decimal(9,6),
Lat2 decimal(9,6),
Long3 decimal(9,6),
Lat3 decimal(9,6),
Long4 decimal(9,6),
Lat4 decimal(9,6),
Long5 decimal(9,6),
Lat5 decimal(9,6),
Long6 decimal(9,6),
Lat6 decimal(9,6),
Long7 decimal(9,6),
Lat7 decimal(9,6),
Long8 decimal(9,6),
Lat8 decimal(9,6),
Long9 decimal(9,6),
Lat9 decimal(9,6),
Long10 decimal(9,6),
Lat10 decimal(9,6),
TLID varchar(50)
)
AS
	BEGIN
	
	declare @todecimal decimal(9,6)
	set @todecimal = 0.000001

INSERT INTO @GEO
select top 1 
	isnull(t1.fraddr,-1) , isnull(t1.fraddl,-1) , isnull(t1.toaddr,-1), isnull(t1.toaddl,-1), t1.zipl, t1.zipr, t1.tolat * @todecimal, t1.tolong * @todecimal, t1.frlong * @todecimal, t1.frlat * @todecimal, 
		isnull(t2.long1,0)  * @todecimal, isnull(t2.lat1,0) * @todecimal, isnull(t2.long2,0) * @todecimal, isnull(t2.lat2,0) * @todecimal, isnull(t2.long3,0) * @todecimal, isnull(t2.lat3,0) * @todecimal, isnull(t2.long4,0) * @todecimal, isnull(t2.lat4,0) * @todecimal, isnull(t2.long5,0) * @todecimal, isnull(t2.lat5,0) * @todecimal, isnull(t2.long6,0) * @todecimal, isnull(t2.lat6,0) * @todecimal, isnull(t2.long7,0) * @todecimal, isnull(t2.lat7,0) * @todecimal, isnull(t2.long8,0) * @todecimal, isnull(t2.lat8,0) * @todecimal, isnull(t2.long9,0) * @todecimal, isnull(t2.lat9,0) * @todecimal, isnull(t2.long10,0) * @todecimal, isnull(t2.lat10,0) * @todecimal, t1.TLID
	  from TIGER_01 t1 with (nolock) left outer join TIGER_02 t2  with (nolock) on t1.tlid = t2.tlid  where fename = @StreetName and fetype = @StreetType and fedirp = @DirPrefix and fedirs = @DirSuffix and (zipl = @ZipCode or zipr =@ZipCode)
       and ((fraddl <= @Number and toaddl >= @Number) or (fraddl >= @Number and toaddl <= @Number )
        OR (fraddr <= @Number and toaddr >= @Number) OR (fraddr >= @Number and toaddr <= @Number) )

	RETURN
	END







GO
