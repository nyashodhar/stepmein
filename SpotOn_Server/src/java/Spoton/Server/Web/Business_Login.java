/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Common.Utils;
import Spoton.Server.Database.BusinessSessionTable;
import Spoton.Server.Impl.Business;
import Spoton.Server.Logger.SpotOnLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "Business_Login", urlPatterns = {"/Business_Login"})
public class Business_Login extends HttpServlet {

    private SpotOnLogger logger = new SpotOnLogger(
            Business_Login.class.getName());
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String resp = "<loginResp>";
        
        try
            {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    request.getInputStream()));
            
            String xmlMessage = "";
            String tmp;
            while((tmp = in.readLine()) != null)
                {
                xmlMessage += tmp;
                }
            
            Business myBiz = (Business) Utils.getObject(xmlMessage);
            
            if(myBiz.checkRegisteredBusiness())
                {
                int businessID = myBiz.loginBusiness();
                if(businessID != Business.BUSINESS_DOES_NOT_EXIST)
                    {
                    UUID sessionID = UUID.randomUUID();
                    java.util.Date curDate = new java.util.Date();
                    logger.info(curDate.toString());
                    boolean status = BusinessSessionTable.prc_InsertBusinessSession
                            (UUID.randomUUID(), businessID, null, 
                            new Date(curDate.getTime()));
                    if(status)
                        {
                        
                        }
                    request.getSession().setAttribute("sessionID", sessionID);
                    resp = resp.concat("<sId value=\"").concat(
                            sessionID.toString()).concat("\"/>");
                    resp = resp.concat("<status value=\"true\"/>");
                    }
                else
                    {
                    resp = resp.concat("<status value=\"false\"/>");
                    }
                }
            resp = resp.concat("</loginResp>");
            }
        catch(Exception ex)
            {
            resp = resp.concat("<status value=\"false\"/>");
            resp = resp.concat("</loginResp>");
            logger.error(ex.getMessage());
            }
        finally
            {
            out.println(resp);
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
