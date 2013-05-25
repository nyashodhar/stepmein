/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Common.Utils;
import Spoton.Server.Impl.Business;
import Spoton.Server.Impl.UserCommon;
import Spoton.Server.Logger.SpotOnLogger;
import Spoton.Server.Responses.ErrorResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Yash.Narvaneni
 */
@WebServlet(name = "Get_Business_List_By_User", urlPatterns = {"/Get_Business_List_By_User"})
public class Get_Business_List_By_User extends HttpServlet {

    private static SpotOnLogger logger = 
            new SpotOnLogger(Get_Business_List_By_User.class.getName());
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
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
        String xml = "";      
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            //HashMap <String, Integer> inp = new HashMap<String, Integer>();
            int userID = Integer.parseInt(request.getParameter("userID").toString());
            ArrayList<Business> BusinessListByUser = UserCommon.Get_Businesses_By_User(userID);
            
            xml = xml.concat(Utils.getXML(BusinessListByUser));
            
            }
        catch(Exception e)
            {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace().toString());
            ErrorResponse myError = new ErrorResponse();
            myError.SetError("Error Occured in getting the Data");
            xml = xml.concat(Utils.getXML(myError));
            }
        finally {
            out.println(xml);
            out.close();
            }
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
