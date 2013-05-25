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
public class BusinessTable
{

/*********************************************************************
* Developed By: Yashodhar Narvaneni                    Date: 01/23/2012
* 
* Static Variables for the names of Columns
 ********************************************************************/
public static String BUSINESS_ID = "BusinessID";
public static String BUSINESS_NAME = "BusinessName";
public static String ADDRESS_1 = "Address1";
public static String ADDRESS_2 = "Address2";
public static String ZIP = "Zip";
public static String BUSINESS_AFFILIATION_ID = "BusinessAffiliationID";
public static String LONGITUDE = "Longitude";
public static String LATITUDE = "Latitude";
public static String PHONE = "Phone";
public static String CREATE_DATETIME = "CreateDateTime";
public static String BUSINESS_TYPE = "BusinessType";
public static String CITY = "City";
public static String STATE = "State";
public static String ADDRESS = "Address";
public static String RATING = "Rating";
public static String EMAIL = "Email";
public static String PASSWORD = "Password";


public static String TABLE_NAME = "Business";

private static SpotOnLogger logger = new SpotOnLogger(
        BusinessTable.class.getName());

public static ResultSet prc_NearBy_Businesses
    (
    int UserID,    
    String Longitude, 
    String Latitude
    )
    {
    SPArgCollection spArgs = new SPArgCollection("Prc_NearBy_Businesses", BusinessTable.TABLE_NAME);
    spArgs.add(new StoredProcedureArg("@"+UserTable.USER_ID, StoredProcedureArg.DataType.INTEGER, UserID));
    spArgs.add(new StoredProcedureArg("@" + LONGITUDE, 
                StoredProcedureArg.DataType.CHAR, Longitude));
    spArgs.add(new StoredProcedureArg("@" + LATITUDE,
                StoredProcedureArg.DataType.CHAR, Latitude));
    
    ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
    return rs;
    }

public static ResultSet prc_Get_Businesses_By_User
    (
    int UserID    
    )
    {
    SPArgCollection spArgs = new SPArgCollection(
                "Prc_Get_Businesses_By_User", BusinessTable.TABLE_NAME);
    spArgs.add(new StoredProcedureArg("@".concat(UserTable.USER_ID), 
                StoredProcedureArg.DataType.INTEGER, UserID));

    ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
    return rs;
        
        
       // SPArgCollection spArgs = new SPArgCollection("Prc_Get_Businesses_By_User", BusinessTable.TABLE_NAME);
    //spArgs.add(new StoredProcedureArg("@"+UserTable.USER_ID, StoredProcedureArg.DataType.INTEGER, UserID));
    
    //ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
    //return rs;
    }

public static ResultSet prc_Get_Businesses()
    {
    SPArgCollection spArgs = new SPArgCollection("prc_Get_Businesses", BusinessTable.TABLE_NAME);
    
    ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
    
    return rs;
    }
 
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/06/2012
     * 
     * Calling Stored Procedure Prc_Insert_Business
     ********************************************************************/
    public static boolean prc_InsertBusiness
    (
    String businessName,
    String address1,
    String address2,
    int zip,
    int businessAffiliationID,
    String longitude,
    String latitude,
    long phone,
    Date createDateTime,
    String businessType,
    String city,
    String state,
    String email,
    String password
    )
        {
        SPArgCollection spArgs = new SPArgCollection("Prc_Insert_Business",
                "Business");
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_NAME), 
                StoredProcedureArg.DataType.CHAR, businessName));
        spArgs.add(new StoredProcedureArg("@".concat(ADDRESS_1),
                StoredProcedureArg.DataType.VARCHAR, address1));
        spArgs.add(new StoredProcedureArg("@".concat(ADDRESS_2), 
                StoredProcedureArg.DataType.VARCHAR, address2));
        spArgs.add(new StoredProcedureArg("@".concat(ZIP), 
                StoredProcedureArg.DataType.INTEGER, zip));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_AFFILIATION_ID), 
                StoredProcedureArg.DataType.INTEGER, businessAffiliationID));
        spArgs.add(new StoredProcedureArg("@".concat(LONGITUDE), 
                StoredProcedureArg.DataType.GEOGRAPHY, longitude));
        spArgs.add(new StoredProcedureArg("@".concat(LATITUDE), 
                StoredProcedureArg.DataType.GEOGRAPHY, latitude));
        spArgs.add(new StoredProcedureArg("@".concat(PHONE), 
                StoredProcedureArg.DataType.BIGINT, phone));
        spArgs.add(new StoredProcedureArg("@".concat(CREATE_DATETIME), 
                StoredProcedureArg.DataType.DATETIME, createDateTime));
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_TYPE), 
                StoredProcedureArg.DataType.VARCHAR, businessType));
        spArgs.add(new StoredProcedureArg("@".concat(CITY), 
                StoredProcedureArg.DataType.VARCHAR, city));
        spArgs.add(new StoredProcedureArg("@".concat(STATE), 
                StoredProcedureArg.DataType.VARCHAR, state));
        spArgs.add(new StoredProcedureArg("@".concat(EMAIL), 
                StoredProcedureArg.DataType.VARCHAR, email));
        spArgs.add(new StoredProcedureArg("@".concat(PASSWORD), 
                StoredProcedureArg.DataType.VARCHAR, password));
        
        return Utils.executeNonQueryStoredProcedure(spArgs);
        }   
    
    public static boolean prc_Check_Business_Registered
    (
    String email
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Check_Business_Registered", "Business");
        spArgs.add(new StoredProcedureArg("@".concat(EMAIL), 
                StoredProcedureArg.DataType.VARCHAR, email));

        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        try
            {
            rs.next();
            logger.info("ResultSet output = " + rs.getString(1));
            if(rs.getString(1).equals("Registered"))
                {
                return true;
                }
            }
        catch(Exception ex)
            {
            logger.error(ex.getMessage());
            }
        return false;
        }
    
    public static ResultSet prc_BusinessLogin
    (
    String uName,
    String password
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Business_Login", "Business");
        spArgs.add(new StoredProcedureArg("@".concat(EMAIL), 
                StoredProcedureArg.DataType.VARCHAR, uName));
        spArgs.add(new StoredProcedureArg("@".concat(PASSWORD), 
                StoredProcedureArg.DataType.VARCHAR, password));

        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    public static ResultSet prc_Get_SearchBusinessesByName
    (
    String businessName,
    double latitude,
    double longitude
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Get_SearchBusinessesByName", "Business");
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_NAME), 
                StoredProcedureArg.DataType.VARCHAR, businessName));
        spArgs.add(new StoredProcedureArg("@".concat(LATITUDE), 
                StoredProcedureArg.DataType.FLOAT, latitude));
        spArgs.add(new StoredProcedureArg("@".concat(LONGITUDE), 
                StoredProcedureArg.DataType.FLOAT, longitude));

        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    public static ResultSet prc_GetBusinessInfo(int BusinessID)
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Get_BusinessInfo", "Business");
        spArgs.add(new StoredProcedureArg("@".concat(BUSINESS_ID), 
                StoredProcedureArg.DataType.INTEGER, BusinessID));

        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    
    }
