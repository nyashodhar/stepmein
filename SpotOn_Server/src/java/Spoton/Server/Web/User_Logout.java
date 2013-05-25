/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Common.Utils;
import Spoton.Server.Impl.UserCommon;
import Spoton.Server.Logger.SpotOnLogger;
import Spoton.Server.Responses.UserLogout_Response;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "User_Logout", urlPatterns = {"/User_Logout"})
public class User_Logout extends HttpServlet {
    
    private static SpotOnLogger logger = 
            new SpotOnLogger(User_Logout.class.getName());

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        UserLogout_Response resp_obj = new UserLogout_Response();
        
        try
            {
            HashMap<String, String> inp = new HashMap<String, String>();  //XMLParser.parseUserLogout(request.getInputStream());
            inp.put("userID", request.getParameter("userID"));
            inp.put("sessionID", request.getParameter("sessionID"));
            
            if(UserCommon.checkActiveSession(inp.get("userID"), UUID.fromString(inp.get("sessionID"))))
                {
                boolean status = UserCommon.logoutActiveSession(inp.get("userID"),
                        UUID.fromString(inp.get("sessionID")));
                
                if(status)
                    resp_obj.setStatus(UserLogout_Response.Status_SUCCESS);
                else
                    resp_obj.setStatus(UserLogout_Response.Status_FAILURE);
                }
            else
                {
                resp_obj.setStatus(UserLogout_Response.Status_FAILURE);
                }
            request.getSession().invalidate();
            }
        catch(Exception ex)
            {
            logger.error(ex.getMessage());
            resp_obj.setStatus(UserLogout_Response.Status_FAILURE);
            }
        finally
            {
            out.println(Utils.getXML(resp_obj));
            out.close();
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
