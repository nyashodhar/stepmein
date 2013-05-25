/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Responses;

import java.util.*;

/**
 *
 * @author Yash.Narvaneni
 */
public class UserLogin_Response 
{
/*
 * SessionID - UniqueIdentifier
 */
private UUID m_sID;
/*
 * UserID - Int
 */
private int m_userID;
/*
 * Status of Login
 */
private String m_Status;

public static final String Status_SUCCESS = "true";
public static final String Status_FAILURE = "false";
public static final String Status_SESSION_INSERT_FAILED = "false, could not insert session into sessions table";
public static final String Status_UNREGISTERED = "User is not registered";


public void setsID(UUID sID)
    {
    m_sID = sID;
    }
public void setuserID(int userID)
    {
    m_userID = userID;
    }
public void setStatus(String status)
    {
    m_Status = status;
    }

/*
public UUID getsID()
    {
    return m_sID;
    }
public int getuserID()
    {
    return m_userID;
    }
public String getStatus()
    {
    return m_Status;
    }
*/


}
