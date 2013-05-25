/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

/**
 *
 * @author admin
 * 
 * It creates and performs operation on the Business Collection.
 */
import Spoton.Server.Database.BusinessTable;
import Spoton.Server.Impl.Restaurant.Restaurant;
import edu.hawaii.ics.csdl.simplejcs.SimpleJcs;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



public class BCManager
{
public static String BUSINESS_COLLECTION = "BusinessCollection";

public static String BUSINESS_COLLECTION_NAME = "SpotOnCache";

final private static double POINT_CLOSENESS_THRESHOLD = 10; // in kilometeres


private static SimpleJcs<String, BusinessCollection> myCache = new SimpleJcs<String, BusinessCollection>(BUSINESS_COLLECTION_NAME);


public void CreateBusinessCollectionCache()
    {
    myCache.get(BUSINESS_COLLECTION_NAME);
    
    
    }


public static BusinessCollection getBusinessCollection()
    {
    
    BusinessCollection myBC = new BusinessCollection();
    
    return myBC;
    }

public void cacheBusinessCollection()
    {
    SimpleJcs<String, BusinessCollection> myCache = new SimpleJcs<String, BusinessCollection>(BUSINESS_COLLECTION_NAME);
    
    }

public static SimpleJcs getCacheObj()
    {
    return myCache;
    }

public static Business getBusinessInfo(int businessID, String businessName)
    {
    BusinessCollection bc = new BusinessCollection();
    Business business = bc.get(businessID);
    //TODO : check business Name comparision.
    if(business.getBusinessName().equalsIgnoreCase(businessName))
        return business;
    return null;
    }


public static BusinessCollection searchBusiness(String searchVal, double latitude,
        double longitude, int userID)
    {
    //TODO: UserID is not taken care.
    BusinessCollection bc = new BusinessCollection();
    Business tmpBusiness;
    
    BusinessCollection result = new BusinessCollection();
    result.clear();
    
    Iterator it = bc.entrySet().iterator();
    while (it.hasNext())
        {
        Map.Entry pairs = (Map.Entry)it.next();
        tmpBusiness = (Business)pairs.getValue();
        if(tmpBusiness.getBusinessName().equals(searchVal))
            {
            double dist;
            dist = Business.getDistance(longitude, latitude,
                    Double.parseDouble(tmpBusiness.GetLongitude()),
                    Double.parseDouble(tmpBusiness.GetLatitude()));
            if(dist < POINT_CLOSENESS_THRESHOLD)
                {
                result.put(tmpBusiness.getBusinessID(), tmpBusiness);
                }
            }
        }
    // Record was not found in BusinessCollection
    // Search DB now.
    if(result.size() == 0)
        {
        ResultSet rs = BusinessTable.prc_Get_SearchBusinessesByName
                (searchVal, latitude, longitude);
            try 
            {
                while(rs.next())
                    {
                    Business myBus = new Business();
                    int BusinessID = rs.getInt(BusinessTable.BUSINESS_ID);                    
                    myBus.SetBusinessID(BusinessID);
                    myBus.SetBusinessName(rs.getString(BusinessTable.BUSINESS_NAME));
                    myBus.SetAddress(rs.getString(BusinessTable.ADDRESS_1), rs.getString(BusinessTable.ADDRESS_2));
                    myBus.SetBusinessAffiliateID(rs.getInt(BusinessTable.BUSINESS_AFFILIATION_ID));
                    myBus.SetCity(rs.getString(BusinessTable.CITY));
                    myBus.SetLatitude(rs.getString(BusinessTable.LATITUDE));
                    myBus.SetLongitude(rs.getString(BusinessTable.LONGITUDE));
                    myBus.SetState(rs.getString(BusinessTable.STATE));
                    myBus.setZip(rs.getInt(BusinessTable.ZIP));
//                    /myBus.setBusinessType(rs.getInt(BusinessTable.BUSINESS_TYPE));
                    myBus.setBusinessType(rs.getString(BusinessTable.BUSINESS_TYPE));
                    myBus.setRating(rs.getDouble(BusinessTable.RATING));

                    result.put(BusinessID, myBus);
                    }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(BusinessCollection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    return result;
    }

public static BusinessCollection findNearestBusinesses(double latitude,
        double longitude, int userID)
    {
    // TODO: UserID is not taken care.
    BusinessCollection bc = new BusinessCollection();
    Business tmpBusiness;
    
    BusinessCollection result = new BusinessCollection();
    result.clear();
    
    Iterator it = bc.entrySet().iterator();
    while (it.hasNext())
        {
        Map.Entry pairs = (Map.Entry)it.next();
        tmpBusiness = (Business)pairs.getValue();
        double dist;
        dist = Business.getDistance(longitude, latitude,
                Double.parseDouble(tmpBusiness.GetLongitude()),
                Double.parseDouble(tmpBusiness.GetLatitude()));
        if(dist < POINT_CLOSENESS_THRESHOLD)
            {
            tmpBusiness.setDistance(dist);
            result.put(tmpBusiness.getBusinessID(), tmpBusiness);
            }
        }
    // Record was not found in BusinessCollection
    // Search DB now.
    if(result.size() == 0)
        {
        ResultSet rs = BusinessTable.prc_NearBy_Businesses(
                userID,
                longitude + "",
                latitude + "");
            try 
            {
                while(rs.next())
                    {
                    Business myBus = new Business();
                    int BusinessID = rs.getInt(BusinessTable.BUSINESS_ID);                    
                    myBus.SetBusinessID(BusinessID);
                    myBus.SetBusinessName(rs.getString(BusinessTable.BUSINESS_NAME));
                    myBus.SetAddress(rs.getString(BusinessTable.ADDRESS_1), rs.getString(BusinessTable.ADDRESS_2));
                    myBus.SetBusinessAffiliateID(rs.getInt(BusinessTable.BUSINESS_AFFILIATION_ID));
                    myBus.SetCity(rs.getString(BusinessTable.CITY));
                    myBus.SetLatitude(rs.getString(BusinessTable.LATITUDE));
                    myBus.SetLongitude(rs.getString(BusinessTable.LONGITUDE));
                    myBus.SetState(rs.getString(BusinessTable.STATE));
                    myBus.setZip(rs.getInt(BusinessTable.ZIP));
                    //myBus.setBusinessType(rs.getInt(BusinessTable.BUSINESS_TYPE));
                    myBus.setBusinessType(rs.getString(BusinessTable.BUSINESS_TYPE));
                    myBus.setRating(rs.getDouble(BusinessTable.RATING));
                    
                    double dist;
                    dist = Business.getDistance(longitude, latitude,
                        Double.parseDouble(myBus.GetLongitude()),
                        Double.parseDouble(myBus.GetLatitude()));
                    myBus.setDistance(dist);

                    result.put(BusinessID, myBus);
                    }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(BusinessCollection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    // Not sorted according to distances.
    // TODO: Sort by distance if needed here.
    
    return result;
    }

public static void addWaitTime(int businessID, int waitTime)
    {
    BusinessCollection bc = getBusinessCollection();
    Restaurant biz = (Restaurant) bc.get(businessID);
    biz.SetWaitTime(biz.GetWaitTime() + waitTime);
    }

}
