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
public class Check_IN_OUTTable
    {

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
    final public static String CHECK_IN_OUT_ID = "CheckINOUTID";
    final public static String USER_ID = "UserID";
    final public static String FACEBOOK_ID = "FacebookID";
    final public static String BUSINESS_ID = "BusinessID";
    final public static String CHECK_IN_DATETIME = "Check_IN_DateTime";
    final public static String CHECK_OUT_DATETIME = "Check_Out_DateTime";
    final public static String PROVIDER_ID = "ProviderID";
    final public static String PARTY_SIZE = "PartySize";
    final public static String CURRENT_DATETIME = "CurrentDateTime";
    final public static String TYPE_OF_CHECK_IN = "TypeOfCheckIN";

    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Calling Stored Procedure Prc_Active_CheckINs
     ********************************************************************/
    public static ResultSet prc_Active_CheckINs
    (
    int userID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Active_CheckINs",
                "Check_IN_OUT");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID),
                StoredProcedureArg.DataType.INTEGER, userID));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }   

    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Calling Stored Procedure Prc_CheckIN_History
     ********************************************************************/
    public static ResultSet prc_CheckIN_History
    (
    int userID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_CheckIN_History",
                "Check_IN_OUT");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Calling Stored Procedure Prc_UserCheckINs
     ********************************************************************/
    public static ResultSet prc_UserCheckINs
    (
    int userID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_UserCheckINs",
                "Check_IN_OUT");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));

        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/02/2012
     * 
     * Calling Stored Procedure Prc_Insert_CheckINOUT
     ********************************************************************/
    public static int prc_InsertCheckIN
    (
    int userID,
    String facebookID,
    int businessID,
    Date checkInDateTime,
    Date checkOutDateTime,
    int providerID,
    int partySize,
    Date currentDateTime,
    String typeOfCheckIn
    //int checkINOUTID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Insert_CheckINOUT",
                "Check_IN_OUT");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        spArgs.add(new StoredProcedureArg("@".concat(FACEBOOK_ID), 
                StoredProcedureArg.DataType.VARCHAR, userID));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_IN_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, checkInDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_OUT_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, checkOutDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(PROVIDER_ID), 
                StoredProcedureArg.DataType.INTEGER, providerID));
        spArgs.add(new StoredProcedureArg("@".concat(PARTY_SIZE), 
                StoredProcedureArg.DataType.INTEGER, partySize));
        spArgs.add(new StoredProcedureArg("@".concat(CURRENT_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, currentDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(TYPE_OF_CHECK_IN), 
                StoredProcedureArg.DataType.VARCHAR, typeOfCheckIn));
        
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_IN_OUT_ID), 
                StoredProcedureArg.DataType.INTEGER, null,StoredProcedureArg.ArgDirection.INPUTOUTPUT));
        
        int rs = Utils.ExecuteQueryStoredProcedureToInt(spArgs, CHECK_IN_OUT_ID);
        return rs;
        }
    
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 06/24/2012
     * 
     * Calling Stored Procedure Prc_CheckOutUser
     ********************************************************************/
    public static boolean prc_CheckOutUser
    (
    int userID,
    int businessID,
    int checkInOutID,
    int providerID,
    int checkOutFlag
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_CheckOutUser",
                "Check_IN_OUT");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_IN_OUT_ID), 
                StoredProcedureArg.DataType.INTEGER, checkInOutID));
        spArgs.add(new StoredProcedureArg("@".concat(PROVIDER_ID), 
                StoredProcedureArg.DataType.INTEGER, providerID));
        spArgs.add(new StoredProcedureArg("@".concat("CheckOutFlag"), 
                StoredProcedureArg.DataType.INTEGER, checkOutFlag));

        boolean status = Utils.executeNonQueryStoredProcedure(spArgs);
        return status;
        }
    
    }