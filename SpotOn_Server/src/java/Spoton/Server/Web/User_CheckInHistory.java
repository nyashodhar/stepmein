/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Common.Utils;
import Spoton.Server.Impl.UserCheckInData;
import Spoton.Server.Impl.UserCommon;
import Spoton.Server.Logger.SpotOnLogger;
import Spoton.Server.Responses.ErrorResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "User_CheckInHistory", urlPatterns = {"/User_CheckInHistory"})
public class User_CheckInHistory extends HttpServlet {

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
            new SpotOnLogger(User_CheckInHistory.class.getName());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String respXml = "";
        try 
            {
            HashMap<String, String> inp = new HashMap<String, String>();  //XMLParser.parseUserLogout(request.getInputStream());
            inp.put("userID", request.getParameter("userID"));
            inp.put("sessionID", request.getParameter("sessionID"));

            int userID = Integer.parseInt(inp.get("userID"));
            //if user session is active, then send the checkIn History
            if(UserCommon.checkActiveSession(inp.get("userID"), UUID.fromString(inp.get("sessionID"))))
                {
                ArrayList<UserCheckInData> checkInHistory = UserCommon.Get_User_CheckIn_History(userID);

                respXml = respXml.concat(Utils.getXML(checkInHistory));
                }
            }
        catch(Exception e)
            {
            logger.error("ERROR: User_CheckInHistory:"+e.getMessage());
            logger.error(e.getStackTrace().toString());
            ErrorResponse myError = new ErrorResponse();
            myError.SetError("Error Occured in getting the CheckIn History");
            respXml = respXml.concat(Utils.getXML(myError));
            }
        finally {  
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
