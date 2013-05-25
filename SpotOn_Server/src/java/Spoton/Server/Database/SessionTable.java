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
public class SessionTable
    {

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
    final public static String USER_ID = "UserID";
    final public static String STATUS = "Status";
    final public static String LAST_ACTIVITY_DATETIME = "LastActivity_Datetime";
    final public static String BUSINESS_ID = "BusinessID";
    final public static String CURRENT_DATETIME = "CurrentDatetime";

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Calling Stored Procedure Prc_Insert_Session
     ********************************************************************/
    public static ResultSet prc_Insert_Session
    (
    int userID,
    String status,
    Date lastActivityDateTime,
    int businessID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Insert_Session",
                "Session");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        spArgs.add(new StoredProcedureArg("@".concat(STATUS),
                StoredProcedureArg.DataType.NCHAR, status));
        spArgs.add(new StoredProcedureArg("@".concat(LAST_ACTIVITY_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, lastActivityDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Calling Stored Procedure Prc_Session_Check
     ********************************************************************/
    public static ResultSet prc_Session_Check
    (
    int userID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Session_Check",
                "Session");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    
    }