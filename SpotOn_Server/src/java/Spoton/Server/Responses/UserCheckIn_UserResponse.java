/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Responses;

import Spoton.Server.Impl.BCManager;
import Spoton.Server.Impl.Business;
import Spoton.Server.Impl.BusinessCollection;
import Spoton.Server.Impl.BusinessType;
import Spoton.Server.Impl.Restaurant.Restaurant;

/**
 *
 * @author Yash.Narvaneni
 */
public class UserCheckIn_UserResponse 
{
private int m_BusinessID;
private int m_waitTime;
private String m_CheckInStatus;
private String m_CheckInType;
private String m_BusinessType;
private int m_CheckInID;

public UserCheckIn_UserResponse(String checkINType, int businessID, String CheckIn_Status, int checkInID)
    {
    String respWaitTime = "";
    
    Business biz = null;  
    BusinessCollection bc = BCManager.getBusinessCollection();
    if(bc != null)
        biz = bc.get(businessID);
    else
        biz = new Business(businessID);
    
    this.m_BusinessID = businessID;
    this.m_CheckInID = checkInID;
    this.m_CheckInStatus = CheckIn_Status;
    this.m_CheckInType = checkINType;
    this.m_BusinessType = biz.getBusinessType().toLowerCase();
    if(checkINType.toLowerCase().equals("now"))
        {
        Restaurant my_rest = null;
        if((m_BusinessType == null ? BusinessType.RESTAURANT == null : m_BusinessType.equals(BusinessType.RESTAURANT)) || "1".equals(m_BusinessType) || "restaurant".equals(m_BusinessType))
            {
            my_rest = (Restaurant) biz;
            this.m_waitTime = my_rest.GetWaitTime();
            }
        }
    }

}
