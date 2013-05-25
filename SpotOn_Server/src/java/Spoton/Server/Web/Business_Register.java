/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Common.Utils;
import Spoton.Server.Impl.Business;
import Spoton.Server.Logger.SpotOnLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author kunal
 */
@WebServlet(name = "Business_Register", urlPatterns = {"/Business_Register"})
public class Business_Register extends HttpServlet {
    private SpotOnLogger logger = new SpotOnLogger(
            Business_Register.class.getName());

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
        String resp = "<registerResp>";
        try
            {
            /*Hashtable<String, String> inp =
                    XMLParser.parseUserRegister(request.getInputStream());*/
            
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    request.getInputStream()));
            
            String xmlMessage = "";
            String tmp;
            while((tmp = in.readLine()) != null)
                {
                xmlMessage += tmp;
                }
            Business inp = (Business)Utils.getObject(xmlMessage);
            
            logger.info("Checking registered Business : ".concat(
                    inp.GetEmail()));
            
            if(inp.checkRegisteredBusiness() != true)
                {
                inp.registerBusiness();
                resp = resp.concat("<status value=\"true\"/>");
                }
            else
                {
                resp = resp.concat("<status value=\"false already exist\"/>");
                }
            }
        catch(Exception ex)
            {
            logger.error(ex.getMessage());
            resp = resp.concat("<status value=\"false\"/>");
            }
        finally
            {
            resp = resp.concat("</registerResp>");
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
