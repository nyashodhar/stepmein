USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[Prc_Ack_Business_Provider_CheckIn]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create Proc [dbo].[Prc_Ack_Business_Provider_CheckIn]
           (@EmployerID int
           ,@ProviderID int
           ,@BusinessID int = NULL
           ,@CheckInAckState Char(10) = NULL)

AS


UPDATE  [BusinessDB].[dbo].[Business_Provider_CheckIn]
       SET    [CheckInAckState] = @CheckInAckState
   
        WHERE  [EmployerID] = @EmployerID
           AND [ProviderID] = @ProviderID
           AND [BusinessID] = @BusinessID      

GO
