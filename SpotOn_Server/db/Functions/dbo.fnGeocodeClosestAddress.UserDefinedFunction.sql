USE [SpotOn_DB]
GO
/****** Object:  UserDefinedFunction [dbo].[fnGeocodeClosestAddress]    Script Date: 3/22/2013 11:07:30 PM ******/
SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER OFF
GO







CREATE    function [dbo].[fnGeocodeClosestAddress]
(@DirPrefix varchar(2) = '', @Number int, @StreetName varchar(50), @StreetType varchar(50), @DirSuffix varchar(2) = '', @ZipCode int)
RETURNS @Location TABLE 
	(
	Longitude Decimal(9,6), 
	Latitude Decimal(9,6),
	ZIP int,
	TLID varchar(50)
	)
as
begin
declare @todecimal decimal(9,6)
set @todecimal = 0.000001

insert into @Location
select  frlong * @todecimal, frlat * @todecimal, isnull(zipl,zipr),tlid from tiger_01 t1 where 
t1.fename = @streetname and t1.fetype =  @streettype and t1.fedirp = @DirPrefix and fedirs = @DirSuffix and (zipl = @ZipCode or zipr =@ZipCode)
and abs(((isnull(fraddr,fraddl) + isnull(fraddl,fraddr))/2) - @number) = (select min(abs(((isnull(fraddr,fraddl) + isnull(fraddl,fraddr))/2) - @number)) from tiger_01 t1 where fename = @streetname and fetype = @streettype  and t1.fedirp = @DirPrefix and fedirs = @DirSuffix and (zipl = @ZipCode or zipr =@ZipCode))

--We're only checking the closest start address record
--to be more accurate, we should test the end addresses also, then return the long/lat thats closest
/*
UNION
select  abs(fraddl - @number), tlid, fename, fetype, fraddr, toaddr, fraddl, toaddl from tiger_01 t1 where 
t1.fename = @streetname and t1.fetype =  @streettype
and abs(((isnull(toaddr,toaddl) + isnull(toaddl,toaddr))/2) - @number) = (select min(abs(((isnull(toaddr,toaddl) + isnull(toaddl,toaddr))/2) - @number)) from tiger_01 t1 where fename = @streetname and fetype = @streettype )
*/
return
end









GO
