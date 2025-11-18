package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import app.MainProgram;

/**
 * 
 * All database connectivity should be handled from within here.
 *
 */
public class SQL {
	
	private static PreparedStatement ps;

    /**
     * Queries the database and prints the results.
     * 
     * @param conn a connection object
     * @param sql a SQL statement that returns rows:
     * 		this query is written with the PrepareStatement class, typically 
     * 		used for dynamic SQL SELECT statements.
     */
    public static void sqlQuery(Connection conn, PreparedStatement sql){
        try {
        	ResultSet rs = sql.executeQuery();
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int columnCount = rsmd.getColumnCount();
        	for (int i = 1; i <= columnCount; i++) {
        		String value = rsmd.getColumnName(i);
        		System.out.print(value);
        		if (i < columnCount) System.out.print(",  ");
        	}
			System.out.print("\n");
        	while (rs.next()) {
        		for (int i = 1; i <= columnCount; i++) {
        			String columnValue = rs.getString(i);
            		System.out.print(columnValue);
            		if (i < columnCount) System.out.print(",  ");
        		}
    			System.out.print("\n");
        	}
        	rs.close();
        	ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *Create PreparedStatement to search a custonmer by customerID.
     * 
     * @param sql query for prepared statement
     * 
     * @param customerID customer ID to search by 
     */
    public static void ps_SearchCustomer(String sql, int customerID){
    	try {
    		ps = MainProgram.dbConnection.prepareStatement(sql);
    		ps.setInt(1, customerID);
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (ps == null) {
        System.out.println("DEBUG: PreparedStatement is NULL");
        }

    	System.out.println("DEBUG: SQL query was: " + sql);

    	sqlQuery(MainProgram.dbConnection, ps);
    }
}
