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
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class admLogger {
    
    static private FileHandler fileTxt;
static private SimpleFormatter formatterTxt;

static private FileHandler fileHTML;
static private Formatter formatterHTML;

static public void setup() throws IOException 
    {
    // Create Logger
    
    Logger logger = Logger.getLogger("");
    logger.setLevel(Level.INFO);
    fileTxt = new FileHandler("SpotOnLogs.txt", true);
    fileHTML = new FileHandler("SpotOnLogs.html", true);

    // Create txt Formatter
    formatterTxt = new SimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    logger.addHandler(fileTxt);

    // Create HTML Formatter
    formatterHTML = new LogFormatter();
    fileHTML.setFormatter(formatterHTML);
    logger.addHandler(fileHTML);
    }
    
}
