/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Web;

import Spoton.Server.Logger.*;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author kunal
 */
public class XMLParser
    {
    
    
    private static DocumentBuilderFactory dbf = 
            DocumentBuilderFactory.newInstance();
    private static SpotOnLogger logger = 
            new SpotOnLogger(XMLParser.class.getName());
    
    /*********************************************************************
     * Developed By: Kunal Jain                    
     * 
     * Gets all the XML Data in the Hashtable
     ********************************************************************/
    private static Hashtable<String,String> getAllXMLData(Document dom)
        {
            Hashtable<String, String> ret;
            try
                {
                ret = new Hashtable<String, String>();
                Element docEle = dom.getDocumentElement();
                NodeList nl = docEle.getChildNodes();
                if(nl!=null)
                    {
                    for(int i = 0; i < nl.getLength(); ++i)
                        {

                        Element el = (Element)nl.item(i);
                        logger.info(el.getNodeName().concat(" ").concat(
                                el.getAttribute("value")));
                        ret.put(el.getNodeName(),
                                el.getAttribute("value"));
                        }
                    }
                }
            catch(Exception e)
                {
                ret = null;
                logger.error(e.getMessage());
                }
            return ret;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/10/2012
     * 
     * Given the Schema Path and the DOM element, it validates the XML in the 
     * DOM element. 
     ********************************************************************/
    private static boolean validateSchema
    (
    String schemaPath,
    Document dom
    )
        {
        try
            {
            SchemaFactory factory = SchemaFactory.newInstance(
                            XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File(
                            schemaPath));
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            
            validator.validate(new DOMSource(dom));
            logger.info("Validated xmlMessage by ".concat(schemaPath));
            return true;
            }
        catch(Exception ex)
            {
            logger.error(ex.getMessage());
            }
        return false;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/06/2012
     * 
     * Parsing XML Data for User Register.
     ********************************************************************/
    public static Hashtable<String,String> parseUserRegister
    (
    InputStream xmlInputStream
    )
        {
            return validateAndParse("UserRegister.xsd", xmlInputStream);
        }    
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/10/2012
     * 
     * Parsing XML Data for User Login.
     ********************************************************************/
    public static Hashtable<String,String> parseUserLogin
    (
    InputStream xmlInputStream
    )
        {
            return validateAndParse("UserLogin.xsd", xmlInputStream);
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/11/2012
     * 
     * Parsing XML Data for User Polling.
     ********************************************************************/
    public static Hashtable<String,String> parseUserPolling
    (
    InputStream xmlInputStream
    )
        {
            return validateAndParse("UserPolling.xsd", xmlInputStream);
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/27/2012
     * 
     * Parsing XML Data for User Logout.
     ********************************************************************/
    public static Hashtable<String,String> parseUserLogout
    (
    InputStream xmlInputStream
    )
        {
            return validateAndParse("UserLogout.xsd", xmlInputStream);
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 03/07/2012
     * 
     * Parsing XML Data for BusinessPolling.
     ********************************************************************/
    public static Hashtable<String,String> parseBusinessPolling
    (
    InputStream xmlInputStream
    )
        {
            return validateAndParse("BusinessPolling.xsd", xmlInputStream);
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/27/2012
     * 
     * Parsing XML Data for User Logout.
     ********************************************************************/
    public static Hashtable<String,String> parseBusinessLogout
    (
    InputStream xmlInputStream
    )
        {
            return validateAndParse("BusinessLogout.xsd", xmlInputStream);
        }
    
    public static Hashtable<String,String> validateAndParse
    (
    String xsdName,
    InputStream xmlInputStream
    )
        {
            Hashtable<String, String> ret = null;
            try
                {
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document dom = db.parse(xmlInputStream);
                String schemaPath = XMLParser.class.getResource(
                        "../ValidationSchemas/".concat(xsdName)).getPath();
                if(validateSchema(schemaPath, dom))
                    {
                    ret = getAllXMLData(dom);
                    }
                }
            catch(Exception e)
                {
                ret = null;
                logger.error(e.getMessage());
                }
            return ret;
        }
    
    public static Hashtable<String,String> parse
    (
    InputStream xmlInputStream
    )
        {
            Hashtable<String, String> ret = null;
            try
                {
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document dom = db.parse(xmlInputStream);
                ret = getAllXMLData(dom);
                }
            catch(Exception e)
                {
                ret = null;
                logger.error(e.getMessage());
                }
            return ret;
        }
    
    }
