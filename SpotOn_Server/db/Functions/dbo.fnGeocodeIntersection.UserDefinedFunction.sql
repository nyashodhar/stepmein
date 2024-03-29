USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnGeocodeIntersection]    Script Date: 3/22/2013 11:07:30 PM ******/
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
If roads a and b intersect, we should find one of the following:
a.frlat & a.frlong = b.frlat & b.frlong
or
a.frlat & a.frlong = b.tolat & b.tolong
or
a.tolat & a.tolong = b.frlat b a.frlong
or
a.tolat & a.tolong = b.tolat & b.tolong

*/

CREATE   FUNCTION [dbo].[fnGeocodeIntersection]
(
@DirPrefixA varchar(2) = '',  @StreetNameA varchar(50), @StreetTypeA varchar(50), @DirSuffixA varchar(2) ='', @ZipA int,
@DirPrefixB varchar(2) = '',  @StreetNameB varchar(50), @StreetTypeB varchar(50), @DirSuffixB varchar(2) ='', @ZipB int
)
RETURNS @Location TABLE 
	(
	Longitude Decimal(9,6), 
	Latitude Decimal(9,6)
	)
AS
BEGIN

declare @todecimal decimal(9,6)
set @todecimal = 0.000001

INSERT into @Location 
select frlong * @todecimal as Longitude, frlat * @todecimal  as Latitude from tiger_01 t1a where t1a.fename = @StreetNameA and t1a.fetype = @StreetTypeA and t1a.fedirp = @DirPrefixA and t1a.fedirs = @DirSuffixA and (t1a.zipl = @ZipA or t1a.zipr =@ZipA)
and 
(
exists(select 1 from tiger_01 t1b where t1b.fename = @StreetNameB and t1b.fetype = @StreetTypeB and (t1b.zipl = @ZipB or t1b.zipr =@ZipB) and t1b.fedirp = @DirPrefixB and t1b.fedirs = @DirSuffixB and t1b.frlat= t1a.frlat and t1b.frlong= t1a.frlong)
or 
exists(select 1 from tiger_01 t1b where t1b.fename = @StreetNameB and t1b.fetype = @StreetTypeB and (t1b.zipl = @ZipB or t1b.zipr =@ZipB) and t1b.fedirp = @DirPrefixB and t1b.fedirs = @DirSuffixB and t1b.tolat= t1a.frlat and t1b.tolong= t1a.frlong)
)
UNION
select tolong * @todecimal as Longitude, tolat * @todecimal as Latitude from tiger_01 t1a where t1a.fename = @StreetNameA and t1a.fetype = @StreetTypeA and t1a.fedirp = @DirPrefixA and t1a.fedirs = @DirSuffixA and (t1a.zipl = @ZipA or t1a.zipr =@ZipA)
and 
(
exists(select 1 from tiger_01 t1b where t1b.fename = @StreetNameB and t1b.fetype = @StreetTypeB and (t1b.zipl = @ZipB or t1b.zipr =@ZipB) and t1b.fedirp = @DirPrefixB and t1b.fedirs = @DirSuffixB and t1b.frlat= t1a.tolat and t1b.frlong= t1a.tolong)
or 
exists(select 1 from tiger_01 t1b where t1b.fename = @StreetNameB and t1b.fetype = @StreetTypeB and (t1b.zipl = @ZipB or t1b.zipr =@ZipB) and t1b.fedirp = @DirPrefixB and t1b.fedirs = @DirSuffixB and t1b.tolat= t1a.tolat and t1b.tolong= t1a.tolong)
)


return
END









GO
