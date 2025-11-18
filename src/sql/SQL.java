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

    	sqlQuery(MainProgram.dbConnection, ps);
    }

    /**
     *Create PreparedStatement to insert a new custonmer.
     * 
     * @param sql query for prepared statement
     * 
     * @param customerData array of customer data to insert
     */
    public static void ps_AddCustomer(String sql, String [] customerData){
    	try {
    		ps = MainProgram.dbConnection.prepareStatement(sql);
    		for (int i = 1; i <= customerData.length; i++) {
                if(i == 8) {
                    ps.setInt(i, Integer.parseInt(customerData[i-1]));
                    continue;
                }
                //tried the autoincrement and it generated a vlaue for customerid but it wasn't correct
    			ps.setString(i, customerData[i-1]);
    		}

            ps.executeUpdate();
            System.out.println("Customer added successfully.");
            ps.close();

    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     *Create PreparedStatement to delete a custonmer.
     * 
     * @param sql query for prepared statement
     * 
     * @param customerID ID of customer to delete
     */
    public static void ps_RemoveCustomer(String sql, int customerID){
    	try {
    		ps = MainProgram.dbConnection.prepareStatement(sql);
    		ps.setInt(1, customerID);

            ps.executeUpdate();
            System.out.println("Customer deleted successfully.");
            ps.close();

    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *Create PreparedStatement to edit a custonmer.
     * 
     * @param sql query for prepared statement
     * 
     * @param customerID ID of customer to delete
     */
    public static void ps_EditCustomer(String sql, int customerID, String [] newValues){
    	try {
    		ps = MainProgram.dbConnection.prepareStatement(sql);
    		for (int i = 1; i <= newValues.length; i++) {
                System.out.print("Setting parameter " + i + " to value " + newValues[i - 1] + "\n");
                ps.setString(i, newValues[i - 1]);
            }
            
            ps.setInt(newValues.length + 1, customerID);

            System.out.print(sql);
            ps.executeUpdate();
            System.out.println("Customer updated successfully.");
            ps.close();

    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
