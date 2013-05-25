/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Impl.BCManager;
import Spoton.Server.Impl.BusinessCollection;
import Spoton.Server.Impl.UserCommon;
import Spoton.Server.Logger.SpotOnLogger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yash.Narvaneni
 */
@WebServlet(name = "User_Review", urlPatterns = {"/User_Review"})
public class User_Review extends HttpServlet {

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
    private static SpotOnLogger logger = 
            new SpotOnLogger(User_Review.class.getName());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
        {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String respXml = "";
        try 
            {
            //insert review -> userID, sessionID, BusinessID, Notes, Review_String
            int userID =  Integer.parseInt(request.getParameter("userID"));
            String sessionID = request.getParameter("sessionID");
            int businessID = Integer.parseInt(request.getParameter("businessID"));
            String notes = request.getParameter("notes");
            String reviewString = request.getParameter("reviewString"); // example 3:4:5:3:4
            
            if(UserCommon.checkActiveSession(Integer.toString(userID), UUID.fromString(sessionID.toString())))
                {
                boolean state = UserCommon.Insert_UserReview(userID, businessID, sessionID, notes, reviewString);
                if(state)
                    {
                    respXml = respXml.concat("<respXml>Success</respXml>");
                    }
                else
                    respXml = respXml.concat("<respXml>Failure</respXml>");
                }           
            else
                respXml = respXml.concat("<respXml>Failure</respXml>");    
            }
        catch(Exception e)
            {
            logger.error("Error, Review Table:" + e.getMessage());
            logger.error("Error, Review Table:" + e.getStackTrace().toString());
            respXml = respXml.concat("<respXml>Exception Failure"+e.getMessage()+"</respXml>");
            }
        finally 
            {
            out.println(respXml);
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
