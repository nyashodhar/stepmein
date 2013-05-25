/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

/**
 *
 * @author admin
 */
import Spoton.Server.Database.*;
import Spoton.Server.Impl.Restaurant.*;
import Spoton.Server.Logger.SpotOnLogger;
import edu.hawaii.ics.csdl.simplejcs.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Map.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusinessCollection implements Map<Integer, Business>, Serializable
{

private HashMap<Integer, Business> m_BusinessCollection;

private static SpotOnLogger logger = 
            new SpotOnLogger(Customer.class.getName());

public BusinessCollection()
    {
    BCManager myBCM = new BCManager();
    SimpleJcs myCache = myBCM.getCacheObj();
    
    if(myCache.get(BCManager.BUSINESS_COLLECTION)== null)
        {
        ResultSet rs = BusinessTable.prc_Get_Businesses();
        m_BusinessCollection = new HashMap<Integer, Business>();
            try 
            {
                while(rs.next())
                    {
                    //INCOMPLETE PART -WILL DO SIMULTANEOUSLY 
                    
                    String myBusinessType = rs.getString(BusinessTable.BUSINESS_TYPE);
                    if((myBusinessType == null ? BusinessType.RESTAURANT == null : myBusinessType.equals(BusinessType.RESTAURANT)) || "1".equals(myBusinessType) || "restaurant".equals(myBusinessType.toLowerCase()))
                    {
                    Restaurant myRes = new Restaurant();
                    int BusinessID = rs.getInt(BusinessTable.BUSINESS_ID);                    
                    myRes.SetBusinessID(BusinessID);
                    myRes.SetBusinessName(rs.getString(BusinessTable.BUSINESS_NAME));
                    myRes.SetAddress(rs.getString(BusinessTable.ADDRESS_1), rs.getString(BusinessTable.ADDRESS_2));
                    myRes.SetBusinessAffiliateID(rs.getInt(BusinessTable.BUSINESS_AFFILIATION_ID));
                    myRes.SetCity(rs.getString(BusinessTable.CITY));
                    myRes.SetLatitude(rs.getString(BusinessTable.LATITUDE));
                    myRes.SetLongitude(rs.getString(BusinessTable.LONGITUDE));
                    myRes.SetState(rs.getString(BusinessTable.STATE));
                    myRes.setZip(rs.getInt(BusinessTable.ZIP));
                    
                    myRes.setBusinessType(rs.getString(BusinessTable.BUSINESS_TYPE));
                    myRes.setRating(rs.getDouble(BusinessTable.RATING));
                    
                    //myRes.setRatingCategoryString(Restaurant.RatingCategoryString);
                    myRes.setBusinessType(BusinessType.RESTAURANT);
                    //ResultSet rs_Rest = Restaurant.getRestaurantInfo(BusinessID);
                    //myRes.SetCuisine(rs_Rest.getString(Restaurant.CUISINE));
                    //myRes.SetWaitTime(rs.getInt(Restaurant.WAITTIME));
                                        //cuisine, waittime, $value, takeout, fullbar, byob
                    m_BusinessCollection.put(BusinessID, myRes);
                    //Add some more fields here which are restaurant specific
                    
                    }
                    else if(myBusinessType == BusinessType.CAFE)
                    {
                    //write fr cafe
                    }
                    else //general business
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
                    
                    myBus.setBusinessType(rs.getString(BusinessTable.BUSINESS_TYPE));
                    myBus.setRating(rs.getDouble(BusinessTable.RATING));

                    m_BusinessCollection.put(BusinessID, myBus);
                     
                    }
                    }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(BusinessCollection.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
            myCache.put(BCManager.BUSINESS_COLLECTION, m_BusinessCollection);
        }
    else
        m_BusinessCollection = (HashMap)myCache.get(BCManager.BUSINESS_COLLECTION);
    }

    
    public void refreshBusiness(int BusinessID, Business myBusiness)
    {
    this.remove(BusinessID);
    this.put(BusinessID, myBusiness);
    }

    @Override
    public int size()
    {
    if(m_BusinessCollection == null)
        return 0;
    else
        return m_BusinessCollection.size();
    
    }

    @Override
    public boolean isEmpty() 
    {
    return m_BusinessCollection.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) 
    {
        //throw new UnsupportedOperationException("Not supported yet.");
        try
        {
        return m_BusinessCollection.containsKey(Integer.parseInt(o.toString()));
        }
        catch(Exception e)
        {
        //log error here    
        return false;
        }
    }

    @Override
    public boolean containsValue(Object o) 
    {
        //throw new UnsupportedOperationException("Not supported yet.");
        try
        {
        return m_BusinessCollection.containsValue((Business)o);
        }
        catch(Exception e)
        {
            //log error here
        return false;
        }
    }

    @Override
    public Business get(Object o) 
    {
        //throw new UnsupportedOperationException("Not supported yet.");
        try
        {
        int BusinessID = Integer.parseInt(o.toString());
        Business myBiz = m_BusinessCollection.get(BusinessID);
        if(myBiz == null)
            {
            ResultSet rs = BusinessTable.prc_GetBusinessInfo(BusinessID);
            int count = 0;
            
            while(rs.next())
                {
                if(count==0)
                    {
                    myBiz.SetBusinessID(rs.getInt("BusinessID"));
                    myBiz.SetBusinessName(rs.getString("BusinessName"));
                    myBiz.SetAddress(rs.getString("Address1"), rs.getString("Address2"));
                    myBiz.SetCity(rs.getString("City"));
                    myBiz.SetState(rs.getString("State"));
                    myBiz.setZip(rs.getInt("Zip"));
                    myBiz.SetPhone(rs.getLong("Phone"));
                    myBiz.setBusinessType(rs.getString("BusinessType"));
                    
                    count++;
                    }
                }
            }
        return myBiz;
        }
        catch(Exception e)
        {
        logger.error(e.getMessage());
        
        return null;
        }
    }

    @Override
    public Business put(Integer k, Business v) 
    {
        try
        {
        m_BusinessCollection.put(k, v);
        
        return v;
        }
        catch(Exception e)
        {
        logger.error(e.getMessage());
        
        return null;
        }
    }
    

    @Override
    public Business remove(Object o) 
    {
        try
        {
        return m_BusinessCollection.remove(Integer.parseInt(o.toString()));
        }
        catch(Exception e)
        {
        logger.error("Couldn't Remove the Business from the Collection "+ e.getMessage());                
        return null;
        }
        
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Business> map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Integer> keySet() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Business> values() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Entry<Integer, Business>> entrySet() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
