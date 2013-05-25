/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

import Spoton.Server.Database.BusinessTable;
import Spoton.Server.Logger.SpotOnLogger;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Yash.Narvaneni
 */
public class BusinessCommon 
{
    private static SpotOnLogger logger = 
            new SpotOnLogger(BusinessCommon.class.getName());
    
/*
 * Gets Business info by name
 */
public static Business GetBusinessInfoById(int BusinessID)
    {
    ResultSet rs = BusinessTable.prc_GetBusinessInfo(BusinessID);
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
    
    return myBusinessList.get(0);
    }
}
