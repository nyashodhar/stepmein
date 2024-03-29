USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Insert_Business_Provider_CheckIn]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Proc [dbo].[Prc_Insert_Business_Provider_CheckIn]
           (@EmployerID int
           ,@ProviderID int
           ,@BusinessID int = NULL
           ,@Check_IN_DateTime datetime = NULL
           ,@Check_OUT_DateTime datetime = NULL
           ,@CheckInAckState char(10) = NULL)

AS

Set nocount on

BEGIN

INSERT INTO [SpotOn_DB].[dbo].[Business_Provider_CheckIn]
           ([EmployerID]
           ,[ProviderID]
           ,[BusinessID]
           ,[Check_IN_DateTime]
           ,[Check_OUT_DateTime]
           ,[CheckInAckState])
  
  SELECT
           @EmployerID
           ,@ProviderID 
           ,@BusinessID 
           ,@Check_IN_DateTime 
           ,@Check_OUT_DateTime 
           ,@CheckInAckState

SELECT 0
END
GO
