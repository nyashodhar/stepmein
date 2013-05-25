/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.UUID;

/**
 *
 * @author kunal
 */
public class UserSessionTable
    {

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/10/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
    final public static String USER_ID = "UserID";
    final public static String IP_ADDRESS = "IPAddress";
    final public static String LAST_ACTIVITY_DATETIME = "LastActivity_Datetime";
    final public static String SESSION_ID = "UserSessionID";
    

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/10/2012
     * 
     * Calling Stored Procedure Prc_Insert_User_Session
     ********************************************************************/
    public static boolean prc_InsertUserSession
    (
    UUID sessionID,
    int userID,
    String ipAddress,
    Date lastActivityDateTime,
    String appName,
    String OS,
    String deviseID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Insert_User_Session",
                "UserSession");
        spArgs.add(new StoredProcedureArg("@".concat(SESSION_ID), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER,
                sessionID.toString()));
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        spArgs.add(new StoredProcedureArg("@".concat(IP_ADDRESS),
                StoredProcedureArg.DataType.VARCHAR, ipAddress));
        spArgs.add(new StoredProcedureArg("@".concat(LAST_ACTIVITY_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, lastActivityDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(UserDevicesTable.APP_NAME),
                StoredProcedureArg.DataType.VARCHAR, appName));
        spArgs.add(new StoredProcedureArg("@".concat(UserDevicesTable.OS),
                StoredProcedureArg.DataType.VARCHAR, OS));
        spArgs.add(new StoredProcedureArg("@".concat(UserDevicesTable.DEVISE_ID),
                StoredProcedureArg.DataType.VARCHAR, deviseID));
        
        
        return Utils.executeNonQueryStoredProcedure(spArgs);
        
        
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/10/2012
     * 
     * Calling Stored Procedure Prc_User_Session_Check
     ********************************************************************/
    public static ResultSet prc_UserSessionCheck
    (
    int userID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_User_Session_Check",
                "UserSession");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/11/2012
     * 
     * Calling Stored Procedure Prc_Active_User_Session
     ********************************************************************/
    public static ResultSet prc_ActiveUserSession
    (
    int userID,
    UUID sessionID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Active_User_Session",
                "UserSession");
        spArgs.add(new StoredProcedureArg("@".concat(UserTable.EMAIL), 
                StoredProcedureArg.DataType.INTEGER, userID));
        spArgs.add(new StoredProcedureArg("@".concat(SESSION_ID), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER,
                sessionID.toString()));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/29/2012
     * 
     * Calling Stored Procedure Prc_Active_User_Session
     ********************************************************************/
    public static boolean prc_DeleteUserSession
    (
    int userID,
    UUID sessionID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Delete_User_Session",
                "UserSession");
        spArgs.add(new StoredProcedureArg("@".concat(UserTable.EMAIL), 
                StoredProcedureArg.DataType.INTEGER, userID));
        spArgs.add(new StoredProcedureArg("@".concat(SESSION_ID), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER,
                sessionID.toString()));
        
        return Utils.executeNonQueryStoredProcedure(spArgs);
        }
    
    }