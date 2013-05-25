/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Impl;

import Spoton.Server.Common.Utils;
import Spoton.Server.Database.BigTable_MainTable;
import Spoton.Server.Database.Check_IN_OUTTable;
import Spoton.Server.Logger.SpotOnLogger;
import Spoton.Server.Responses.*;
import java.sql.*;
import java.util.*;
//import sun.util.logging.resources.logging;

/**
 *
 * @author admin
 */
public class CheckoutHandler 
    {

    private static SpotOnLogger logger = new SpotOnLogger(
            CheckoutHandler.class.getName());
    
    /*********************************************************************
    * Developed By: Kunal Jain                    
    * 
    * Check-out the user from its check-in in the BusinessID.
********************************************************************/
    public static String checkOut
    (
    int userID,
    int businessID,
    int checkInOutID,
    int providerID,
    int checkOutFlag
    )
        {
        boolean status = Check_IN_OUTTable.prc_CheckOutUser(userID, businessID, checkInOutID, providerID, checkOutFlag);
        
        if(status) // if checked out successfully
            {
            if(providerID == -1) // if checked out by user, send status to user and UserCheckOut_BusinessTransfer and put it in business table
                {
                UserCheckout_UserResponse userResp = new UserCheckout_UserResponse();
                userResp.SetStatus(true);
                
                UserCheckout_BusinessTransfer bizResp = new UserCheckout_BusinessTransfer();
                String xml_bizResp = Utils.getXML(bizResp);
                java.util.Date curr_utilDate = new java.util.Date();
                java.sql.Date currDate = new java.sql.Date(curr_utilDate.getTime());
                BigTable_MainTable.prc_InsertBigTable_Main(businessID, userID, xml_bizResp, currDate, null);
                
                return Utils.getXML(userResp);
                }
            else if(providerID > 0) //if checked out by business, send status to business and UserCheckOut_ByProvider_UserTransfer and put it in business table
                {
                UserCheckout_ByProvider_BusinessResponse providerResp = new UserCheckout_ByProvider_BusinessResponse();
                providerResp.SetStatus(true);

                UserCheckout_ByProvider_UserTransfer userResp = new UserCheckout_ByProvider_UserTransfer();
                userResp.SetBusinessId(businessID);
                userResp.SetcheckInId(checkInOutID);
                userResp.SetStatus(UserCheckout_ByProvider_UserTransfer.CHECKEDOUT_BY_BUSINESS);

                java.util.Date curDateTime = new java.util.Date();
                BigTable_MainTable.prc_InsertBigTable_Main(userID,businessID, Utils.getXML(userResp), (java.sql.Date)curDateTime, null);

                return Utils.getXML(providerResp);
                }
            }
        else
            {
            if(providerID == -1)
                {
                UserCheckout_UserResponse userResp = new UserCheckout_UserResponse();
                userResp.SetStatus(false);
                
                return Utils.getXML(userResp);
                }
            else
                {
                UserCheckout_ByProvider_BusinessResponse providerResp = new UserCheckout_ByProvider_BusinessResponse();
                providerResp.SetStatus(false);
                }
            
            }
        return null;
        }
    }
