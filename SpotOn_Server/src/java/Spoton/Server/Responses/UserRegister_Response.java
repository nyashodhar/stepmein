/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Responses;

/**
 *
 * @author Yash.Narvaneni
 */
public class UserRegister_Response 
{
public static String Status_SUCCESS = "User Successfully Registered";
public static String Status_FAILURE = "false, User already exists";
public static String Status_DBERROR = "false, Database Error";

private String m_Status;

public void setStatus(String status)
    {
    m_Status = status;
    }
}
