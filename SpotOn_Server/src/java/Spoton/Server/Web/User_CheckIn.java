/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Common.Utils;
import Spoton.Server.Impl.CheckIn;
import Spoton.Server.Impl.UserCommon;
import Spoton.Server.Logger.SpotOnLogger;
import Spoton.Server.Responses.ErrorResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yash.Narvaneni
 */
@WebServlet(name = "User_CheckIn", urlPatterns = {"/User_CheckIn"})
public class User_CheckIn extends HttpServlet {

    
    private static SpotOnLogger logger = 
            new SpotOnLogger(User_CheckIn.class.getName());
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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String xml = "";
        try {
            /*
             * 
             */
            HashMap<String, Object> inp = new HashMap<String, Object>();
            inp.put("userID", request.getParameter("userID").toString());
            inp.put("businessID", request.getParameter("businessID").toString());
            inp.put("checkInDateTime", new Date());//request.getParameter("checkInDateTime").toString());
            inp.put("peopleCount", request.getParameter("peopleCount").toString());
            inp.put("checkInType", request.getParameter("checkInType").toString());
            String resrv_Time = request.getParameter("reservationTime").toString();
            String checkInType = inp.get("checkInType").toString();
            
            
            if((resrv_Time == null || resrv_Time.equals("")) && checkInType.toLowerCase().equals("now"))
            {
            //checkIn Type now, so checkInTime = ReservationTime
            java.sql.Date myCheckInDate = new java.sql.Date(((Date)(inp.get("checkInDateTime"))).getTime());
            xml = xml.concat(CheckIn.checkInUser(
                                Integer.parseInt(inp.get("userID").toString()), 
                                Integer.parseInt(inp.get("businessID").toString()), 
                                myCheckInDate, 
                                Integer.parseInt(inp.get("peopleCount").toString()), 
                                inp.get("checkInType").toString(), myCheckInDate));
            UserCommon.sendTextMessage(myCheckInDate, Integer.parseInt(inp.get("peopleCount").toString()), Integer.parseInt(inp.get("businessID").toString()), Integer.parseInt(inp.get("userID").toString()));
            }
            else if(checkInType.toLowerCase().equals("later") && (resrv_Time != null || resrv_Time.length() != 0 ) && !(resrv_Time.equals("")))
            {
            inp.put("reservationTime", resrv_Time);
            //CheckIn Type Later, so checkInTime != reservationTime
            xml = xml.concat(CheckIn.checkInUser(
                                Integer.parseInt(inp.get("userID").toString()), 
                                Integer.parseInt(inp.get("businessID").toString()), 
                                new java.sql.Date(((Date)(inp.get("checkInDateTime"))).getTime()), 
                                Integer.parseInt(inp.get("peopleCount").toString()), 
                                inp.get("checkInType").toString(), 
                                new java.sql.Date((new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a")).parse(inp.get("reservationTime").toString()).getTime())
                            ));
            }
            else
            {
            ErrorResponse myError = new ErrorResponse();
            myError.SetError("There is an error in the CheckIn Request");
            xml = xml.concat(Utils.getXML(myError));
            }
            
            
        } 
        catch(Exception e)
            {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace().toString());
            ErrorResponse myError = new ErrorResponse();
            myError.SetError("Error Occured. Could not Check In. Please Try Again");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(User_CheckIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(User_CheckIn.class.getName()).log(Level.SEVERE, null, ex);
        }
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
