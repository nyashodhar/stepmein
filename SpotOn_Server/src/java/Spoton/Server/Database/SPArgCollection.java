/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

import Spoton.Server.Database.StoredProcedureArg.ArgDirection;
import Spoton.Server.Logger.SpotOnLogger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author admin
 * 
 * Creates an object for a stored procedure with all its fields
 * so only this objects need to be passed for executing the stored procedure.
 */

//should implement a List <object>

public class SPArgCollection extends ArrayList<StoredProcedureArg>
    {

    private String m_spName;
    private String m_tableName;
    private static SpotOnLogger logger = 
            new SpotOnLogger(SPArgCollection.class.getName());
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Constructor for SPArgCollection
     ********************************************************************/    
    public SPArgCollection
    (
    String storedProcedureName, 
    String tableName
    ) 
        {
        m_spName = storedProcedureName;
        m_tableName = tableName;
        }

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Get method for storedProcedureName property.
     ********************************************************************/
    public String getStoredProcedureName() 
        {
        return m_spName;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Set method for storedProcedureName property.
     ********************************************************************/
    public void setStoredProcedureName
    (
    String storedProcedureName
    ) 
        {
        m_spName = storedProcedureName;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Get method for tableName property.
     ********************************************************************/
    public String getTableName()
        {
        return m_tableName;
        }
    
    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * Set method for tableName property.
     ********************************************************************/
    public void setTableName
    (
    String tableName
    )
        {
        m_tableName = tableName;
        }

    /*********************************************************************
     * Developed By: Kunal Jain                    Date: 01/17/2012
     * 
     * skeleton for getting the statement for execution of query.
     ********************************************************************/
    public CallableStatement getCallableStatement()
        {
        String sql;
        String logSql;
        try
            {
            sql = "{call ".concat(getStoredProcedureName()).concat("(");
            logSql = sql;
            for(int i = 0; i < this.size(); ++i)
                {
                sql = sql.concat("?");
                if(i != this.size()-1)
                    {
                    sql = sql.concat(",");
                    logSql = logSql.concat(",");
                    }
                }
            
            sql = sql.concat(")}");
            logSql = logSql.concat(")}");
            logger.info("SPArgCollection.getCallableStatement() ".concat(
                    "SQL: ").concat(logSql));
            Connection serverConn = Utils.getConnection();
            CallableStatement cs = null;
            if(serverConn != null)
                {
                cs = serverConn.prepareCall(sql);
                Iterator<StoredProcedureArg> itr = this.iterator();
                int parameterIndex = 0;
                while(itr.hasNext())
                    {
                    StoredProcedureArg element = itr.next();
                    ++parameterIndex;
                    
                    if((element.getDataType().equals(StoredProcedureArg.DataType.VARCHAR)) 
                            || (element.getDataType().equals(StoredProcedureArg.DataType.NCHAR))
                            || (element.getDataType().equals(StoredProcedureArg.DataType.CHAR))
                            || (element.getDataType().equals(StoredProcedureArg.DataType.UNIQUEIDENTIFIER))
                            )
                        {
                        if(element.getArgDirection() != null && (element.getArgDirection().equals(ArgDirection.INPUTOUTPUT) || element.getArgDirection().equals(ArgDirection.OUTPUT)))
                            {
                            cs.registerOutParameter(parameterIndex, Types.INTEGER);
                            }
                        else
                            {
                            Object s = element.getArgValue();
                            String st = s.toString();
                            cs.setString(parameterIndex, st);
                            }
                        }
                    else if(element.getDataType().equals(
                            StoredProcedureArg.DataType.INTEGER))
                        {
                        if(element.getArgDirection() != null && (element.getArgDirection().equals(ArgDirection.INPUTOUTPUT) || element.getArgDirection().equals(ArgDirection.OUTPUT)))
                            {
                            cs.registerOutParameter(parameterIndex, Types.INTEGER);
                            }
                        else
                            cs.setInt(parameterIndex, (Integer)element.getArgValue());
                        }
                    else if(element.getDataType().equals(
                            StoredProcedureArg.DataType.DATETIME))
                        {
                        // cs.setDate(parameterIndex, (Date)element.getArgValue());
                        if(element.getArgValue() != null)
                            {
                            try
                            {
                            cs.setTimestamp(parameterIndex,
                                new Timestamp((
                                (Date)element.getArgValue()).getTime()));
                            }
                            catch(Exception dateerr)
                                {
                                logger.error("Input Date is in wrong format"+ element.getArgValue() + "\\n"+dateerr);
                                }
                            }
                        else
                            {
                            cs.setTimestamp(parameterIndex, null);
                            }
                        }
                    else if(element.getDataType().equals(
                            StoredProcedureArg.DataType.BIGINT))
                        {
                        cs.setLong(parameterIndex, (Long)element.getArgValue());
                        }
                    else if(element.getDataType().equals(
                            StoredProcedureArg.DataType.FLOAT))
                        {
                        cs.setFloat(parameterIndex, (Float)element.getArgValue());
                        }
                    else
                        {
                        cs.setString(parameterIndex, (String)element.getArgValue());
                        }
                    }
                    
                return cs;
                }
            else
                {
                throw new NullPointerException("The Database Connection has not been established. Please check the DB Connection String");
                }
            
            }
        catch
        (
        Exception e
        )
            {
            logger.error("Exception:SPArgCollection.".concat(
                    "getCallableStatement while calling ").concat(
                    getStoredProcedureName()));
            logger.error(e.getMessage());
            }
        return null;
        }
    }
