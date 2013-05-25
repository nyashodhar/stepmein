/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

/**
 *
 * @author admin
 */
import Spoton.Server.Common.*;
import Spoton.Server.Database.Check_IN_OUTTable;
import java.sql.Date;
import java.sql.ResultSet;

public class Checkout implements IMessage
{

    @Override
    public void SetFromID(String fromID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String GetFromID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void SetTOID(String ToID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String GetTOID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void SetMessageType(String MessageType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String GetMessageType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static String checkOutUser(int UserID, int BusinessID, int checkINOUTID, int providerID)
        {
        int rs = Check_IN_OUTTable.prc_InsertCheckIN(
                                                        UserID, 
                                                        null, 
                                                        BusinessID, 
                                                        null, 
                                                        new java.sql.Date((new java.util.Date()).getTime()), 
                                                        providerID, 
                                                        0, 
                                                        null, 
                                                        null
                                                            );
        
        if(rs != -1)
            {
            return "<checkOutResp value=\"success\" ></checkOutResp>";
            }
        else
            {
            return "<checkOutResp value=\"failed\" ></checkOutResp>";
            }
        //return "";
        }
    
}
