package cs5530;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Provides function to minuplate/interact-with the Feedback database table
 * using 'con.stmt'
 * 
 * @Assigment Phase2
 * @author Fahad Alothaimeen
 * @uNid u0824563
 */
public class Feedback {

	/**
	 * Empty constructor to initialize the Feedback object
	 */
	public Feedback() {
	}

	/**
	 * Shows all feedbacks made by a specified user
	 * 
	 * @param login
	 *            user login
	 * @param stmt
	 *            database connection
	 * @return all user's feedbacks in a string
	 */
	public String showFeeds(String login, Statement stmt) {
		String sql = "SELECT P.pname, P.address, F.score, F.fbtext, F.fbdate " + "FROM Feedback F, POI P "
				+ "WHERE login='" + login + "' AND F.pid=P.pid;";
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
	 * Adds a POI's feedback made by a specified user
	 * 
	 * @param login
	 *            login
	 * @param target
	 *            targeted POI id
	 * @param score
	 *            score of feedback
	 * @param comment
	 *            any aditional comments
	 * @param stmt
	 *            database connection
	 */
	public void addFeed(String login, String target, String score, String comment, Statement stmt) {
		String sql = "SELECT login FROM Feedback WHERE pid=" + target + "";
		ResultSet rs = null;
		ArrayList<String> usernames = new ArrayList<String>();

		try {
			rs = stmt.executeQuery(sql);
			while (rs.next())
				usernames.add(rs.getString("login"));

			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query.");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset.");
			}
		}
		if (usernames.contains(login)) {
			System.out.println("Sorry, only one feedback per POI per user is allowed!");
			return;
		} else {
			if (!comment.equals(""))
				comment = "'" + comment + "'";
			else
				comment = "null";

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date_str = sdf.format(date);
			String sql2 = "INSERT INTO Feedback (score, fbtext, pid, login, fbdate) " + "VALUES ('" + score + "',"
					+ comment + ", '" + target + "', '" + login + "', '" + date_str + "')";

			try {
				stmt.executeUpdate(sql2);
				System.out.println("Feedback was recorded successfully.");
			} catch (SQLException e) {
				System.out.println("cannot execute the update.");
			}
		}
	}

	/**
	 * Prints all existing feedbacks in the database
	 * 
	 * @param stmt
	 *            database connection
	 */
	public void printFeeds(Statement stmt) {
		String sql = "SELECT F.fid, F.score, F.fbtext, F.login, P.pname, P.address " + "FROM Feedback F, POI P "
				+ "WHERE F.pid=P.pid;";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			String output = "";

			while (rs.next()) { // for every row returned...
				output += "[ ";
				for (int i = 1; i <= numCols; i++) {
					if (numCols != i)
						output += rs.getString(i) + " ][ ";
					else
						output += rs.getString(i) + " ]";
				}
				output += "\r\n";
			}
			System.out.println(output);

			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query.");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset.");
			}
		}
	}

	/**
	 * Asseses a targeted feedback by a specified user
	 * 
	 * @param login
	 *            user login
	 * @param target
	 *            targeted feedback id
	 * @param rating
	 *            rating of feedback
	 * @param stmt
	 *            database connection
	 */
	public void assessFeed(String login, String target, String rating, Statement stmt) {
		String sql = "SELECT login FROM Feedback WHERE fid=" + target + "";
		ResultSet rs = null;
		String username = "";
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			username = rs.getString("login");

			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query.");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset.");
			}
		}
		if (login.equals(username)) {
			System.out.println("Sorry, you cannot assess your own feedback(s)!");
			return;
		} else {
			String sql2 = "INSERT INTO Rates (login, fid, rating) " + "VALUES ('" + login + "', '" + target + "', '"
					+ rating + "')";

			try {
				stmt.executeUpdate(sql2);
				System.out.println("Assessment was recorded successfully.");
			} catch (SQLException e) {
				System.out.println("cannot execute the update.");
			}
		}
	}

	/**
	 * Prints all feedbacks of a targeted POI
	 * 
	 * @param target
	 *            targeted POI id
	 * @param limit
	 *            maximum records to be provided
	 * @param stmt
	 *            database connection
	 */
	public void search(String target, String limit, Statement stmt) {
		String sql = "SELECT P.pname, P.address, F.score, F.fbtext, F.fbdate, U.login, ROUND(AVG(R.rating), 2)"
				+ " as Usefulness "
				+ "FROM Feedback F LEFT OUTER JOIN Rates R ON F.fid=R.fid, POI P, Users U "
				+ "WHERE F.pid=P.pid AND F.pid='" + target + "' AND F.login=U.login "
				+ "GROUP BY F.fid ORDER BY Usefulness DESC LIMIT " + limit;

		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			String output = "";

			while (rs.next()) { // for every row returned...
				output += "[ ";
				for (int i = 1; i <= numCols; i++) {
					if (numCols != i)
						output += rs.getString(i) + " ][ ";
					else
						output += rs.getString(i) + " ]";
				}
				output += "\r\n";
			}
			System.out.println(output);

			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query.");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset.");
			}
		}
	}
}
