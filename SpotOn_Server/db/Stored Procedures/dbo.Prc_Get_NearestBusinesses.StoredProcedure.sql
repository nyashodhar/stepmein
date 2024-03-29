USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Get_NearestBusinesses]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <12-Feb-2012>
-- Description:	Retreiving top 50 nearest businesses(within 5 square miles)   
--				Supply longitude and latitude and retrieve nearest top 50 businesses within 5 square miles.
-- =============================================

CREATE Proc [dbo].[Prc_Get_NearestBusinesses]
( 
@UserID int,
@Longitude float,
@Latitude float
)
AS

Declare @longitude1 float = @longitude - 0.071948 -- 5 miles , 0.0143896 = 1 Mile, 0.089953 5 km
Declare @longitude2 float = @longitude + 0.071948
Declare @latitude1 float  = @Latitude - 0.079388  -- 5 miles, 0.0158776 = 1 Mile, 0.099257 5 km
Declare @latitude2 float  = @Latitude + 0.079388

Create table #temp1
( BusinessID int ,
  longitude_temp float,
  latitude_temp float,
  Distance float
 ) 
Insert into #temp1(BusinessID,longitude_temp, latitude_temp)
Select BusinessID,Longitude,Latitude from dbo.Business with(nolock)
where Longitude between  @longitude1 and @longitude2
AND Latitude between @latitude1 and @latitude2

Update #temp1
Set Distance = sqrt(square((longitude_temp - @Longitude)/(0.0143896)) + SQUARE((latitude_temp - @Latitude)/(0.0158776)))


SELECT 
  TOP 50 B.*,t.Distance 
FROM dbo.Business B with(nolock)
inner join #temp1 t 
on B.BusinessID = t.BusinessID
where B.BusinessID NOT IN (Select Block.BusinessID
				  FROM [SpotOn_DB].[dbo].[Block] Block with(nolock)
				  Where Block.UserID = @UserID)
and t.Distance <= 5

order by t.Distance asc


GO
