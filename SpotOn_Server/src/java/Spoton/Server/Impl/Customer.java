/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

/**
 *
 * @author Yash Narvaneni
 */
import Spoton.Server.Common.*;
import Spoton.Server.Database.*;
import Spoton.Server.Impl.Restaurant.Restaurant;
import Spoton.Server.Impl.UserCommon.*;
import Spoton.Server.Logger.SpotOnLogger;
import java.sql.ResultSet;
import java.util.*;

public class Customer implements IUser
{

private int CustomerID;
private String CustomerName;
private String PhoneNumber;
private String Email;
private UserType UserType;
private String Address;

private static SpotOnLogger logger = 
            new SpotOnLogger(Customer.class.getName());


/**
 * Constructor of the Class
 *  
 */
public Customer()
{}
public Customer(String UserID)
    {
    this(Integer.parseInt(UserID));
    }

public Customer(int UserID)
    {
    try
        {
        ResultSet rs = UserTable.prc_UserDetails(UserID);
        rs.next();
        int count = rs.getRow();
        
        if(count == 1)
            {
            CustomerID = UserID;
            CustomerName = rs.getString(UserTable.FIRST_NAME) + " "+rs.getString(UserTable.LAST_NAME);
            PhoneNumber = rs.getString(UserTable.PHONE_NUMBER);
            Address = rs.getString(UserTable.ADDRESS_1)+" "+rs.getString(UserTable.ADDRESS_2)
                    +"\n"+rs.getString(UserTable.CITY)+" "+rs.getString(UserTable.ZIP);
            UserType = UserType.CUSTOMER;
            Email = rs.getString(UserTable.EMAIL);
            
            }
        else if (count > 1)
            {
            logger.error("Duplicate Entries exist for the CustomerID= " + Integer.toString(UserID));
            throw new Exception ();
            }
        else if (count == 0)
            {
            logger.error("No User exists for the CustomerID= " + Integer.toString(UserID));
            throw new Exception ();
            }
        }
    catch(Exception er)
        {
        
        }
    }


public void SetCustomerID(int CustomerID)
    {
    this.CustomerID = CustomerID;
    }


public int getCustomerID()
    {
    return this.CustomerID;
    }

public void SetCustomerName(String CustomerName)
    {
    this.CustomerName = CustomerName;
    }

public String getCustomerName()
    {
    return this.CustomerName;
    }

public void SetCustomerPhone(String PhoneNumber)
    {
    this.PhoneNumber = PhoneNumber;
    }

public String getCustomerPhone()
    {
    return this.PhoneNumber;
    }

public String getCustomerEmail()
    {
    return this.Email;
    }

public void setCustomerEmail(String Email)
    {
    this.Email = Email;
    }

public UserType getUserType()
    {
    return UserType.CUSTOMER;
    }


public List<Business> getUserActiveCheckIns(int UserID)
    {
    //hashtable of checkIn objects
    try
        {
        ResultSet rs = Check_IN_OUTTable.prc_Active_CheckINs(UserID);
        ArrayList<Business> myList = new ArrayList<Business>();
        
        while(rs.next())
            {
            /*BusinessName = B.BusinessName,
	BusinessID = B.BusinessID,
	Address = B.Address1 + ISNULL(B.Address2,''),
	City = B.City,
	State = B.State,
	Zip = B.Zip,
	Longitude = B.Longitude,
	Latitude = B.Latitude*/
            Business myBus = new Business();
            myBus.SetBusinessID(rs.getInt(BusinessTable.BUSINESS_ID));
            myBus.SetBusinessName(rs.getString(BusinessTable.BUSINESS_NAME));
            myBus.SetAddress(rs.getString(BusinessTable.ADDRESS_1));
            myBus.SetCity(rs.getString(BusinessTable.CITY));
            myBus.SetState(rs.getString(BusinessTable.STATE));
            myBus.setZip(rs.getInt(BusinessTable.ZIP));
            myBus.SetLongitude(rs.getString(BusinessTable.LONGITUDE));
            myBus.SetLatitude(rs.getString(BusinessTable.LATITUDE));
            
            myList.add(myBus);
            }
        return myList;
        }
    catch(Exception e)
        {
        logger.error("SQL ERROR" + e.getMessage());
        return null;
        }
    
    
    }

/*
 *this stored procedure will return all the Businesses closer to the user location
 * <param>UserID</param>
 * <param>Longitude</param>
 * <param>Latitude</param>
 */
public List<Business> getNearByBusinesses(int UserID, String Longitude, String Latitude)
    {
    try
        {
        //get from cache else get frmo DB    
            
        ResultSet rs = BusinessTable.prc_NearBy_Businesses(UserID, Longitude, Latitude);
        ArrayList<Business> myList = new ArrayList<Business>();
        
        
        while(rs.next())
            {
            //Business myBus = new Business();
            //name, type, ID, distance, wait time, ratings
            //switch(rs.getString(BusinessTable.BUSINESS_TYPE))
            if(rs.getString(BusinessTable.BUSINESS_TYPE) == BusinessType.RESTAURANT)    
                {
                //case "Restaurant":
                Restaurant myRes = new Restaurant();
                myRes.SetBusinessName(rs.getString(BusinessTable.BUSINESS_NAME));
                myRes.SetBusinessID(rs.getInt(BusinessTable.BUSINESS_ID));
                myRes.setDistance(rs.getDouble(Business.BUSINESS_DISTANCE));//object related not database related
                myRes.setRating(rs.getDouble(Business.BUSINESS_RATING));
                myRes.SetWaitTime(rs.getInt(Restaurant.WAITTIME));
                
                myList.add(myRes);
                break;
                }
            
            
            
            }
        //get data from foursquare as well
        
        return myList;
        }
    catch(Exception e)
        {
        logger.error("ERROR GETTING BUSINESS LISTING:"+ e.getMessage());
        return null;
        }
    }

public void blockBusiness
(
int userID,
int businessID,
String reason
)
    {
    BlockTable.prc_InsertBlock(userID,
            businessID,
            reason,
            "TypeOfBlock",
            "User");
    }


}
