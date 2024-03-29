USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnReverseGeocode]    Script Date: 3/22/2013 11:07:30 PM ******/
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

/*
This function will find the closest node to the given long/lat
It will usually return more than one row since the beginning/end of one node is almost always the beginning/end of at least one other
However, the Long/Lat returned for all records will almost always be the same unless we happen to query a point which is equidistant from 2 or more completely unchained nodes.
You can query the return table for distinct names to get the title of the intersection if one exists.
If you are using a database with more than one tiger/line county set, use the statel/stater and countyl/countyr FIPs codes for general location info
*/

CREATE     function [dbo].[fnReverseGeocode](@Longitude decimal(9,6), @Latitude decimal(9,6))
RETURNS @Location TABLE 
	(
	TLID varchar(50),
	Longitude Decimal(9,6), --These are the closest node points, they could be frlong/frlat or tolong/tolat
	Latitude Decimal(9,6), 
	fedirp varchar(2),
	fename varchar(50),
	fetype varchar(10),
	fraddr int,
	toaddr int,
	fraddl int,
	toaddl int,
	zipl int,
	zipr int,
	frlat int,
	frlong int,
	tolat int,
	tolong int,
	countyl int,
	countyr int,
	statel int,
	stater int
	)
AS
BEGIN

declare @fromdec int 
set @fromdec = 1000000
declare @todecimal decimal(9,6)
set @todecimal = 0.000001
declare @Lat int 
declare @Lng int
declare @sway int

set @sway =0.01449275 * 100000
set @Lat = @Latitude * @fromdec
set @Lng = @Longitude * @fromdec

insert into @Location
select 	TLID, frlong * @todecimal as Longitude, frlat  * @todecimal as Latitude, fedirp, fename, fetype, fraddr, toaddr, fraddl, toaddl, zipl, zipr, frlat, frlong, tolat, tolong, countyl, countyr, statel, stater 
	from tiger_01 WITH (nolock, index(IDX_FRLAT)) where (frlat < (@lat + @sway) and frlat > (@lat - @sway) and frlong < (@lng + @sway) and frlong > (@lng - @sway)) and abs(frlat - @Lat) +  abs(frlong - @Lng) = (select min(abs(frlat - @Lat) + abs(frlong - @Lng)) from tiger_01 WITH (nolock, index(IDX_FRLAT)) where (frlat < (@lat + @sway) and frlat > (@lat - @sway) and frlong < (@lng + @sway) and frlong > (@lng - @sway))    )
UNION
select TLID, tolong * @todecimal as Longitude, tolat * @todecimal as Latitude, fedirp, fename, fetype, fraddr, toaddr, fraddl, toaddl, zipl, zipr, frlat, frlong, tolat, tolong, countyl, countyr, statel, stater 
	from tiger_01 WITH (nolock, index(IDX_TOLAT)) where (tolat < (@lat + @sway) and tolat > (@lat - @sway) and tolong < (@lng + @sway) and tolong > (@lng - @sway)) and abs(tolat - @Lat) +  abs(tolong - @Lng) = (select min(abs(tolat - @Lat) + abs(tolong - @Lng)) from tiger_01 WITH (nolock, index(IDX_TOLAT)) where (tolat < (@lat + @sway) and tolat > (@lat - @sway) and tolong < (@lng + @sway) and tolong > (@lng - @sway))    )

return
END











GO
