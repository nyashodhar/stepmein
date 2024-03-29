USE [SpotOn_DB]
GO
/****** Object:  StoredProcedure [dbo].[DataDictionary]    Script Date: 3/22/2013 11:04:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Sharath Dudala>
-- Create date: <13-Mar-2012>
-- Description: DataDictionary
-- This stored procedure gets information on all table and columns stored in the database
-- When the table name is passed, it only gets data for that particular table, else it will show all tables
-- Usage: Exec DataDictionary  OR Exec DataDictionary 'appointment'
-- =============================================

CREATE PROC [dbo].[DataDictionary]
( @Table_Name Varchar(100) = NULL)

AS

Set nocount on

IF (@Table_Name IS NULL) OR (@Table_Name = '')
BEGIN
SELECT T.name AS 'Table Name',
C.name AS 'Column Name',
S.name AS 'Data Type',
CASE WHEN C.is_nullable = 1
THEN 'Y'
ELSE 'N'
END AS 'Allow NULLs',
P.value AS 'Description'
FROM sys.tables AS T
JOIN sys.columns AS C
ON T.object_id = C.object_id
JOIN sys.types AS S
ON C.user_type_id = S.user_type_id
LEFT OUTER JOIN sys.extended_properties AS P
ON C.object_id = P.major_id
AND C.column_id = P.minor_id
AND P.class = 1
AND P.name = 'MS_Description'
WHERE T.type = 'U'
ORDER BY T.name, C.column_id;
END
ELSE
BEGIN
SELECT T.name AS 'Table Name',
C.name AS 'Column Name',
S.name AS 'Data Type',
CASE WHEN C.is_nullable = 1
THEN 'Y'
ELSE 'N'
END AS 'Allow NULLs',
P.value AS 'Description'
FROM sys.tables AS T
JOIN sys.columns AS C
ON T.object_id = C.object_id
JOIN sys.types AS S
ON C.user_type_id = S.user_type_id
LEFT OUTER JOIN sys.extended_properties AS P
ON C.object_id = P.major_id
AND C.column_id = P.minor_id
AND P.class = 1
AND P.name = 'MS_Description'
WHERE T.type = 'U'
AND T.Name  = @Table_Name
ORDER BY T.name, C.column_id;

END

GO
