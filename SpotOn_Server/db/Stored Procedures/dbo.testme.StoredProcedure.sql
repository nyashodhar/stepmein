USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[testme]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[testme] @input  VARCHAR(10),
                       @output VARCHAR(20) output
AS
  BEGIN
      IF @input >= '1'
        BEGIN
            SET @output = 'i am back';

            RETURN;
        END
  END

GO
