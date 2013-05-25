/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl.Restaurant;

/**
 *
 * @author admin
 */
import Spoton.Server.Impl.Business;
import Spoton.Server.Impl.BusinessCollection;
import Spoton.Server.Impl.BusinessType;
import Spoton.Server.Logger.SpotOnLogger;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.json.simple.parser.*;

public class YelpAPIImpl 
{

public static String NO_RATING = "NA";

  OAuthService service;
  Token accessToken;
  private static SpotOnLogger logger = 
            new SpotOnLogger(YelpAPIImpl.class.getName());
  /**
   * Setup the Yelp API OAuth credentials.
   *
   * OAuth credentials are available from the developer site, under Manage API access (version 2 API).
   *
   * @param consumerKey Consumer key
   * @param consumerSecret Consumer secret
   * @param token Token
   * @param tokenSecret Token secret
   */
  public YelpAPIImpl() 
    {
    String consumerKey = "NKTrB5ZwgrwZTU6t_UNhaA";
    String consumerSecret = "ldi6D9pDXBFLCMYPr8jyH7gURH8";
    String token = "tqS41Xjv-BqplMqCQOA7bbK-J7mAw5Sp";
    String tokenSecret = "9vBwIl7WXy0hEe_Rh4kdo2bhKIo";
    
    this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
    this.accessToken = new Token(token, tokenSecret);
    }

  /**
   * Search with term and location.
   *
   * @param term Search term
   * @param latitude Latitude
   * @param longitude Longitude
   * @return JSON string response
   */
  public String search(String term, double latitude, double longitude) 
  {
    OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
    request.addQuerystringParameter("term", term);
    request.addQuerystringParameter("ll", latitude + "," + longitude);
    this.service.signRequest(this.accessToken, request);
    Response response = request.send();
    return response.getBody();
  }


/**
   * Search Restaurants by location.
   *
   * @param latitude Latitude
   * @param longitude Longitude
   * @return JSONObject response
   */  
public static JSONObject searchRestaurantsByLocation(double latitude, double longitude)
    {
    YelpAPIImpl yelp = new YelpAPIImpl();
    String resp = yelp.search("Restaurants", latitude, longitude);
    JSONParser jp = new JSONParser();
    JSONObject jo = new JSONObject();
    try
    {
    jo = (JSONObject)jp.parse(resp);
    }
    catch(Exception e)
    {
    logger.error(e.getMessage());
    }
            
    return jo;
    }
 
/*****************************************************************************
 * Yash Narvaneni
 * <info>Gets the Business Info from YELP 
 * based on Business Name and location
 * <\info>
 * 
 *****************************************************************************/
public static JSONObject getBusinessInfoByName(String businessName, double latitude, double longitude)
  {
    YelpAPIImpl yelp = new YelpAPIImpl();
    String resp = yelp.search(businessName, latitude, longitude);
    JSONParser jp = new JSONParser();
    JSONObject jo = new JSONObject();
    try
    {
    jo = (JSONObject)jp.parse(resp);
    }
    catch(Exception e)
    {
    logger.error(e.getMessage());
    }
            
    return jo;
  }


/*****************************************************************************
 * Yash Narvaneni
 * <info>Gets the rating of Business from YELP 
 * based on Business Name and location. else returns 'NA'
 * <\info>
 *****************************************************************************/
public static String getRating(String businessName, double latitude, double longitude)
    {
    String rating = NO_RATING;
    
    YelpAPIImpl yelp = new YelpAPIImpl();
    String resp = yelp.search(businessName, latitude, longitude);
    JSONParser jp = new JSONParser();
    JSONObject jo = new JSONObject();
    try
    {
    jo = (JSONObject)jp.parse(resp);
    return getRating(jo, businessName);
    }
    catch(Exception e)
    {
    logger.error(e.getMessage());
    return NO_RATING;
    }
    
    //return rating;
    }


/*****************************************************************************
 * Yash Narvaneni
 * <info>Gets the rating of Business from YELP 
 * based on Business Name and location. else returns 'NA'
 *<\info>
 *<Comment>businessName can be ""</Comment> 
 *****************************************************************************/
public static String getRating(JSONObject YelpResponse, String businessName)
    {
    try
        {
        if(YelpResponse!=null)
            {
            JSONArray ar = (JSONArray)YelpResponse.get("businesses");
            String rating = NO_RATING;
            for(int i=0; i<ar.size(); i++)
                {
                JSONObject temp = (JSONObject)ar.get(i);
                if(businessName == temp.get("name"))
                    {
                    rating = temp.get("rating").toString();
                    break;
                    }
                else
                    rating = NO_RATING;
                }
            return rating;
            }
        else
            return NO_RATING;
        }
    catch(Exception e)
        {
        logger.error(e.getMessage());
        return NO_RATING;
        }
    }


/*****************************************************************************
 * Kunal Jain
 * <info>Gets the URL display pic of Business from YELP 
 * based on Business Name and location. else returns null
 *<\info>
 *<Comment>businessName can be ""</Comment> 
 *****************************************************************************/
public static String getDisplayPic(JSONObject YelpResponse, String businessName)
    {
    try
        {
        if(YelpResponse!=null)
            {
            JSONArray ar = (JSONArray)YelpResponse.get("businesses");
            String image = "";
            for(int i=0; i<ar.size(); i++)
                {
                JSONObject temp = (JSONObject)ar.get(i);
                if(businessName.equalsIgnoreCase((String)temp.get("name")))
                    {
                    image = (String)temp.get("image_url");
                    break;
                    }
                }
            return image;
            }
        return null;
        }
    catch(Exception e)
        {
        logger.error(e.getMessage());
        return null;
        }
    }

