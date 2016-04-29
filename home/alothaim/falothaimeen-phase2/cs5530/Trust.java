package cs5530;

import java.sql.*;

/**
 * Provides function to minuplate/interact-with the Trust database table using
 * 'con.stmt'
 * 
 * @Assigment Phase2
 * @author Fahad Alothaimeen
 * @uNid u0824563
 */
public class Trust {

	/**
	 * Empty constructor to initialize the Trust object
	 */
	public Trust() {
	}

	/**
	 * Asseses a targeted user's trustness by a specified user
	 * 
	 * @param login
	 *            user login
	 * @param target
	 *            targeted user's login
	 * @param isTrusted
	 *            0-> un-trusted, 1-> trusted
	 * @param stmt
	 *            database connection
	 */
	public void assessUser(String login, String target, String isTrusted, Statement stmt) {
		if (login.equals(target)) {
			System.out.println("Sorry, you cannot trust yourself");
			return;
		} else {
			String sql = "INSERT INTO Trust (login1, login2, isTrusted) " + "VALUES ('" + login + "','" + target + "', "
					+ isTrusted + ")";

			try {
				stmt.executeUpdate(sql);
				System.out.println("your Trust assessment was recorded successfully.");
			} catch (SQLException e) {
				System.out.println("cannot execute the update.");
			}
		}
	}
}
