/*
 * 
 */
package Spoton.Server.Common;

import Spoton.Server.Logger.*;
import java.util.regex.*;

/**
 *This class is used to validate the server input Data
 * 
 * @author Yash Narvaneni, 
 * 
 */
public class Validate 
{

private static Pattern myPattern;

private static Matcher myMatcher;

private static String REGEX;

private static SpotOnLogger logger = new SpotOnLogger(Validate.class.getName());


/**
 * Returns true if the input matches the pattern of the regex
 * else returns False
 *
 * @param REGEX  a Pattern used to validate
 * @return     boolean
 * @throws Exception  
 */
public static boolean Match(String REGEX, String input)
    {
    myPattern = Pattern.compile(REGEX);
    myMatcher = myPattern.matcher(input);
    return myMatcher.matches();
    }

/**
 * Returns true if the input is a valid Alpha Numeric String
 * else returns False
 *
 * @param myObj  a input to validate
 * @return     boolean
 * @throws Exception 
 */
public static boolean isAlphaNumeric(Object myObj)
    {
    try
        {
        String input = myObj.toString();    
        REGEX = "^[a-zA-Z0-9]+$";
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS ALPHA NUMERIC: "+ e.getMessage());
        return false;
        }
            
    }

/**
 * Returns true if the input is a valid User ID
 * else returns False
 * 
 * Rules for UserID - It is a 10 digit Positive Integer
 *
 * @param myObj  a input to validate
 * @return     boolean
 * @throws Exception 
 */
public static boolean isCheckInOutID(Object myObj)
    {
    try
        {
        String input = myObj.toString();
        REGEX = "^\\d{10}$";
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS USER ID: "+ e.getMessage());
        return false;
        }
    }

/**
 * Returns true if the input is a valid User ID
 * else returns False
 * 
 * Rules for UserID - It is a 10 digit Positive Integer
 *
 * @param myObj  a input to validate
 * @return     boolean
 * @throws Exception 
 */
public static boolean isUserID(Object myObj)
    {
    try
        {
        String input = myObj.toString();
        REGEX = "^\\d{8}$";
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS USER ID: "+ e.getMessage());
        return false;
        }
    }

/**
 * Returns true if the input is a valid Alpha Numeric String
 * else returns False
 * 
 * Rules for BusinessID - It is a 10 digit Positive Integer
 *
 * @param myObj  a input to validate
 * @return     boolean
 * @throws Exception 
 */
public static boolean isBusinessID(Object myObj)
    {
    try
        {
        String input = myObj.toString();
        REGEX = "^\\d{6}$";
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS BUSINESS ID: "+ e.getMessage());
        return false;
        }
    }

/**
 * Returns true if the input is a Integer either positive or negative
 * signed or unsigned
 * else returns False
 *
 * @param myObj  a input to validate
 * @return     boolean
 * @throws Exception 
 */
public static boolean isInteger(Object myObj)
    {
    try
        {
        String input = myObj.toString();
        REGEX = "^(\\+|-)?\\d+$";
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS INTEGER: "+ e.getMessage());
        return false;
        }
    }

/**
 * Returns true if the input is a valid Email format
 * else returns False
 *
 * @param myObj  a input to validate
 * @return     boolean
 * @throws Exception 
 */
public static boolean isEmail(Object myObj)
    {
    try
        {
        String input = myObj.toString();
        REGEX = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}"
                + "\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))"
                + "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS EMAIL: "+ e.getMessage());
        return false;
        }
    }
/**
 * Returns true if the input is a valid Phone Number written in popular formats
 * else returns False
 * 
 * Matching Phone Number Formats:
 * 123-456-7800
 * (123)456-7800
 * 1234567800
 *
 * @param myObj  a input to validate
 * @return     boolean
 * @throws Exception 
 */
public static boolean isPhoneNumber(Object myObj)
    {
    
    try
        {
        String input = myObj.toString();
        REGEX = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS PHONE NUMBER: "+ e.getMessage());
        return false;
        }
    }

/**
 * Given a pre-fixed Email Ext by a business, this is used to validate 
 * all the emails of the companies Email IDs given by Providers
 *
 * @param myObj  a input to validate
 * @param EmailExt a input which is a email extension given by the company
 * @return     boolean
 * @throws Exception 
 */
public static boolean isValidBusinessEmail(Object myObj, String EmailExt)
    {
    try
        {
        String input = myObj.toString();
        REGEX = "^([a-zA-Z0-9_\\-\\.]+)@"+EmailExt;
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS VALID BUSINESS EMAIL: "+ e.getMessage());
        return false;
        }
    }

/**
 * Returns true if the input is a valid URL
 * else returns False
 *
 * @param myObj  a input to validate
 * @return     boolean
 * @throws Exception 
 */
public static boolean isValidURL(Object myObj)
    {
    try
        {
        String input = myObj.toString();
        REGEX = "((http|ftp|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@"
                + "?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS VALID URL: "+ e.getMessage());
        return false;
        }
    }

/**
 * TODO---------------------------TODO--------------------------TODO
 * Returns true if the input is a valid DATE
 * else returns False
 *
 * @param myObj  a input to validate
 * @return     boolean
 * @throws Exception 
 */
public static boolean isValidDate(Object myObj)
    {
    try
        {
        String input = myObj.toString();
        REGEX = "";
        return Match(REGEX, input);
        }
    catch(Exception e)
        {
        logger.error("VALIDATION ERROR - IS VALID DATE: "+ e.getMessage());
        return false;
        }
    }

}
