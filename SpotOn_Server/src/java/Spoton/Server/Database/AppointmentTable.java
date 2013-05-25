/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author kunal
 */
public class AppointmentTable
    {

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
    final public static String APPOINTMENT_ID = "AppointmentID";
    final public static String CHECK_INOUT_ID = "CheckINOUTID";
    final public static String START_DT_TIME = "StartDtTime";
    final public static String END_DT_TIME = "EndDtTime";
    final public static String CURRENT_DT_TIME = "CurrentDateTime";
    final public static String REMINDER = "Reminder";
    final public static String ALERT = "Alert";

    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/06/2012
     * 
     * Calling Stored Procedure Prc_Insert_Appointment
     ********************************************************************/
    public static ResultSet prc_InsertAppointment
    (
    int checkINOUTID,
    Date startDateTime,
    Date endDateTime,
    Date currentDateTime,
    int reminder,
    int alert
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Insert_Appointment",
                "Appointment");
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_INOUT_ID), 
                StoredProcedureArg.DataType.INTEGER, checkINOUTID));
        spArgs.add(new StoredProcedureArg("@".concat(START_DT_TIME),
                StoredProcedureArg.DataType.DATETIME, startDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(END_DT_TIME),
                StoredProcedureArg.DataType.DATETIME, endDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(CURRENT_DT_TIME),
                StoredProcedureArg.DataType.DATETIME, currentDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(REMINDER), 
                StoredProcedureArg.DataType.INTEGER, reminder));
        spArgs.add(new StoredProcedureArg("@".concat(ALERT), 
                StoredProcedureArg.DataType.INTEGER, alert));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }   
    }