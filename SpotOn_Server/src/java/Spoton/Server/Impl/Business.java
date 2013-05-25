/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

/**
 *
 * @author admin
 * 
 * Stores all the properties of the Business and performs operations
 * on this Business.
 */
import Spoton.Server.Common.*;
import Spoton.Server.Database.BigTable_MainTable;
import Spoton.Server.Database.BlockTable;
import Spoton.Server.Database.BusinessSessionTable;
import Spoton.Server.Database.BusinessTable;
import Spoton.Server.Database.ReviewTable;
import Spoton.Server.Logger.SpotOnLogger;
import edu.hawaii.ics.csdl.simplejcs.SimpleJcs;
import java.io.*;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;
import org.json.simple.JSONArray;

public class Business implements Serializable
{
private int m_BusinessID;
private String m_BusinessName;
private String m_Addr1;
private String m_Addr2;
private String m_Address;
private int m_Zip;
private int m_BusinessAffiliateID;
private String m_Longitude;
private String m_Latitude;
private long m_Phone;
private Date m_CreateDateTime;
private String m_BusinessType;
private String m_City;
private String m_State;
private double m_distance;
private double m_Rating;
private String m_Rating_Category_String;
private String m_Email;
private String m_Password;
private String m_DisplayImage;


final public static int BUSINESS_DOES_NOT_EXIST = -1;
public static String BUSINESS_DISTANCE = "Distance";

public static String BUSINESS_RATING = "Rating";
final public static double RADIUS_OF_EARTH = 6371;

private static SpotOnLogger logger = new SpotOnLogger(Business.class.getName());

public Business(String BusinessID)
    {
    this(Integer.parseInt(BusinessID));
    }

public Business()
    {
    }

public Business(int BusinessID)
    {
    // get from BusinessCollection, else get from DB
    this.SetBusinessID(BusinessID);
    BusinessCollection myBC = new BusinessCollection();
    if(myBC != null || myBC.containsKey(BusinessID))
        {
             
        }
    else
        {
        ResultSet rs = BusinessTable.prc_GetBusinessInfo(BusinessID);
        try
            {
            m_BusinessName = rs.getString(BusinessTable.BUSINESS_NAME);
            m_Addr1 = rs.getString(BusinessTable.ADDRESS_1);
            m_Addr2 = rs.getString(BusinessTable.ADDRESS_2);
            m_Zip = rs.getInt(BusinessTable.ZIP);
            m_BusinessAffiliateID = rs.getInt(BusinessTable.BUSINESS_AFFILIATION_ID);
            m_Longitude = rs.getString(BusinessTable.LONGITUDE);
            m_Latitude = rs.getString(BusinessTable.LATITUDE);
            m_Phone = rs.getLong(BusinessTable.PHONE);
            m_CreateDateTime = rs.getDate(BusinessTable.CREATE_DATETIME);
            m_BusinessType = rs.getString(BusinessTable.BUSINESS_TYPE);
            m_City = rs.getString(BusinessTable.CITY);
            m_State = rs.getString(BusinessTable.STATE);
            m_Rating = rs.getDouble(BusinessTable.RATING);
            m_Email = rs.getString(BusinessTable.EMAIL);
            }
        catch(Exception e)
            {
        
            }
        }
    
    }

public void SetBusinessID(int BusinessID)
    {
    this.m_BusinessID = BusinessID;
    
    }

public int getBusinessID()
    {
    return this.m_BusinessID;
    }

public void SetBusinessName(String BusinessName)
    {
    this.m_BusinessName = BusinessName;
    
    }

public String getBusinessName()
    {
    return this.m_BusinessName;
    }

public void SetAddress(String Addr1, String Addr2)
    {
    this.m_Addr1 = Addr1;
    this.m_Addr2 = Addr2;
    this.m_Address = Addr1 + " " + Addr2;
    
    }

public void SetAddress(String Addr)
    {   
    this.m_Address = Addr;
    }

public String getAddress()
    {
        if(this.m_Addr1 != null)
        {
            if(this.m_Addr2 != null)
                return this.m_Addr1+this.m_Addr2;
            else
                return this.m_Addr1;
        }
        else
        {
        return this.m_Address;
        }
    
    }

public int getZip()
    {
    return this.m_Zip;
    }

public void setZip(int Zip)
    {
    this.m_Zip = Zip;
    }

public int GetBusinessAffiliateID()
    {
    return this.m_BusinessAffiliateID;
    }

public void SetBusinessAffiliateID(int BusinessID)
    {
    
    }

public String GetCity()
    {
    return this.m_City;
    }

public void SetCity(String City)
    {
    this.m_City = City;
    }

public void SetState(String State)
    {
    this.m_State = State;
    }

public String GetState()
    {
    return this.m_State;
    }

public String GetLongitude()
    {
    return this.m_Longitude;
    }

public void SetLongitude(String Longitude)
    {
    this.m_Longitude = Longitude;
    }

public String GetLatitude()
    {
    return this.m_Latitude;
    }

public void SetLatitude(String Latitude)
    {
    this.m_Latitude = Latitude;
    }

public void setDistance(double Distance)
    {
    this.m_distance = Distance;
    }

public double getDistance()
    {
    return this.m_distance;
    }


public void setDisplayImage(String displayimage)
    {
    this.m_DisplayImage = displayimage;
    }

public String getDisplayImage()
    {
    return this.m_DisplayImage;
    }

public void setRating(double rating) 
    {
    this.m_Rating = rating;
    }

public double getRating()
    {
    return m_Rating;
    }

public void setRatingCategoryString(String ratingCategory)
    {
    this.m_Rating_Category_String = ratingCategory;
    }

public String getRatingCategoryString()
    {
    try{
    if(this.m_Rating_Category_String.equals(null))
        {
        return "";
        }
    return this.m_Rating_Category_String;
    }
    catch(Exception e)
    {
    logger.error("Rating Category String error: "+e.getMessage());
    return "";
    }
    
    }

public void setBusinessType(String BusinessType)
    {
    this.m_BusinessType = BusinessType;
    }


public String getBusinessType()
    {
    return m_BusinessType;
    }

/*public int getBusinessType()
    {
    return m_BusinessType;
    }
*/
public void SetPhone(long Phone)
    {
    this.m_Phone = Phone;
    }
public long GetPhone()
    {
    return this.m_Phone;
    }
public void SetEmail(String Email)
    {
    this.m_Email = Email;
    }

public String GetEmail()
    {
    return this.m_Email;
    }

public void SetPassword(String Password)
    {
    this.m_Password = Password;
    }

public String GetPassword()
    {
    return this.m_Password;
    }

public boolean checkRegisteredBusiness()
    {
    if(Validate.isEmail(GetEmail()))
        {
        return BusinessTable.prc_Check_Business_Registered(GetEmail());
        }
    return false;
    }

/*********************************************************************
* Developed By: Kunal Jain                    
* 
* Registers this Business Object and adds entry to the database.
********************************************************************/
public void registerBusiness()
    {
    String password;
    String latitude, longitude;
    JSONArray latlong = Utils.getLatLong(m_Address);
    if(latlong != null)
        {
        latitude = (Double)latlong.get(1) + "";
        longitude = (Double)latlong.get(0) + "";
        }
    else
        {
        latitude = longitude = "";
        }
    m_Latitude = latitude;
    m_Longitude = longitude;
    if(Validate.isEmail(GetEmail()))
        {
        password = UserCommon.encryptToMD5(GetPassword());
        java.util.Date curDate = new java.util.Date();
        
        BusinessTable.prc_InsertBusiness(m_BusinessName, m_Addr1, m_Addr2, 
                m_Zip, m_BusinessAffiliateID, m_Longitude, m_Latitude,
                m_Phone, new Date(curDate.getTime()), m_BusinessType + "", 
                m_City, m_State, m_Email,
                password);
        }
    }

/*********************************************************************
* Developed By: Kunal Jain                    
* 
* Logs the Business and return the Business ID to the XML Parser
********************************************************************/
public int loginBusiness()
    {
    String password = UserCommon.encryptToMD5(GetPassword());
    
    int businessID = BUSINESS_DOES_NOT_EXIST;
    
    ResultSet rs = BusinessTable.prc_BusinessLogin(m_Email, password);
    try
        {
        if(rs.next())
            {
            businessID = rs.getInt(BusinessTable.BUSINESS_ID);
            }
        }
    catch(Exception ex)
        {
        logger.error(ex.getMessage());
        }
    return businessID;
    }

/*********************************************************************
     * Developed By: Kunal Jain                    Date: 03/07/2012
     * 
     * Checking if the business is currently has active session.
     ********************************************************************/
public static boolean checkActiveSession(String userName, UUID sessionID)
    {
    ResultSet rs = BusinessSessionTable.prc_ActiveBusinessSession(
            userName, sessionID);
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
     * Developed By: Kunal Jain                    Date: 03/07/2012
     * 
     * Logs the business out of active session.
     ********************************************************************/
public static void logoutActiveSession(String userName, UUID sessionID)
    {
    try
        {
        BusinessSessionTable.prc_DeleteBusinessSession(userName, sessionID);
        }
    catch(Exception ex)
        {
        logger.error(ex.getMessage());
        }
    }

/*********************************************************************
     * Developed By: Kunal Jain                    
     * 
     * Finds the distance in kilometers of the two geological locations
     * Probably not the right place for this method.
     ********************************************************************/
public static double getDistance
(
double startLongitude,
double startLatitude,
double destLongitude,
double destLatitude
)
    {
    double dist = 0;
    double dLat = Math.toRadians(destLatitude-startLatitude);  
    double dLon = Math.toRadians(destLongitude-startLongitude);  
    dist = Math.sin(dLat/2) * Math.sin(dLat/2) +  
            Math.cos(Math.toRadians(startLatitude)) *
            Math.cos(Math.toRadians(destLatitude)) *  
            Math.sin(dLon/2) * Math.sin(dLon/2);  
    dist = 2 * Math.asin(Math.sqrt(dist));  
    return RADIUS_OF_EARTH * dist;      
    }

public static String businessPolling
(
int businessID,
UUID responseReferenceCode,
int waitTime
)
    {
    if(responseReferenceCode == null)
        {
        ResultSet rs = BigTable_MainTable.prc_GetBigTable_Main_BusinessID(businessID);
        String resp = "<businessPollingResp>";
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
            resp = resp.concat("</businessPollingResp>");
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
            BigTable_MainTable.prc_DeleteBigTable_Main_BusinessID(businessID, responseReferenceCode);
            }
        catch(Exception ex)
            {
            logger.equals(ex.getMessage());
            }
        }
    return null;
    }


/*********************************************************************
* Developed By: Kunal Jain                    
* 
* Blocks the user from doing activities from this Business.
********************************************************************/
public static void blockUser
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
            "Business");
    }

