/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

/**
 *
 * @author admin
 * 
 * It performs operations for checkin of the user and also checks for 
 * active session and checkin history.
 */
import Spoton.Server.Common.*;
import Spoton.Server.Database.BigTable_MainTable;
import Spoton.Server.Database.Check_IN_OUTTable;
import Spoton.Server.Database.UserTable;
import Spoton.Server.Impl.Restaurant.Restaurant;
import Spoton.Server.Logger.SpotOnLogger;
import Spoton.Server.Responses.UserCheckIn_UserResponse;
import Spoton.Server.Responses.UserCheckIn_BusinessTransfer;
import java.sql.Date;
import java.sql.ResultSet;

public class CheckIn implements IMessage
{
private String CHECK_IN = "CHECK_IN";
private String VALUE_FromID = new String();
private String VALUE_ToID = new String();
private String VALUE_MessageType = CHECK_IN;

private static String CheckIn_SUCCESS = "Success";
private static String CheckIn_FAILURE = "Failure";

public final static long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

private static SpotOnLogger logger = 
            new SpotOnLogger(CheckIn.class.getName());
    
@Override
public void SetFromID(String fromID) 
    {
    if(fromID != null)    
        VALUE_FromID = fromID;    
    }

@Override
public String GetFromID() 
    {    
    return VALUE_FromID;
    }

@Override
public void SetTOID(String ToID) 
    {
    if (ToID != null)
        VALUE_ToID = ToID;
    throw new UnsupportedOperationException("Not supported yet.");
    }

@Override
public String GetTOID() 
    {
    return VALUE_ToID;
    }

@Override
public void SetMessageType(String MessageType) 
    {
    if (MessageType != null)
        VALUE_MessageType = MessageType;
    throw new UnsupportedOperationException("Not supported yet.");
    }

@Override
public String GetMessageType() 
    {
    return VALUE_MessageType;
    }


/*private static class UserCheckin_BusinessTransfer
    {
    String userName;
    String phoneNumber;
    int age;
    Date DOB;
    String address;
    String city;
    String zip;
    String state;
    int partySize;
    Date checkINTime;
    }*/

/*********************************************************************
* Developed By: Kunal Jain                    
* 
* Check-In the User to the Business, Adds entry to the BigTable.
********************************************************************/
public static String checkInUser
(
int userID,
int businessID,
Date currentDateTime,
int numberOfPeople,
String checkINType,
Date timeOfReservation
)
    {
    String fbID = null;
    ResultSet rs = null;
    try
        {
        rs = UserTable.prc_UserDetails(userID);
        //TODO - Get FacebookID
        //fbID = rs.getString(UserTable.FACEBOOK_ID);
    
        //Inserting CheckIn    
        int checkInID = Check_IN_OUTTable.prc_InsertCheckIN(userID, fbID, businessID, timeOfReservation, null, -1, numberOfPeople, currentDateTime, checkINType);
        String xmlResp = "";
        UserCheckIn_BusinessTransfer Business_resp = null;
        
        //Creating Business Response
        if(rs != null)
            {
            Business_resp = new UserCheckIn_BusinessTransfer(rs, currentDateTime, timeOfReservation, numberOfPeople);
            xmlResp = Utils.getXML(Business_resp);
            }
        // Inserting Business Response into the BigTable
        boolean insert_status = BigTable_MainTable.prc_InsertBigTable_Main(businessID, userID, xmlResp, currentDateTime, timeOfReservation);
        UserCheckIn_UserResponse myResp = null;
        //If everything goes well, Send User Response
        if(insert_status)
            {
            myResp = new UserCheckIn_UserResponse(checkINType, businessID, CheckIn.CheckIn_SUCCESS, checkInID);
            }
        else
            myResp = new UserCheckIn_UserResponse(checkINType, businessID, CheckIn.CheckIn_FAILURE, checkInID);
        
        
        String userResp = Utils.getXML(myResp);
        return userResp;
        }
    catch(Exception ex)
        {
        logger.error(ex.getMessage());
        return Utils.getXML(new UserCheckIn_UserResponse(checkINType, businessID, CheckIn.CheckIn_FAILURE, -1));
        }
    }
    

/*********************************************************************
* Developed By: Kunal Jain                    
* 
* History of check-in by the User with userID.
********************************************************************/
public static ResultSet getCheckInHistory
(
int userID
)
    {
    ResultSet rs = Check_IN_OUTTable.prc_CheckIN_History(userID);
    return rs;
    }

/*********************************************************************
* Developed By: Kunal Jain                    
* 
* Gets active check-in of the userID.
********************************************************************/
public static ResultSet getActiveCheckIn
(
int userID
)
    {
    ResultSet rs = Check_IN_OUTTable.prc_Active_CheckINs(userID);
    return rs;
    }

}
