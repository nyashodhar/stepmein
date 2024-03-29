/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Impl.Business;
import Spoton.Server.Logger.SpotOnLogger;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Business_Polling", urlPatterns = {"/Business_Polling"})
public class Business_Polling extends HttpServlet {

    private static SpotOnLogger logger = new SpotOnLogger(
            Business_Polling.class.getName());
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String resp = "<businesspollingResp>";
        try
            {
            Hashtable<String, String> inp =
                    XMLParser.parseBusinessPolling(request.getInputStream());
            
            if(Business.checkActiveSession(inp.get("email"),
                    UUID.fromString(inp.get("sessionID"))))
                {
                    resp = resp.concat("<status value=\"true\"/>");
                }
            else
                {
                resp = resp.concat("<status value=\"false\"/>");
                }
            resp = resp.concat("</businesspollingResp>");
            }
        catch(Exception ex)
            {
            resp = resp.concat("<status value=\"false\"/>");
            resp = resp.concat("</businesspollingResp>");
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
