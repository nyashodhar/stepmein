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
public class BusinessSessionTable
    {

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/10/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
    final public static String BUSINESS_ID = "BusinessID";
    final public static String IP_ADDRESS = "IPAddress";
    final public static String LAST_ACTIVITY_DATETIME = "LastActivity_Datetime";
    final public static String SESSION_ID = "BusinessSessionID";

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 03/01/2012
     * 
     * Calling Stored Procedure Prc_Insert_Business_Session
     ********************************************************************/
    public static boolean prc_InsertBusinessSession
    (
    UUID sessionID,
    int businessID,
    String ipAddress,
    Date lastActivityDateTime
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Insert_Business_Session",
                "BusinessSession");
        spArgs.add(new StoredProcedureArg("@".concat(SESSION_ID), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER,
                sessionID.toString()));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        spArgs.add(new StoredProcedureArg("@".concat(IP_ADDRESS),
                StoredProcedureArg.DataType.VARCHAR, ipAddress));
        spArgs.add(new StoredProcedureArg("@".concat(LAST_ACTIVITY_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, lastActivityDateTime));
        
        boolean status = Utils.executeNonQueryStoredProcedure(spArgs);
        
        return status;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 03/01/2012
     * 
     * Calling Stored Procedure Prc_Business_Session_Check
     ********************************************************************/
    public static ResultSet prc_BusinessSessionCheck
    (
    int businessID
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Business_Session_Check",
                "BusinessSession");
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 03/01/2012
     * 
     * Calling Stored Procedure Prc_Active_Business_Session
     ********************************************************************/
    public static ResultSet prc_ActiveBusinessSession
    (
    String userName,
    UUID sessionID
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Active_Business_Session",
                "BusinessSession");
        spArgs.add(new StoredProcedureArg("@".concat(UserTable.EMAIL), 
                StoredProcedureArg.DataType.VARCHAR, userName));
        spArgs.add(new StoredProcedureArg("@".concat(SESSION_ID), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER,
                sessionID.toString()));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 03/01/2012
     * 
     * Calling Stored Procedure Prc_Active_Business_Session
     ********************************************************************/
    public static void prc_DeleteBusinessSession
    (
    String userName,
    UUID sessionID
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Delete_Business_Session",
                "BusinessSession");
        spArgs.add(new StoredProcedureArg("@".concat(UserTable.EMAIL), 
                StoredProcedureArg.DataType.VARCHAR, userName));
        spArgs.add(new StoredProcedureArg("@".concat(SESSION_ID), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER,
                sessionID.toString()));
        
        Utils.executeNonQueryStoredProcedure(spArgs);
        }
    
    }