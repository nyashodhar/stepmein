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
public class ReviewTable
    {

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
    final public static String USER_ID = "UserID";
    final public static String BUSINESS_ID = "BusinessID";
    final public static String CURRENT_DATETIME = "CurrentDateTime";
    final public static String NOTES = "Notes";
    final public static String RATING = "Rating";
    final public static String REVIEW_ID = "ReviewID";
    final public static String PROVIDER_ID = "ProviderID";
    final public static String REVIEW_STRING = "Review_String";
    final public static String REVIEW_CATEGORY_STRING = "ReviewCategory_String";

    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/06/2012
     * 
     * Calling Stored Procedure Prc_Insert_Review
     ********************************************************************/
    public static boolean prc_Insert_Review
    (
    int userID,
    int businessID,
    Date currentDateTime,
    String notes,
    float rating,
    int providerID,
    String reviewString,
    String reviewCategoryString
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Insert_Review",
                "Review");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        spArgs.add(new StoredProcedureArg("@".concat(CURRENT_DATETIME),
                StoredProcedureArg.DataType.DATETIME, currentDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(NOTES), 
                StoredProcedureArg.DataType.VARCHAR, notes));
        spArgs.add(new StoredProcedureArg("@".concat(RATING), 
                StoredProcedureArg.DataType.FLOAT, rating));
        spArgs.add(new StoredProcedureArg("@".concat(PROVIDER_ID), 
                StoredProcedureArg.DataType.INTEGER, providerID));
        spArgs.add(new StoredProcedureArg("@".concat(REVIEW_STRING), 
                StoredProcedureArg.DataType.VARCHAR, reviewString));
        spArgs.add(new StoredProcedureArg("@".concat(REVIEW_CATEGORY_STRING), 
                StoredProcedureArg.DataType.VARCHAR, reviewCategoryString));
        
        boolean rs = Utils.executeNonQueryStoredProcedure(spArgs);
        return rs;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 06/20/2012
     * 
     * Calling Stored Procedure Prc_Get_Review
     ********************************************************************/
    public static ResultSet prc_Get_Review
    (
    int businessID
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Get_Review",
                "Review");
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, businessID));
        
        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    }