 /*********************************************************************
     * Developed By: Kunal Jain                    Date: 03/22/2012
     * 
     * Gets details of the restaurants from Yelp
     ********************************************************************/
public static BusinessCollection getInfoFromYelp(BusinessCollection bc)
    {
    Iterator it = bc.entrySet().iterator();
    while(it.hasNext())
        {
        Map.Entry pairs = (Map.Entry) it.next();
        Business tmpBusiness = (Business) pairs.getValue();
        if(tmpBusiness.getBusinessType() == BusinessType.RESTAURANT) //_ID
            {
            JSONObject resObject;
            resObject = getBusinessInfoByName(tmpBusiness.getBusinessName(),
                    Double.parseDouble(tmpBusiness.GetLatitude()),
                    Double.parseDouble(tmpBusiness.GetLongitude()));
            
            String rating = getRating(resObject, tmpBusiness.getBusinessName());
            String pic = getDisplayPic(
                    resObject, tmpBusiness.getBusinessName());
            // Format mismatch rating.
            tmpBusiness.setRatingCategoryString(rating);
            tmpBusiness.setDisplayImage(pic);
            }
        }
    return bc;
    }


/*****************************************************************************
 * Yash Narvaneni
 * <info>
 * Gets the rating of Business from YELP
 *<\info>
 *<Comment>
 *  Just give it a YELP Resp and it will return all the restaurants and ratings
 * in a Hash Table
 * SimpleEntry.Key = name
 * SimpleEntry.value = rating
 * HashMap.Key = id
 * HashMap.Key = SimpleEntry
 * </Comment> 
 *****************************************************************************/
public static HashMap<String, SimpleEntry> getRestaurantRatings(JSONObject YelpResponse)
    {
    HashMap<String, SimpleEntry> ht = new HashMap<String, SimpleEntry>();
    try
        {
        if(YelpResponse!=null)
            {
            JSONArray ar = (JSONArray)YelpResponse.get("businesses");
            int noofBusinesses = ar.size();
            if(noofBusinesses != 0)
                {
                for(int i=0; i<ar.size(); i++)
                    {
                    JSONObject temp = (JSONObject)ar.get(i);
                    String rating = temp.get("rating").toString();
                    String id = temp.get("id").toString();
                    String name = temp.get("name").toString();

                    SimpleEntry se = new SimpleEntry(name, rating);
                    ht.put(id, se);
                    }
                return ht;
                }
            else 
                return null;
            }
        else
            return null;
        }
    catch(Exception e)
        {
        logger.error(e.getMessage());
        return null;
        }
    
    }

   
}
