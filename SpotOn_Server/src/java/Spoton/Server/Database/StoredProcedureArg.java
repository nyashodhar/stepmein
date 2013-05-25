/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

/**
 *
 * @author admin
 */


public class StoredProcedureArg
    {

    public enum ArgDirection
        {
        
        INPUT,
        
        OUTPUT,
        
        INPUTOUTPUT,
        
        RETURN_VALUE
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * An Enumerator for Datatype so that its easy later if we want to
     * write a validator for them.
     ********************************************************************/ 
    public enum DataType
        {
        INTEGER,
        VARCHAR,
        DATETIME,
        NCHAR,
        CHAR,
        GEOGRAPHY,
        BIGINT,
        XML,
        UNIQUEIDENTIFIER,
        FLOAT
        }


    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Properties of this class.
     ********************************************************************/
    private String m_spArgName;
    private DataType m_argDataType;
    private int m_argDataTypeLength;
    private String m_argColDisplayName;
    private Object m_argValue;
    private ArgDirection m_argDirection;
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Get method for argValue property.
     ********************************************************************/
    public Object getArgValue()
        {
        return m_argValue;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Set method for argValue property.
     ********************************************************************/
    public void setArgValue
    (
    Object argValue
    )
        {
        m_argValue = argValue;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/02/2012
     * 
     * Get method for dataType property.
     ********************************************************************/
    public Object getDataType()
        {
        return m_argDataType;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 02/04/2012
     * 
     * Get method for dataType property.
     ********************************************************************/
    public Object getArgName()
        {
        return m_spArgName;
        }
    
    public ArgDirection getArgDirection()
        {
        return m_argDirection;
        }

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Constructor for StoredProcedureArg
     ********************************************************************/
    public StoredProcedureArg
    (
    String argName,
    Object argValue,
    ArgDirection argDirection
    )
        {
        m_spArgName = argName;
        m_argValue = argValue;
        m_argDirection = argDirection;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Constructor for StoredProcedureArg
     ********************************************************************/
    public StoredProcedureArg
    (
    String argName,
    DataType dataType,
    Object argValue
    )
        {
        m_spArgName = argName;
        m_argDataType = dataType;
        m_argValue = argValue;
        m_argDirection = ArgDirection.INPUT;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Constructor for StoredProcedureArg
     ********************************************************************/
    public StoredProcedureArg
    (
    String argName, 
    DataType dataType,
    Object argValue, 
    ArgDirection argDirection
    ) 
        {
        m_spArgName = argName;
        m_argDataType = dataType;
        m_argValue = argValue;
        m_argDirection = argDirection;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Constructor for StoredProcedureArg
     ********************************************************************/
    public StoredProcedureArg
    (
    String argName, 
    Object argValue
    )
        {
        m_spArgName = argName;
        m_argValue = argValue;
        }
    }
