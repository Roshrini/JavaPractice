package cs5530;

import java.sql.*;

/**
 * Provide a connected object to the database
 * 
 * @Assigment Phase2 
 * @modifier Fahad Alothaimeen
 * @uNid u0824563
 */
public class Connector {
	public Connection con;
	public Statement stmt;
	public Connector() throws Exception {
		try{
		 	String userName = "cs5530u61";
	   		String password = "bned4g8u";
	        	String url = "jdbc:mysql://georgia.eng.utah.edu/cs5530db61";
		        Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        		con = DriverManager.getConnection (url, userName, password);

			stmt = con.createStatement();
			//stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch(Exception e) {
			System.err.println("Unable to open mysql jdbc connection. The error is as follows,\n");
            		System.err.println(e.getMessage());
			throw(e);
		}
	}
	
	public void closeConnection() throws Exception{
		con.close();
	}
}
