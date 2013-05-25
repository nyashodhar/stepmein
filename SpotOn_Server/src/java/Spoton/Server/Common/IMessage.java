/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Common;

/**
 *
 * @author admin
 */
public interface IMessage 
{
public final static int TYPE_USER_TO_BUSINESS = 101;

public final static int TYPE_PROVIDER_TO_BUSINESS = 102;

public final static int TYPE_EMPLOYER_TO_PROVIDER = 105;

public static String KEY_FROM = "FromID";
public static String KEY_TO = "ToID";
public static String KEY_TYPE = "MessageType";

public void SetFromID(String fromID);
public String GetFromID();

public void SetTOID(String ToID);
public String GetTOID();

public void SetMessageType(String MessageType);
public String GetMessageType();

}
