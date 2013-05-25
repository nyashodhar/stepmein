/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

import Spoton.Server.Common.Validate;
import Spoton.Server.Database.*;
import Spoton.Server.Logger.SpotOnLogger;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 * 
 * It takes care of the User.
 * IT registers the user, logs him in and maintains his current session.
 */


public class UserCommon 
{
    
final public static int USER_DOES_NOT_EXIST = -1;
private static SpotOnLogger logger = 
            new SpotOnLogger(UserCommon.class.getName());
private static final String TWILIO_SID = "AC5bfa799d4cd4bdb1d3e9b20384975c80";
private static final String TWILIO_AUTH = "f6a3932aa4297bea82144175c2045088";

private static String createTextMessage(String customerName, String businessName, int peopleCount, java.sql.Date myCheckInDate)
    {
    String message = "Hi ";
    message = message.concat(customerName);
    message = message.concat(", You just checked into "+businessName.replaceAll("\\s","")+" for a party of "+ Integer.toString(peopleCount) + " for "+ myCheckInDate.toString());
    
    return message;
    }
/**
 * Send Text message through twilio
 */
public static void sendTextMessage(java.sql.Date myCheckInDate, int peopleCount, int businessID, int userID) throws TwilioRestException 
    {
    final TwilioRestClient client = new TwilioRestClient(TWILIO_SID, TWILIO_AUTH);
    final Account mainAccount = client.getAccount();
    
    final SmsFactory smsFactory = mainAccount.getSmsFactory();
    final Map<String, String> smsParams = new HashMap<String, String>();
    
    Customer myUser = new Customer(userID);
    //String phoneNumber = myUser.getCustomerPhone();
    String to_phoneNumber = "4845245846";
    String customerName = myUser.getCustomerName().trim().replaceAll(" +", " ");
    String businessName = BCManager.getBusinessCollection().get(businessID).getBusinessName();
    String message = createTextMessage(customerName, businessName, peopleCount, myCheckInDate);
    String message1 = message;
    //int phoneNumber = 
    if(message.length() > 160)
        {
        
        }
    smsParams.put("To", to_phoneNumber); // Replace with a valid phone number
    smsParams.put("From", "4848734114"); // Replace with a valid phone number in your account
    smsParams.put("Body", message);
    smsFactory.create(smsParams);
    }

    
public enum UserType
    {
    SUPERADMIN,
    ADMINISTRATOR,
    MANAGER,
    PROVIDER,
    CUSTOMER
    }    

/*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/06/2012
     * 
     * Encrypt text using MD5
     ********************************************************************/
public static String encryptToMD5(String text)
    {
    String md5Text = "";
    try
        {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(text.getBytes(Charset.forName("UTF8")));
        byte[] resultByte = messageDigest.digest();
        BigInteger bigInt = new BigInteger(1, resultByte);
        md5Text = bigInt.toString(16);
        while(md5Text.length() < 32)
            {
            md5Text = "0" + md5Text;    
            }
        }
    catch(Exception e)
        {
        logger.error(e.getMessage());
        }
    return md5Text;
    }

/*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/06/2012
     * 
     * Register New User.
     ********************************************************************/
public static boolean registerUser(String userName, String email, String password, String type)
    {
    boolean status = false;
    if(Validate.isEmail(email))
        {
        password = encryptToMD5(password);

        status = UserTable.prc_Insert_User(userName, "", "", email, 0, password, "", 
                "", type, "", null, "", "", "", 0, "");
        
        }
    return status;
    }


/*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/10/2012
     * 
     * Login User
     ********************************************************************/
public static int loginUser(String uName, String password)
    {
    password = encryptToMD5(password);
    
    int userID = USER_DOES_NOT_EXIST;
    
    ResultSet rs = UserTable.prc_UserLogin(uName, password);
    try
        {
        if(rs.next())
            {
            userID = rs.getInt(UserTable.USER_ID);
            }
        }
    catch(Exception ex)
        {
        logger.error(ex.getMessage());
        }
    return userID;
    }

/*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/10/2012
     * 
     * Checking if the email is registered or not.
     ********************************************************************/
public static Boolean checkRegisteredUser(String email)
    {
    if(Validate.isEmail(email))
        {
        return UserTable.prc_CheckUserRegistered(email);
        }
    return false;
    }

/*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/11/2012
     * 
     * Checking if the user is currently has active session.
     ********************************************************************/
public static boolean checkActiveSession(String userID, UUID sessionID)
    {
    int uID = Integer.parseInt(userID);
    ResultSet rs = UserSessionTable.prc_ActiveUserSession(uID, sessionID);
    try
        {
        rs.next();
        if(rs.getString(1).equalsIgnoreCase(sessionID.toString()))
            {
            return true;
            }
        return false;
        }
    catch(Exception ex)
        {
        return false;
        }
    }

/*********************************************************************
* Developed By: Kunal Jain                    
* 
* Deletes the User's active session.
********************************************************************/
public static boolean logoutActiveSession(String userID, UUID sessionID)
    {
    try
        {
        int uID = Integer.parseInt(userID);
        return UserSessionTable.prc_DeleteUserSession(uID, sessionID);
        }
    catch(Exception ex)
        {
        logger.error(ex.getMessage());
        return false;
        }
    }

public static String userPolling
(
int userID,
UUID responseReferenceCode
)
    {
    if(responseReferenceCode == null)
        {
        ResultSet rs = BigTable_MainTable.prc_GetBigTable_Main_UserID(userID);
        String resp = "<userPollingResp>";
        UUID newResponseReferenceCode = UUID.randomUUID();
        try
            {
            while(rs.next())
                {
                BigTable_MainTable.prc_UpdateBigTable_Main_ReferenceCode
                        (rs.getInt(BigTable_MainTable.BIGTABLE_ID),
                        newResponseReferenceCode);
                resp = resp.concat("<record>");
                resp = resp.concat("<" + BigTable_MainTable.BIGTABLE_ID +
                        " value=\"" + rs.getInt(BigTable_MainTable.BIGTABLE_ID)
                        + "\"/>");
                resp = resp.concat("<" + BigTable_MainTable.TO_ID +
                        " value=\"" + rs.getInt(BigTable_MainTable.TO_ID)
                        + "\"/>");
                resp = resp.concat("<" + BigTable_MainTable.FROM_ID +
                        " value=\"" + rs.getInt(BigTable_MainTable.FROM_ID)
                        + "\"/>");
                resp = resp.concat("<" + BigTable_MainTable.MESSAGE +
                        " value=\"" + rs.getString(BigTable_MainTable.MESSAGE)
                        + "\"/>");
                resp = resp.concat("<" + BigTable_MainTable.CURRENT_DATETIME +
                        " value=\"" + rs.getDate(BigTable_MainTable.CURRENT_DATETIME)
                        + "\"/>");
                resp = resp.concat("<" + BigTable_MainTable.CHECKIN_TIME +
                        " value=\"" + rs.getDate(BigTable_MainTable.CHECKIN_TIME)
                        + "\"/>");
                resp = resp.concat("<" + BigTable_MainTable.RESPONSE_REFERENCE_CODE +
                        " value=\"" + newResponseReferenceCode
                        + "\"/>");
                resp = resp.concat("</record>");
                }
            resp = resp.concat("</userPollingResp>");
            return resp;
            }
        catch(Exception ex)
            {
            logger.error(ex.getMessage());
            }
        }
    else
        {
        try
            {
            BigTable_MainTable.prc_DeleteBigTable_Main_UserID(userID, responseReferenceCode);
            }
        catch(Exception ex)
            {
            logger.equals(ex.getMessage());
            }
        }
    return null;
    }


/*
 * 
 * Gets the List of Businesses which are not blocked by user or which did not block the user
 * 
 */
public static ArrayList<Business> Get_Businesses_By_User(int userID)
    {
    ResultSet rs = BusinessTable.prc_Get_Businesses_By_User(userID);
    ArrayList<Business> myBusinessList = new ArrayList<Business>();
    try
        {
        while(rs.next())
            {
            Business myBus = new Business();
            myBus.SetBusinessID(rs.getInt("BusinessID"));
            myBus.SetBusinessName(rs.getString("BusinessName"));
            myBus.SetAddress(rs.getString("Address1"), rs.getString("Address2"));
            myBus.setZip(rs.getInt("Zip"));
            myBus.setBusinessType(rs.getString("BusinessType"));
            myBus.SetCity(rs.getString("City"));
            myBus.SetState(rs.getString("State"));
            myBus.SetPhone(rs.getLong("Phone"));
            //myBus.setRating(rs.getDouble("Rating"));
            
            myBusinessList.add(myBus);
            }
        }
    catch(Exception e)
        {
        logger.error("GET BUSINESSES BY USER: "+ e.getMessage());
        }
    return myBusinessList;
    }

/*
 * 
 * Gets the List of Businesses which are not blocked by user or which did not block the user
 * 
 */
public static ArrayList<UserCheckInData> Get_User_CheckIn_History(int userID)
    {
    ResultSet rs = Check_IN_OUTTable.prc_CheckIN_History(userID);
    ArrayList<UserCheckInData> myList = new ArrayList<UserCheckInData>();
    try
        {
        while(rs.next())
            {
            UserCheckInData myCheckInData = new UserCheckInData();
            myCheckInData.SetBusinessID(rs.getInt(BusinessTable.BUSINESS_ID));
            myCheckInData.SetBusinessName(rs.getString(BusinessTable.BUSINESS_NAME));
            myCheckInData.SetBusinessType(rs.getString(BusinessTable.BUSINESS_NAME));
            myCheckInData.SetCheckInDate(rs.getDate(Check_IN_OUTTable.CHECK_IN_DATETIME));
            myCheckInData.SetCheckOutDate(rs.getDate(Check_IN_OUTTable.CHECK_OUT_DATETIME));
            //TODO: Uncomment the line below
            //myCheckInData.SetBusinessIconUrl(rs.getString(BusinessImages.IMAGE_PATH));
            myList.add(myCheckInData);
            }
        return myList;
        }
    catch(Exception e)
        {
        logger.error("Error in Get_User_CheckIn_History: "+ e.getMessage());    
        return myList;
        }
    
    }

public static ArrayList<UserCheckInData> Get_ActiveCheckIns(int userID)
    {
    ResultSet rs = Check_IN_OUTTable.prc_Active_CheckINs(userID);
    ArrayList<UserCheckInData> myList = new ArrayList<UserCheckInData>();
    try
        {
        while(rs.next())
            {
            UserCheckInData myCheckInData = new UserCheckInData();
            myCheckInData.SetBusinessID(rs.getInt(BusinessTable.BUSINESS_ID));
            myCheckInData.SetBusinessName(rs.getString(BusinessTable.BUSINESS_NAME));
            myCheckInData.SetBusinessType(rs.getString(BusinessTable.BUSINESS_NAME));
            myCheckInData.SetCheckInDate(rs.getDate(Check_IN_OUTTable.CHECK_IN_DATETIME));
            myCheckInData.SetCheckOutDate(rs.getDate(Check_IN_OUTTable.CHECK_OUT_DATETIME));
            //TODO: Uncomment the line below
            //myCheckInData.SetBusinessIconUrl(rs.getString(BusinessImages.IMAGE_PATH));
            myList.add(myCheckInData);
            }
        return myList;
        }
    catch(Exception e)
        {
        logger.error("Error in Get_User_CheckIn_History: "+ e.getMessage());    
        return myList;
        }
    
    }

public static boolean Insert_UserReview(int userID, int businessID, String sessionID, String Notes, String reviewString)
    {
    BusinessCollection myBC = BCManager.getBusinessCollection();
    //get data from the collection
    String businessType = myBC.get(businessID).getBusinessType();
    Business myBiz = myBC.get(businessID);
    String ratingCategoryString = myBiz.getRatingCategoryString();
    
    
    reviewString = reviewString.replaceAll(" ", "");
    String[] reviewArray = reviewString.split(":");
    int RatingSum = 0;
    int arrayLength = reviewArray.length;
    Date utilDate = new Date();
    java.sql.Date currDate = new java.sql.Date(utilDate.getTime());
    
    for(int i =0; i<arrayLength; i++)
        {
        RatingSum = RatingSum+Integer.parseInt(reviewArray[i]);
        }

    double Rating = (double)RatingSum/(double)arrayLength;
    
    return ReviewTable.prc_Insert_Review(userID, businessID, currDate, Notes, (float)Rating, -1, reviewString, ratingCategoryString);

    }

}
