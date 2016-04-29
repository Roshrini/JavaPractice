package cs5530;

import java.sql.*;

/**
 * Provides function to minuplate/interact-with the Visit database table using
 * 'con.stmt'
 * 
 * @Assigment Phase2
 * @author Fahad Alothaimeen
 * @uNid u0824563
 */
public class Visit {

	/**
	 * Empty constructor to initialize the Visit object
	 */
	public Visit() {
	}

	/**
	 * Shows all visits made by a specified user
	 * 
	 * @param login
	 *            user login
	 * @param stmt
	 *            database connection
	 * @return all user's visits in a string
	 */
	public String showVisits(String login, Statement stmt) {
		String sql = "SELECT P.pname, P.address, VE.cost, VE.visitors, V.vdate "
				+ "FROM Visit V, Users U, POI P, VisEvent VE " + "WHERE V.login=U.login AND V.login='" + login
				+ "' AND V.pid=P.pid AND V.veid=VE.veid";

		String output = "";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();

			while (rs.next()) {
				output += "[ ";
				for (int i = 1; i <= numCols; i++) {
					if (numCols != i)
						output += rs.getString(i) + " ][ ";
					else
						output += rs.getString(i) + " ]";
				}
				output += "\r\n";
			}

			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}
}
