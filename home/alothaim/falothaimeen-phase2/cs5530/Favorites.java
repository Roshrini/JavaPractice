package cs5530;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Provides function to minuplate/interact-with the Favorites database table
 * using 'con.stmt'
 * 
 * @Assigment Phase2
 * @author Fahad Alothaimeen
 * @uNid u0824563
 */
public class Favorites {

	/**
	 * Empty constructor to initialize the Favorites object
	 */
	public Favorites() {
	}

	/**
	 * Shows all favorite POIs for a specified user
	 * 
	 * @param login
	 *            user login
	 * @param stmt
	 *            database connection
	 * @return all user's favs in a string
	 */
	public String showFavs(String login, Statement stmt) {
		String sql = "SELECT P.pname, P.address, F.fvdate " + "FROM Favorites F, POI P " + "WHERE login='" + login
				+ "' AND F.pid=P.pid";
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

	/**
	 * Adds a POI to a sepcified user's favorites
	 * 
	 * @param login
	 *            user login
	 * @param target
	 *            targeted POI id
	 * @param stmt
	 *            database connection
	 */
	public void addPOI(String login, String target, Statement stmt) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date_str = sdf.format(date);
		String sql = "INSERT INTO Favorites (pid, login, fvdate) " + "VALUES ('" + target + "','" + login + "', '"
				+ date_str + "')";

		try {
			stmt.executeUpdate(sql);
			System.out.println("Favorite POI was added successfully.");
		} catch (SQLException e) {
			System.out.println("cannot execute the update.");
		}
	}
}
