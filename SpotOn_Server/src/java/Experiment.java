/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Spoton.Server.Database.BusinessTable;
import Spoton.Server.Database.UserTable;
import Spoton.Server.Database.Utils;
import Spoton.Server.Impl.Business;
import Spoton.Server.Impl.Customer;
import Spoton.Server.Impl.Restaurant.Restaurant;
import Spoton.Server.Impl.Restaurant.YelpAPIImpl;
import Spoton.Server.Logger.SpotOnLogger;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;



/**
 *
 * @author kunal
 * 
 * This is just an experiment class, to test the procedures created
 * and make sure they are working fine.
 */


public class Experiment {
    private static CallableStatement cs;
    
//static SpotOnLogger logger = new SpotOnLogger(Experiment.class.getName());
    
static void postXML(String service, List<NameValuePair> params) {
    HttpURLConnection conn;
    try {
        URI uri = new URI("http",null,"localhost",
        8084,"/SpotOn_Server/" + service,null,null);
        URL url = uri.toURL();

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url.toString());
        
        
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        
        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(instream));
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    System.out.println(inputLine);
                in.close();
            } finally {
                instream.close();
            }
        }
    }catch(Exception ex) {
        //logger.error(ex.getMessage());
        java.lang.System.out.println(ex);
    }
    finally {
    }
}
    public static void getXML(String path) throws URISyntaxException, IOException
        {
        URIBuilder builder = new URIBuilder();
        
        builder.setScheme("http").setHost("localhost:8084").setPath("/SpotOn_Server/User_Register")
            .setParameter("uName", "shahid190")
            .setParameter("email", "shahid@shahid.com")
            .setParameter("password", "ali786")
            .setParameter("type", "customer");
        
        URI uri = builder.build();
        
        HttpGet httpget = new HttpGet(uri);
        HttpClient httpclient = new DefaultHttpClient();
        
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        
        if (entity != null) 
            {
            InputStream instream = entity.getContent();
            try 
                {
                BufferedReader in = new BufferedReader(new InputStreamReader(instream));
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    System.out.println(inputLine);
                in.close();
                } 
            finally 
                {
                instream.close();
                }
            }
        System.out.println(httpget.getURI());
        }
    
    
    public static void main(String args[]) throws IOException, URISyntaxException, SQLException {
       
        
        Business myBiz = new Restaurant();
        myBiz.SetBusinessID(123456678);
        myBiz.SetBusinessName("My Business");
        Restaurant myRes = (Restaurant)myBiz;
        myRes.SetCuisine("Italian");
        Statement stmt = null;
        
        CallableStatement cstmt = Utils.getConnection().prepareCall("{call prc_Get_Businesses_By_User(?)}");
        cstmt.setInt("UserID", 69288167);
        boolean results = cstmt.execute();
        ResultSet rs = null;
        
                    //BusinessTable.prc_Get_Businesses_By_User(69288167);
        
        ArrayList<Business> myBusinessList = new ArrayList<Business>();
        //Customer myUser = new Customer();
        if(results)
        {
        rs = cstmt.getResultSet();
        }
        else
        {
        System.out.println("Something is wrong");
        }
        while(rs.next())
        {
        /*
        myUser.SetCustomerName(rs.getString("Name"));
        myUser.setCustomerEmail(rs.getString("Email"));
        myUser.SetCustomerPhone(rs.getString("PhoneNumber"));
        */

        Business myBus = new Business();
        // myBus.SetBusinessID(rs.getInt("BusinessID"));
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

        System.out.println(Spoton.Server.Common.Utils.getXML(myBusinessList));
            
    }
}
