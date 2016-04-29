package cs5530;

import java.sql.*;

/**
 * Provides function to minuplate/interact-with the Users database table using
 * 'con.stmt'
 * 
 * @Assigment Phase2
 * @author Fahad Alothaimeen
 * @uNid u0824563
 */
public class User {
	private String login;
	private String password;
	private boolean isAdmin = false;

	/**
	 * Constructor that store user: (username, password) PER connected session
	 * AND initialize the Users object
	 * 
	 * @param tlogin
	 * @param tpassword
	 */
	public User(String tlogin, String tpassword) {
		login = tlogin;
		password = tpassword;
	}

	/**
	 * Verifies a user existince in the database
	 * 
	 * @param stmt
	 *            database connection
	 * @return true, if login process succeeded (existing user OR new user)
	 */
	public boolean verifyUser(Statement stmt) {
		String sql = "select * from Users where login='" + login + "' AND upassword='" + password + "'";
		String sql2 = "select login from Users where login='" + login + "'";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println("");
				System.out.println("               Welcome Back! :)              ");
				String admin = rs.getString("isAdmin");
				if (admin.equals("1"))
					isAdmin = true;
				rs.close();
				return true;

			} else if (!stmt.executeQuery(sql2).next() && (!login.equals("") || !password.equals(""))) {

				System.out.println("");
				System.out.println("Welcome New User! - We're Setting-up Your Account :)");
				String sql3 = "INSERT INTO Users (login, upassword) VALUES ('" + login + "','" + password + "')";
				stmt.executeUpdate(sql3);
				return true;
			}

			System.out.println("the username and/or password you entered does not match! :(");
			System.out.println("*if you did not create this username, then it's already taken.\r\n");
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

