/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

import java.sql.ResultSet;

/**
 *
 * @author kunal
 */
public class BlockTable
    {

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
    final public static String USER_ID = "UserID";
    final public static String BUSINESS_ID = "BusinessID";
    final public static String CURRENT_DATETIME = "CurrentDateTime";
    final public static String REASON = "Reason";
    final public static String TYPE_OF_BLOCK = "TypeOfBlock";
    final public static String WHO_BLOCKS = "WhoBlocks";

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Calling Stored Procedure Prc_Insert_Block
     ********************************************************************/
    public static ResultSet prc_InsertBlock
    (
    int userID,
    int businessID,
    String reason,
    String typeOfBlock,
    String whoBlocks
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Insert_Block",
                "Block");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID),
                StoredProcedureArg.DataType.INTEGER, businessID));
        spArgs.add(new StoredProcedureArg("@".concat(REASON), 
                StoredProcedureArg.DataType.VARCHAR, reason));
        spArgs.add(new StoredProcedureArg("@".concat(TYPE_OF_BLOCK), 
                StoredProcedureArg.DataType.CHAR, typeOfBlock));
        spArgs.add(new StoredProcedureArg("@".concat(WHO_BLOCKS), 
                StoredProcedureArg.DataType.CHAR, whoBlocks));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }   
    }