/*********************************************************************
* Developed By: Kunal Jain                    
* 
* Gets the overall rating from the DB rating string.
********************************************************************/
private static double getOverallRating
(
float rating
)
    {
    double overallRating = 0;
    // Processing String for getting Overall Rating.
    return overallRating;
    }

/*********************************************************************
* Developed By: Kunal Jain                    
* 
* Adding a review to this Business.
********************************************************************/
/*public static void reviewBusiness
(
int userID,
int businessID,
float rating,
int checkInID
)
    {
    ReviewTable.prc_Insert_Review(
            userID,
            businessID,
            (java.sql.Date)new java.util.Date(),
            "notes",
            rating,
            -1,
            "reviewString",
            "reviewCategoryString");
    // getBusinessType
    Business curBusiness = BCManager.getBusinessCollection().get(businessID);
    
    ResultSet rsRating = ReviewTable.prc_Get_Review(businessID);
    
    double totalRating = 0;
    int reviewCount = 0;
    try
        {
        while(rsRating.next())
            {
            reviewCount++;
            totalRating += getOverallRating(
                    rsRating.getString(ReviewTable.RATING));
            }
        reviewCount++;
        totalRating += getOverallRating(rating);
        double newAvgRating;
        newAvgRating = totalRating/(double)reviewCount;
        
        curBusiness.setRating(newAvgRating);
        }
    catch(SQLException sqlEx)
        {
        logger.error(sqlEx.getMessage());
        }
    }
*/
}