		return false;
	}

	/**
	 * Shows this user with their profile information
	 * 
	 * @param stmt
	 *            databse connection
	 * @return user's profile information in a string
	 */
	public String getUserInfo(Statement stmt) {
		String sql = "select * from Users where login='" + login + "' AND upassword='" + password + "'";
		ResultSet rs = null;
		String output = "";

		try {
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			rs.next();

			for (int i = 1; i <= numCols; i++) { // print every column
													// returned...
				output += rsmd.getColumnName(i) + ": " + rs.getString(i) + "  ";
				output += "\r\n";
			}

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

		return output;
	}

	/**
	 * Updates a user's attribute information
	 * 
	 * @param fieldName
	 *            the name of the attribute
	 * @param newValue
	 *            the new value of the attribute
	 * @param stmt
	 *            database connection
	 */
	public void updateUserInfo(String fieldName, String newValue, Statement stmt) {

		if (!fieldName.equals("full_name") && !fieldName.equals("address") && !fieldName.equals("telephone")) {
			System.out.println("Sorry, the specified field name cannot be modified" + " or does not exist!");
			return;
		}
		String sql = "UPDATE Users SET " + fieldName + "='" + newValue + "' where login='" + login + "'";

		try {
			stmt.executeUpdate(sql);
			System.out.println("Your profile was updated successfully.");
		} catch (Exception e) {
			System.out.println("cannot execute the update.");
		}
	}

	/**
	 * Assignes a targeted user as 'Admin' by this user
	 * 
	 * @param target
	 *            targeted user login
	 * @param stmt
	 *            database connection
	 */
	public void assignAdmin(String target, Statement stmt) {
		String sql = "UPDATE Users SET isAdmin=1 where login='" + target + "'";
		try {
			stmt.executeUpdate(sql);
			System.out.println("Your assignment was made successfully.");
		} catch (Exception e) {
			System.out.println("cannot execute the update.");
		}
	}

	/**
	 * Prints the login of all existing users in the database
	 * 
	 * @param stmt
	 *            database connection
	 */
	public void printUsers(Statement stmt) {
		String sql = "select login from Users";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) { // for every row returned...
				System.out.println("[ " + rs.getString("login") + " ]");
			}
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
	 * @return true, if this user is an 'Admin'
	 */
	public boolean isAdmin() {
		return this.isAdmin;
	}

	/**
	 * Shows a list of the top trusted users determined by: (trusts - untrusts)
	 * 
	 * @param limit
	 *            maximum records to be provided
	 * @param stmt
	 *            database connection
	 * @return list of top trusted users
	 */
	public String trustedUsers(String limit, Statement stmt) {
		String sql = "SELECT U.login, U.full_name, U.address, U.telephone, (trusts-untrusts) as Diff FROM Users U, "
				+ "((SELECT trusted.login2 AS login1, trusted.trusts, untrusted.login2, IFNULL(untrusted.trusts, 0)"
				+ " as untrusts FROM "
				+ "(SELECT login2, COUNT(*) as trusts FROM Trust WHERE isTrusted=1 GROUP BY login2)"
				+ " as trusted LEFT OUTER JOIN "
				+ "(SELECT login2, COUNT(*) as trusts FROM Trust WHERE isTrusted=0 GROUP BY login2)"
				+ " as untrusted ON trusted.login2=untrusted.login2) " + "UNION "
				+ "(SELECT trusted.login2 AS login1, IFNULL(trusted.trusts, 0)"
				+ " as trusts, untrusted.login2, untrusted.trusts FROM "
				+ "(SELECT login2, COUNT(*) as trusts FROM Trust WHERE isTrusted=1 GROUP BY login2)"
				+ " as trusted RIGHT OUTER JOIN "
				+ "(SELECT login2, COUNT(*) as trusts FROM Trust WHERE isTrusted=0 GROUP BY login2)"
				+ " as untrusted ON trusted.login2=untrusted.login2)) as RES1 "
				+ "WHERE U.login=login1 or U.login=login2 ORDER BY Diff DESC LIMIT " + limit + "";

		ResultSet rs = null;
		String output = "";

		try {
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();

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

		return output;
	}

	/**
	 * Shows a list of the top useful users determined by the average usefulness
	 * of all of their recorded feedbacks
	 * 
	 * @param limit
	 *            maximum records to be provided
	 * @param stmt
	 *            database connection
	 * @return list of top useful users
	 */
	public String usefulUsers(String limit, Statement stmt) {
		String sql = "SELECT U.login, U.full_name, U.address, U.telephone, " + "ROUND(AVG(R.rating), 2) as rating "
				+ "FROM Users U, Feedback F, Rates R " + "WHERE U.login=F.login AND F.fid=R.fid "
				+ "GROUP BY U.login ORDER BY rating DESC LIMIT " + limit + "";

		ResultSet rs = null;
		String output = "";

		try {
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();

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

		return output;
	}

	/**
	 * Determines the 'Degree-of-Separation' from this user to a targeted user
	 * 
	 * @param target
	 *            targeted user login
	 * @param stmt
	 *            database connection
	 */
	public void DOS(String target, Statement stmt) {
		String sql1 = "SELECT COUNT(*) FROM Users U, Favorites F " + "WHERE U.login=F.login AND U.login ='" + target
				+ "' AND U.login!='" + login + "' " + "AND F.pid IN" + "(SELECT F2.pid FROM Users U2, Favorites F2 "
				+ "WHERE U2.login=F2.login AND U2.login='" + login + "')";

		String sql2 = "SELECT COUNT(distinct U4.login) FROM Users U4, Favorites F4 "
				+ "WHERE U4.login=F4.login AND U4.login!='" + login + "' AND U4.login='" + target + "' "
				+ "AND F4.pid IN " + "(SELECT distinct pid FROM " + "( "
				+ "SELECT U.login FROM Users U, Favorites F WHERE U.login=F.login AND U.login!='" + login + "' "
				+ "AND F.pid IN "
				+ "(SELECT distinct F2.pid FROM Users U2, Favorites F2 WHERE U2.login=F2.login AND U2.login='" + login
				+ "') " + ") AS first_Ds, " + "Users U3 JOIN Favorites F3 ON U3.login=F3.login "
				+ "WHERE first_Ds.login=U3.login " + ")";

		ResultSet rs = null;
		String output = "";

		try {
			// one degree
			rs = stmt.executeQuery(sql1);
			rs.next();
			output = rs.getString(1);
			rs.close();

			if (output.equals("1")) {
				System.out.println("You have one-degree of separation between you and " + target + ".");
			} else { // could be two-degree
				rs = stmt.executeQuery(sql2);
				rs.next();
				output = rs.getString(1);
				rs.close();

				if (output.equals("1")) {
					System.out.println("You have two-degrees of separation between you and " + target + ".");
				} else { // no association
					System.out.println("Sorry, there is no association between you and " + target + ".");
				}
			}

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
