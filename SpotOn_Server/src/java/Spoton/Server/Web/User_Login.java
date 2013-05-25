/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Common.Utils;
import Spoton.Server.Database.UserSessionTable;
import Spoton.Server.Impl.UserCommon;
import Spoton.Server.Logger.SpotOnLogger;
import Spoton.Server.Responses.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kunal
 */
@WebServlet(name = "User_Login", urlPatterns = {"/User_Login"})
public class User_Login extends HttpServlet {

    private static SpotOnLogger logger = 
            new SpotOnLogger(User_Login.class.getName());
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
        {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        UserLogin_Response resp_obj = new UserLogin_Response();
        
        try
            {
            //Hashtable<String, String> inp = XMLParser.parseUserLogin(request.getInputStream());
            HashMap<String, String> inp = new HashMap<String, String>();
            inp.put("uName", request.getParameter("uName"));
            inp.put("password", request.getParameter("password"));
            inp.put("appName", request.getParameter("appName"));
            inp.put("OS", request.getParameter("OS"));
            
            String deviceID = request.getParameter("deviceID");
            if(deviceID == null)
                {
                deviceID = "";
                }
            
            inp.put("deviceID", deviceID);
            
            String ipAddress = request.getHeader("Remote_Addr");
            if(ipAddress == null || ipAddress == "")
                {
                ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");

                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                }
            inp.put("ipAddress", ipAddress);
                /*  @AppName nvarchar(50),
                @OS nvarchar(50),
                @DeviceID nvarchar(50)
                **/
            //inp.put("");
            
            
            Boolean isRegistered = UserCommon.checkRegisteredUser(inp.get("uName"));
            if(isRegistered)
                {
                int userID = UserCommon.loginUser(inp.get("uName"),
                        inp.get("password"));
                if(userID != UserCommon.USER_DOES_NOT_EXIST)
                    {
                    UUID  sessionID = UUID.randomUUID();
                    java.util.Date curDate = new java.util.Date();
                    logger.info(curDate.toString());
                    //TODO: Change "" with IP Address fetched from Headers
                    boolean status = UserSessionTable.prc_InsertUserSession(sessionID, userID, "", new Date(curDate.getTime()), inp.get("appName"), inp.get("OS"), inp.get("deviceID"));
                    
                    if(status) //if session created
                        {
                        request.getSession().setAttribute("sessionID", sessionID);
                        resp_obj.setStatus(UserLogin_Response.Status_SUCCESS);
                        resp_obj.setsID(sessionID);
                        resp_obj.setuserID(userID);
                        }
                    else
                        {
                        resp_obj.setStatus(UserLogin_Response.Status_SESSION_INSERT_FAILED);
                        }
                    }
                else
                    {
                    resp_obj.setStatus(UserLogin_Response.Status_FAILURE);
                    }
                }
            else
                {
                resp_obj.setStatus(UserLogin_Response.Status_UNREGISTERED);
                }
            }
        catch(Exception ex)
            {
            resp_obj.setStatus(UserLogin_Response.Status_FAILURE);
            logger.error(ex.getMessage());
            logger.error(ex.getStackTrace().toString());
            }
        finally
            {
            out.println(Utils.getXML(resp_obj));
            out.close();
            }
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the.concat(sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
