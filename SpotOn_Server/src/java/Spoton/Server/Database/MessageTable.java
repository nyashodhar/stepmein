/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

import java.sql.ResultSet;
import java.sql.SQLXML;

/**
 *
 * @author kunal
 */
public class MessageTable
    {

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
    final public static String CHECK_IN_OUT_ID = "CheckINOUTID";
    final public static String MESSAGE = "Message";
    
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/06/2012
     * 
     * Calling Stored Procedure Prc_insert_Message
     ********************************************************************/
    public static ResultSet prc_InsertMessage
    (
    int checkINOUTID,
    SQLXML message
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_insert_Message",
                "Message");
        spArgs.add(new StoredProcedureArg("@".concat(CHECK_IN_OUT_ID), 
                StoredProcedureArg.DataType.INTEGER, checkINOUTID));
        spArgs.add(new StoredProcedureArg("@".concat(MESSAGE),
                StoredProcedureArg.DataType.XML, message));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }   
    
    }