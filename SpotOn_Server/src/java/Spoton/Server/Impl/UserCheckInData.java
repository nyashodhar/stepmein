/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

import java.sql.Date;

/**
 *
 * @author Yash.Narvaneni
 */
public class UserCheckInData 
{
int m_BusinessID;
String m_BusinessName;
String m_BusinessType;
Date m_checkInDate;
Date m_checkOutDate;

String m_BusinessIcon_Url;

public void SetBusinessID(int BusinessID)
    {
    m_BusinessID = BusinessID;
    }

public void SetBusinessName(String BusinessName)
    {
    m_BusinessName = BusinessName;
    }

public void SetBusinessType(String BusinessType)
    {
    m_BusinessType = BusinessType;
    }
public void SetCheckInDate(Date CheckInDate)
    {
    m_checkInDate = CheckInDate;
    }
public void SetCheckOutDate(Date CheckOutDate)
    {
    m_checkOutDate = CheckOutDate;
    }
public void SetBusinessIconUrl(String iconUrl)
    {
    m_BusinessIcon_Url = iconUrl;
    }

    
}
