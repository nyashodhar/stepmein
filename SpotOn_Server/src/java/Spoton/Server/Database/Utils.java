/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

/**
 *
 * @author admin
 * 
 * Makes a new DB Connection and executes Stored Procedures.
 */
import Spoton.Server.Logger.SpotOnLogger;
import java.sql.*;
import java.util.*;

public class Utils
{

private static SpotOnLogger logger = 
        new SpotOnLogger(Utils.class.getName());

// TODO : sGet this information from different file.
private static String HOSTNAME = "173.0.134.63";
private static String PORTNO = "1433";
private static String DATABASENAME = "SpotOn_DB";
private static String USERNAME = "sa";
private static String PASSWORD = "Password1!";
private static Connection conn;

/*********************************************************************
* Developed By: Kunal Jain                    Date: 01/17/2012
* 
* This method returns the connection object  
********************************************************************/
    
public static Connection getConnection()
{
try
    {
    if(conn!= null && conn.isClosed() == false)
        {
        return conn;
        }
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String dbConnectString = "jdbc:sqlserver://".concat(HOSTNAME).
            //concat(":").
            //concat(PORTNO).
            concat(";databaseName=").concat(DATABASENAME);
    logger.info("DBConnectString: ".concat(dbConnectString));
    conn = DriverManager.getConnection(dbConnectString,
            USERNAME, PASSWORD);
    return conn;
    }
catch (Exception e)
    {
    logger.error(e.toString());
    }
return null;
}
    

/*********************************************************************
* Developed By: Kunal Jain                    Date: 01/17/2012
* 
* This method returns the ResultSet object connected to Stored Procedure
********************************************************************/
public static ResultSet executeStoredProcedureToResultSet
(
SPArgCollection spArgs
)
    {
    try
        {
        CallableStatement cs = spArgs.getCallableStatement();
        boolean results = cs.execute();
        ResultSet rs = null;

        if(results)
            {
            rs = cs.getResultSet();
            if(rs != null)
                return rs;
            }
        else
            {
            System.out.println("Something is wrong");
            return null;
            }
        }
    catch(Exception e)
        {
        logger.error(e.getMessage());
        }
    return null;
    }


/*********************************************************************
* Developed By: Kunal Jain                    Date: 01/17/2012
* 
* This method executes the query. 
********************************************************************/
public static boolean executeNonQueryStoredProcedure
(
SPArgCollection spArgs
)
    {
    try
        {
        CallableStatement cs = spArgs.getCallableStatement();
        boolean status = cs.execute();
        cs.close();
        return status;
        //cs.getConnection().close();
        }
    catch(Exception e)
        {
        logger.error(e.getMessage());
        return false;
        }
    }

/*********************************************************************
* Developed By: Yashodhar Narvaneni                 Date: 01/17/2013
* 
* This method executes the query. 
********************************************************************/
public static int ExecuteQueryStoredProcedureToInt
(
SPArgCollection spArgs, String OutParamName
)
    {
    try
        {
        CallableStatement cs = spArgs.getCallableStatement();
        boolean results = cs.execute();
        
        if(results)
            {
            return cs.getInt(OutParamName);
            }
        else
            {
            System.out.println("Something is wrong");
            return -1;
            }
        }
    catch(Exception e)
        {
        logger.error(e.getMessage());
        }
    return -1;
    }

    
}
