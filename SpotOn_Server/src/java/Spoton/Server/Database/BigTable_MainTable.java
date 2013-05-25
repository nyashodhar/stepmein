/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

import Spoton.Server.Logger.SpotOnLogger;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.UUID;

/**
 *
 * @author kunal
 */
public class BigTable_MainTable
{

/*********************************************************************
* Developed By: Kunal Jain                    Date: 05/09/2012
* 
* Static Variables for the names of Columns
 ********************************************************************/
public static String BIGTABLE_ID = "BigTableID";
public static String TO_ID = "ToID";
public static String FROM_ID = "FromID";
public static String MESSAGE = "Message";
public static String CURRENT_DATETIME = "CurrentDatetime";
public static String CHECKIN_TIME = "CheckINTime";
public static String RESERVATION_DATETIME = "Reservation_DateTime";
public static String RESPONSE_REFERENCE_CODE = "ResponseReferenceCode";


public static String TABLE_NAME = "BigTable_Main";

private static SpotOnLogger logger = new SpotOnLogger(
        BigTable_MainTable.class.getName());

 
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 05/09/2012
     * 
     * Calling Stored Procedure Prc_Insert_BigTable_Main
     ********************************************************************/
    public static boolean prc_InsertBigTable_Main
    (
    int toID,
    int fromID,
    String message,
    Date checkINTime,
    Date timeOfReservation
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Insert_BigTable_Main",
                "BigTable_Main");
        spArgs.add(new StoredProcedureArg("@".concat(TO_ID), 
                StoredProcedureArg.DataType.INTEGER, toID));
        spArgs.add(new StoredProcedureArg("@".concat(FROM_ID),
                StoredProcedureArg.DataType.INTEGER, fromID));
        spArgs.add(new StoredProcedureArg("@".concat(MESSAGE), 
                StoredProcedureArg.DataType.VARCHAR, message));
        spArgs.add(new StoredProcedureArg("@".concat(CHECKIN_TIME), 
                StoredProcedureArg.DataType.DATETIME, checkINTime));
        spArgs.add(new StoredProcedureArg("@".concat(RESERVATION_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, timeOfReservation));
        /*spArgs.add(new StoredProcedureArg("@".concat(RESPONSE_REFERENCE_CODE), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER,
                UUID.randomUUID()));*/
        
        //
        return Utils.executeNonQueryStoredProcedure(spArgs);
        }   
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 05/09/2012
     * 
     * Calling Stored Procedure Prc_Delete_BigTable_Main_BusinessID
     ********************************************************************/
    public static void prc_DeleteBigTable_Main_BusinessID
    (
    int businessID,
    UUID responseReferenceCode
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Delete_BigTable_Main_BusinessID",
                "BigTable_Main");
        spArgs.add(new StoredProcedureArg("@".concat(TO_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        spArgs.add(new StoredProcedureArg("@".concat(RESPONSE_REFERENCE_CODE), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER,
                responseReferenceCode));
        
        Utils.executeNonQueryStoredProcedure(spArgs);
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 05/09/2012
     * 
     * Calling Stored Procedure Prc_Delete_BigTable_Main_UserID
     ********************************************************************/
    public static void prc_DeleteBigTable_Main_UserID
    (
    int userID,
    UUID responseReferenceCode
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Delete_BigTable_Main_UserID",
                "BigTable_Main");
        spArgs.add(new StoredProcedureArg("@".concat(TO_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        spArgs.add(new StoredProcedureArg("@".concat(RESPONSE_REFERENCE_CODE), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER,
                responseReferenceCode));
        
        Utils.executeNonQueryStoredProcedure(spArgs);
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 05/09/2012
     * 
     * Calling Stored Procedure Prc_Get_BigTable_Main_BusinessID
     ********************************************************************/
    public static ResultSet prc_GetBigTable_Main_BusinessID
    (
    int businessID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Get_BigTable_Main_BusinessID",
                "BigTable_Main");
        spArgs.add(new StoredProcedureArg("@".concat(TO_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 05/09/2012
     * 
     * Calling Stored Procedure Prc_Get_BigTable_Main_UserID
     ********************************************************************/
    public static ResultSet prc_GetBigTable_Main_UserID
    (
    int userID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Get_BigTable_Main_UserID",
                "BigTable_Main");
        spArgs.add(new StoredProcedureArg("@".concat(FROM_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 05/09/2012
     * 
     * Calling Stored Procedure Prc_Update_BigTable_Main_ReferenceCode
     ********************************************************************/
    public static boolean prc_UpdateBigTable_Main_ReferenceCode
    (
    int bigTableID,
    UUID responseReferenceCode
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Update_BigTable_Main_ReferenceCode",
                "BigTable_Main");
        spArgs.add(new StoredProcedureArg("@".concat(BIGTABLE_ID), 
                StoredProcedureArg.DataType.INTEGER, bigTableID));
        spArgs.add(new StoredProcedureArg("@".concat(RESPONSE_REFERENCE_CODE), 
                StoredProcedureArg.DataType.UNIQUEIDENTIFIER, responseReferenceCode));
        
        return Utils.executeNonQueryStoredProcedure(spArgs);
        }
    
    }
