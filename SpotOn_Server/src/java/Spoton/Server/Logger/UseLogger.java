/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Logger;

/**
 *
 * @author admin
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UseLogger {
    
        // Always use the classname, this way you can refactor
private final static Logger LOGGER = Logger.getLogger(UseLogger.class
                .getName());

public static void LogInfo(String message) 
    {
    LOGGER.info(message);
    }



public static void main(String[] args) 
    {
    //inTUseLogger logger = new inTUseLogger();
    try 
        {
        admLogger.setup();
        } 
    catch (IOException e) 
        {
        e.printStackTrace();
        throw new RuntimeException("Problems with creating the log files");
        }
        //logger.writeLog();
    }
    
}
