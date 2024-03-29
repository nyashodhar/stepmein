USE [SpotOn_DB]
GO
/****** Object:  Table [dbo].[Appointment]    Script Date: 3/22/2013 11:06:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Appointment](
	[AppointmentID] [int] NOT NULL,
	[CheckINOUTID] [int] NOT NULL,
	[StartDtTime] [datetime] NULL,
	[EndDtTime] [datetime] NULL,
	[CurrentDateTime] [datetime] NULL,
	[Reminder] [int] NULL,
	[Alert] [int] NULL,
 CONSTRAINT [PK_Appointment] PRIMARY KEY CLUSTERED 
(
	[AppointmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Appointment] ADD  CONSTRAINT [CURRENT_TIMESTAMP_123]  DEFAULT (getdate()) FOR [CurrentDateTime]
GO
ALTER TABLE [dbo].[Appointment]  WITH CHECK ADD  CONSTRAINT [FK_Appointment_CheckINOUT] FOREIGN KEY([CheckINOUTID])
REFERENCES [dbo].[CheckINOUT] ([CheckINOUTID])
GO
ALTER TABLE [dbo].[Appointment] CHECK CONSTRAINT [FK_Appointment_CheckINOUT]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This is a unique appointment identifier key' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Appointment', @level2type=N'COLUMN',@level2name=N'AppointmentID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'This field is declared in CheckINOUT Table and is mainly used to identify users checked into any business' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Appointment', @level2type=N'COLUMN',@level2name=N'CheckINOUTID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Indicates when a user wants to schedule an appointment' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Appointment', @level2type=N'COLUMN',@level2name=N'StartDtTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Indicates the time when the appointment ends' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Appointment', @level2type=N'COLUMN',@level2name=N'EndDtTime'
GO
EXEC sys.sp_addextendedproperty @name=N'Appointment', @value=N'This table is used to schedule an Appointment. ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Appointment'
GO
