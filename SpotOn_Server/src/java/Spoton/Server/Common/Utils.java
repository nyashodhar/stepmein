/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Common;

/**
 *
 * @author admin
 */
import Spoton.Server.Impl.Business;
import Spoton.Server.Logger.SpotOnLogger;
import Spoton.Server.Web.XMLParser;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.*;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Utils 
{

private static SpotOnLogger logger = new SpotOnLogger(Utils.class.getName());
    
//xstream serialization of objects
public static String getXML(Object myObj)
    {
    XStream xstream = new XStream(new StaxDriver());
    
    xstream.alias("resp", myObj.getClass());
    return xstream.toXML(myObj);
    }

//Deserialization XML to Objects
public static Object getObject(String myXML)
    {
    XStream xstream = new XStream(new StaxDriver());
    xstream.alias("resp", Business.class);

    return xstream.fromXML(myXML);
    }

public static Map ConvertRequestToHashMap(HttpServletRequest request)
    {
    Map<String, String[]> inp = request.getParameterMap();
    
    return inp;
    }
    
public static JSONArray getLatLong(String address)
    {
    String urlString;
    urlString = "http://maps.google.com/maps/geo?output=json" +
            "&oe=utf8&sensor=false&gl=us&q=";
    JSONArray coord;
    try
        {
        URL url = new URL(urlString + address);
        // URL url = new URL("http://web.iiit.ac.in/~kunal.jainug08/geo.txt");
        logger.info(urlString + address);
        InputStream inputStream = url.openStream();
        BufferedReader buffRead = new BufferedReader(
                new InputStreamReader(inputStream));
        String line = buffRead.readLine();
        String geoJSONMessage = "";
        while (line != null)
            {
            geoJSONMessage += line;
            line = buffRead.readLine();
            }
        buffRead.close();
        logger.info("json :" + geoJSONMessage);
        
        JSONParser jsonParser = new JSONParser();
        JSONObject json = (JSONObject) jsonParser.parse(geoJSONMessage);
        JSONObject reqStatus = (JSONObject) json.get("Status");
        if(((Long)reqStatus.get("code")) == 200)
            {
            JSONArray reqPlacemark = (JSONArray) json.get("Placemark");
            JSONObject reqPoint = (JSONObject)
                    ((JSONObject)reqPlacemark.get(0)).get("Point");
            coord = (JSONArray) reqPoint.get("coordinates");
            return coord;
            }
        }
    catch(Exception ex)
        {
        logger.error(ex.getMessage());
        }
    return null;
    }

}
