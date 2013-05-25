/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Logger;

import org.apache.log4j.Logger;

/**
 *
 * @author kunal
 */
public class SpotOnLogger
    {
    
    // private static Logger log = Logger.getLogger(SpotOnLogger.class.getName());
    private Logger log;
    
    public SpotOnLogger(String clazzName)
        {
        log = Logger.getLogger(clazzName);
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/26/2012
     * 
     * Wrapper for INFO Logger
     ********************************************************************/
    public void info
    (
    String message
    )
        {
        log.info(message);
        }
    
    
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/26/2012
     * 
     * Wrapper for DEBUG Logger
     ********************************************************************/
    public void debug
    (
    String message
    )
        {
        log.debug(message);
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/26/2012
     * 
     * Wrapper for ERROR Logger
     ********************************************************************/
    public void error
    (
    String message
    )
        {
        log.error(message);
        }
    
}