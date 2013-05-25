/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Responses;

/**
 *
 * @author Yash.Narvaneni
 */
public class UserCheckout_ByProvider_UserTransfer 
{
private int m_checkinID;
private int m_businessID;
private String m_status;
public static String CHECKEDOUT_BY_BUSINESS = "You have been checked out by the Business";

public void SetcheckInId(int checkInId)
    {
    m_checkinID = checkInId;
    }

public void SetBusinessId(int businessID)
    {
    m_businessID = businessID;
    }

public void SetStatus(String status)
    {
    m_status = status;
    }

}
