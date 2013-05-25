/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Responses;

/**
 *
 * @author Yash.Narvaneni
 */
public class UserCheckout_ByProvider_BusinessResponse 
{
private String m_Status;
private static String UserCheckout_SUCCESS = "SUCCESS";
private static String UserCheckout_FAILURE = "FAILURE";

public void SetStatus(boolean status)
    {
    if(status)
        m_Status = UserCheckout_SUCCESS;
    else
        m_Status = UserCheckout_FAILURE;
    }    
}
