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
public class Business_Provider_CheckInTable
    {

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/02/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
    final public static String EMPLOYER_ID = "EmployerID";
    final public static String PROVIDER_ID = "ProviderID";
    final public static String BUSINESS_ID = "BusinessID";
    final public static String CHECK_IN_DATETIME = "Check_IN_DateTime";
    final public static String CHECK_OUT_DATETIME = "Check_OUT_DateTime";
    final public static String CHECK_IN_ACK_STATE = "CheckInAckState";
    
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/02/2012
     * 
     * Calling Stored Procedure Prc_Insert_Business_Provider_CheckIn
     ********************************************************************/
    public static ResultSet prc_InsertBusinessProviderCheckIn
    (
    int employerID,
    int providerID,
    int businessID,
    Date checkInDateTime,
    Date checkOutDateTime,
    String checkInAckState
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Insert_Business_Provider_CheckIn",
                "Business_Provider_CheckIn");
        spArgs.add(new StoredProcedureArg("@".concat(EMPLOYER_ID), 
                StoredProcedureArg.DataType.INTEGER, employerID));
        spArgs.add(new StoredProcedureArg("@".concat(PROVIDER_ID), 
                StoredProcedureArg.DataType.INTEGER, providerID));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_IN_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, checkInDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_OUT_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, checkOutDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_IN_ACK_STATE), 
                StoredProcedureArg.DataType.CHAR, checkInAckState));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/02/2012
     * 
     * Calling Stored Procedure Prc_Ack_Business_Provider_CheckIn
     ********************************************************************/
    public static ResultSet prc_AckBusinessProviderCheckIn
    (
    int employerID,
    int providerID,
    int businessID,
    String checkInAckState
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Ack_Business_Provider_CheckIn",
                "Business_Provider_CheckIn");
        spArgs.add(new StoredProcedureArg("@".concat(EMPLOYER_ID), 
                StoredProcedureArg.DataType.INTEGER, employerID));
        spArgs.add(new StoredProcedureArg("@".concat(PROVIDER_ID), 
                StoredProcedureArg.DataType.INTEGER, providerID));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_IN_ACK_STATE), 
                StoredProcedureArg.DataType.CHAR, checkInAckState));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    }