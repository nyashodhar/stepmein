/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

import Spoton.Server.Logger.SpotOnLogger;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author kunal
 */
public class UserTable
    {

    private static SpotOnLogger logger = 
            new SpotOnLogger(UserTable.class.getName());
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Static Variables for the names of Columns
     ********************************************************************/
/*<<<<<<< .mine
    public static String USER_ID = "UserID";
    public static String FIRST_NAME = "FirstName";
    public static String MIDDLE_NAME = "MiddleName";
    public static String LAST_NAME = "LastName";
    public static String EMAIL = "Email";
    public static String PHONE_NUMBER = "PhoneNumber";
    public static String PASSWORD = "Password";
    public static String TYPE_OF_PLATFORM = "TypeOfPlatform";
    public static String FACEBOOK_ID = "FacebookID";
    public static String CREATE_DATETIME = "CreateDateTime";
    public static String USER_TYPE = "Usertype";
    public static String PUSH_NOTIFICATION = "PushNotification";
    public static String DATE_OF_BIRTH = "DateOfBirth";
    public static String ADDRESS_1 = "Address1";
    public static String ADDRESS_2 = "Address2";
    public static String CITY = "City";
    public static String ZIP = "Zip";
    public static String STATE = "State";
*/
    final public static String USER_ID = "UserID";
    final public static String FIRST_NAME = "FirstName";
    final public static String MIDDLE_NAME = "MiddleName";
    final public static String LAST_NAME = "LastName";
    final public static String EMAIL = "Email";
    final public static String PHONE_NUMBER = "PhoneNumber";
    final public static String PASSWORD = "Password";
    final public static String TYPE_OF_PLATFORM = "TypeOfPlatform";
    final public static String FACEBOOK_ID = "FacebookID";
    final public static String CREATE_DATETIME = "CreateDateTime";
    final public static String USER_TYPE = "Usertype";
    final public static String PUSH_NOTIFICATION = "PushNotification";
    final public static String DATE_OF_BIRTH = "DateOfBirth";
    final public static String ADDRESS_1 = "Address1";
    final public static String ADDRESS_2 = "Address2";
    final public static String CITY = "City";
    final public static String ZIP = "Zip";
    final public static String STATE = "State";
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Calling Stored Procedure Prc_Insert_User
     ********************************************************************/
    public static boolean prc_Insert_User
    (
    String firstName,
    String middleName,
    String lastName,
    String email,
    int phoneNumber,
    String password,
    String typeOfPlatform,
    String facebookID,
    String userType,
    String pushNotification,
    Date dateOfBirth,
    String address1,
    String address2,
    String city,
    int zip,
    String state
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Insert_User", "User");
        spArgs.add(new StoredProcedureArg("@".concat(FIRST_NAME), 
                StoredProcedureArg.DataType.CHAR, firstName));
        spArgs.add(new StoredProcedureArg("@".concat(MIDDLE_NAME),
                StoredProcedureArg.DataType.CHAR, middleName));
        spArgs.add(new StoredProcedureArg("@".concat(LAST_NAME), 
                StoredProcedureArg.DataType.CHAR, lastName));
        spArgs.add(new StoredProcedureArg("@".concat(EMAIL), 
                StoredProcedureArg.DataType.VARCHAR, email));
        spArgs.add(new StoredProcedureArg("@".concat(PHONE_NUMBER), 
                StoredProcedureArg.DataType.INTEGER, phoneNumber));
        spArgs.add(new StoredProcedureArg("@".concat(PASSWORD), 
                StoredProcedureArg.DataType.VARCHAR, password));
        spArgs.add(new StoredProcedureArg("@".concat(TYPE_OF_PLATFORM), 
                StoredProcedureArg.DataType.CHAR, typeOfPlatform));
        spArgs.add(new StoredProcedureArg("@".concat(FACEBOOK_ID), 
                StoredProcedureArg.DataType.VARCHAR, facebookID));
        spArgs.add(new StoredProcedureArg("@".concat(USER_TYPE), 
                StoredProcedureArg.DataType.VARCHAR, userType));
        spArgs.add(new StoredProcedureArg("@".concat(PUSH_NOTIFICATION), 
                StoredProcedureArg.DataType.CHAR, pushNotification));
        spArgs.add(new StoredProcedureArg("@".concat(DATE_OF_BIRTH), 
                StoredProcedureArg.DataType.DATETIME, dateOfBirth));
        spArgs.add(new StoredProcedureArg("@".concat(ADDRESS_1), 
                StoredProcedureArg.DataType.VARCHAR, address1));
        spArgs.add(new StoredProcedureArg("@".concat(ADDRESS_2), 
                StoredProcedureArg.DataType.VARCHAR, address2));
        spArgs.add(new StoredProcedureArg("@".concat(CITY), 
                StoredProcedureArg.DataType.VARCHAR, city));
        spArgs.add(new StoredProcedureArg("@".concat(ZIP), 
                StoredProcedureArg.DataType.INTEGER, zip));
        spArgs.add(new StoredProcedureArg("@".concat(STATE), 
                StoredProcedureArg.DataType.VARCHAR, state));
        
        
        return Utils.executeNonQueryStoredProcedure(spArgs);
        }
    
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/23/2012
     * 
     * Calling Stored Procedure Prc_UserDetails
     ********************************************************************/
    public static ResultSet prc_UserDetails
    (
    int userID
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_UserDetails", "User");
        spArgs.add(new StoredProcedureArg("@".concat(USER_ID), 
                StoredProcedureArg.DataType.INTEGER, userID));

        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    
    public static Boolean prc_CheckUserRegistered
    (
    String email
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_Check_User_Registered", "User");
        spArgs.add(new StoredProcedureArg("@".concat(EMAIL), 
                StoredProcedureArg.DataType.VARCHAR, email));

        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        if(rs!=null)
            {
            try
                {
                rs.next();
                if(rs.getString(1).equals("Registered"))
                    {
                    return true;
                    }
                else
                    return false;
                }
            catch(Exception ex)
                {
                logger.error(ex.getMessage());
                return false;
                }
            }
        else
            return null;
        }
    
    public static ResultSet prc_UserLogin
    (
    String uName,
    String password
    )
        {
        SPArgCollection spArgs = new SPArgCollection(
                "Prc_User_Login", "User");
        spArgs.add(new StoredProcedureArg("@".concat(EMAIL), 
                StoredProcedureArg.DataType.VARCHAR, uName));
        spArgs.add(new StoredProcedureArg("@".concat(PASSWORD), 
                StoredProcedureArg.DataType.VARCHAR, password));

        ResultSet rs = Utils.executeStoredProcedureToResultSet(spArgs);
        return rs;
        }
    
    }