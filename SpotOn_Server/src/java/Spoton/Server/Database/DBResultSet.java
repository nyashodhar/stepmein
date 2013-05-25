/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yash.Narvaneni
 */
public abstract class DBResultSet implements ResultSet
{
private ArrayList<HashMap> m_RowList = new ArrayList<HashMap>();

public ArrayList<HashMap> ToArrayList()
    {
        try {
            while(this.next())
                {
                HashMap<String, Object> rowData = new HashMap<String, Object>();
                ResultSetMetaData rsMetaData = this.getMetaData();
                int col_count = rsMetaData.getColumnCount();
                for(int i= 1; i<=col_count; i++)
                    {
                    String col_Name = rsMetaData.getColumnName(i);
                    switch (rsMetaData.getColumnType(i))
                        {
                        case Types.VARCHAR:
                            rowData.put(col_Name, this.getString(i));
                        case Types.INTEGER:
                            rowData.put(col_Name, this.getInt(i));
                        case Types.BIGINT:
                            rowData.put(col_Name, this.getInt(i));
                        case Types.BOOLEAN:
                            rowData.put(col_Name, this.getBoolean(i));
                        case Types.DATE:
                            rowData.put(col_Name, this.getDate(i));
                        case Types.DOUBLE:
                            rowData.put(col_Name, this.getDouble(i));
                        case Types.FLOAT:
                            rowData.put(col_Name, this.getFloat(i));
                        case Types.TIMESTAMP:
                            rowData.put(col_Name, this.getInt(i));
                        }
                    }
                m_RowList.add(rowData);
                }
            return m_RowList;
            } 
        catch (SQLException ex) {
            Logger.getLogger(DBResultSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
}
