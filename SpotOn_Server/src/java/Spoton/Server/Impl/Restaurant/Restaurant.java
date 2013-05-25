/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl.Restaurant;

import Spoton.Server.Impl.Business;
import java.io.*;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class Restaurant extends Business 
{
public static String RatingCategoryString = "Ambience, Service, Taste, Quality, Food";
public static String CUISINE = "CUISINE";
public static String WAITTIME = "WAITTIME";

    public static ResultSet getRestaurantInfo(int BusinessID) 
        {
        ResultSet rs = null;
        
        return rs;
        }
private String m_Cuisine = "";
private int m_WaitTime = 0;
private boolean m_hasFullBar;
private boolean m_hasBYOB;
private String m_DollarValue = "";

public void SetCuisine(String Cuisine)
    {
    m_Cuisine = Cuisine;
    }
public String GetCuisine()
    {
    return m_Cuisine;
    }

public void SetHasFullBar(boolean HasFullBar)
    {
    m_hasFullBar = HasFullBar;
    }
public boolean GetHasFullBar()
    {
    return m_hasFullBar;
    }
public void SetHasBYOB(boolean HasBYOB)
    {
    m_hasBYOB = HasBYOB;
    }
public boolean GetHasBYOB()
    {
    return m_hasBYOB;
    }
public void SetDollarValue(int DollarValue)
    {
    String myDollarValue = "";
    if(DollarValue <= 4)
        {
        for(int i = 0; i<DollarValue; i++)
            {
            myDollarValue = myDollarValue.concat("$");
            }
        }
    m_DollarValue = myDollarValue;
    }
public String GetDollarValue()
    {
    return m_DollarValue;
    }

public void SetWaitTime(int WaitTime)
    {
    //int represents Minutes of time
    m_WaitTime = WaitTime;
    }
public int GetWaitTime()
    {
    return m_WaitTime;
    }

    
}
