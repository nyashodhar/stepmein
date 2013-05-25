/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Responses;

/**
 *
 * @author Yash.Narvaneni
 */
public class UserLogout_Response 
{
public static String Status_SUCCESS = "User Successfully Logged Out";
public static String Status_FAILURE = "false, User Logout Failed";
public static String Status_DBERROR = "false, Database Error";
public static String Status_NOACTIVESESSION = "false, No Active Session";

private String m_Status;

public void setStatus(String status)
    {
    m_Status = status;
    }    
}
