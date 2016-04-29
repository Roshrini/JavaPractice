package cs5530;

import java.sql.*;

/**
 * Provides function to minuplate/interact-with the POI database table using
 * 'con.stmt'
 * 
 * @Assigment Phase2
 * @author Fahad Alothaimeen
 * @uNid u0824563
 */
public class POI {

	/**
	 * Empty constructor to initialize the POI object
	 */
	public POI() {
	}

	/**
	 * Records a visit to a targeted POI made by a specified user
	 * 
	 * @param login
	 *            user login
	 * @param pid
	 *            targeted POI id
	 * @param cost
	 *            total cost of visit
	 * @param visitors
	 *            number of visitors
	 * @param date
	 *            date of visit
	 * @param stmt
	 *            database connection
	 */
	public void recordVisit(String login, String pid, String cost, String visitors, String date, Statement stmt) {
		String sql1 = "INSERT INTO VisEvent (cost, visitors) " + "VALUES (" + cost + ", " + visitors + ")";
		String sql2 = "SELECT veid FROM VisEvent ORDER BY veid DESC LIMIT 1";
		ResultSet rs = null;
		String veid = ""; // getting the VisEvent id of last added record in
							// VisEvent table

		try {
			stmt.executeUpdate(sql1);
		} catch (Exception e) {
			System.out.println("cannot execute the update.");
			return;
		}

		try {
			rs = stmt.executeQuery(sql2);
			rs.next();
			veid = rs.getString("veid");
			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query.");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset.");
				return;
			}
		}
		String sql3 = "INSERT INTO Visit (login, pid, veid, vdate) " + "VALUES ('" + login + "', " + pid + ", " + veid
				+ ", '" + date + "')";

		try {
			stmt.executeUpdate(sql3);
		} catch (Exception e) {
			System.out.println("cannot execute the query.");
			try {
				stmt.executeUpdate("DELETE FROM VisEvent WHERE veid='" + veid + "'");
			} catch (Exception e1) {
				System.out.println("cannot delete last added visit event.");
			}
			return;
		}
		System.out.println("Your visit was added successfully.");
	}

	/**
	 * Adds a POI to the database
	 * 
	 * @param pname
	 *            name of POI
	 * @param address
	 *            address of POI
	 * @param telephone
	 *            telephone of POI
	 * @param url
	 *            url of POI
	 * @param pyear
	 *            year of establishment of POI
	 * @param hours
	 *            hours of operations of POI
	 * @param price
	 *            average price per meal per visitor of POI
	 * @param category
	 *            category of POI
	 * @param stmt
	 *            database connection
	 */
	public void addPOI(String pname, String address, String telephone, String url, String pyear, String hours,
			String price, String category, Statement stmt) {
		// alowing 'skipping' meshanisim for better user experience
		if (!pname.equals(""))
			pname = "'" + pname + "'";
		else
			pname = "null";
		if (!address.equals(""))
			address = "'" + address + "'";
		else
			address = "null";
		if (!telephone.equals(""))
			telephone = "'" + telephone + "'";
		else
			telephone = "null";
		if (!url.equals(""))
			url = "'" + url + "'";
		else
			url = "null";
		if (!pyear.equals(""))
			pyear = "'" + pyear + "'";
		else
			pyear = "null";
		if (!hours.equals(""))
			hours = "'" + hours + "'";
		else
			hours = "null";
		if (!price.equals(""))
			price = "'" + price + "'";
		else
			price = "null";
		if (!category.equals(""))
			category = "'" + category + "'";
		else
			category = "null";

		String sql = "INSERT INTO POI (pname, address, telephone, url, " + "pyear, hours, price, category) "
				+ "VALUES (" + pname + ", " + address + "," + "" + telephone + ", " + url + ", " + pyear + ", " + hours
				+ ", " + price + ", " + category + ")";
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("cannot execute the update.");
			return;
		}
		System.out.println("Your POI was added successfully.");
	}

	/**
	 * Prints (breif list of) all POIs in the database
	 * 
	 * @param stmt
	 *            database connection
	 */
	public void printPOIs(Statement stmt) {
		String sql = "select pid, pname, address from POI";
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
	 * Shows a targeted POI information
	 * 
	 * @param target
	 *            targeted POI id
	 * @param stmt
	 *            database connection
	 * @return POI information in a string
	 */
	public String showPOI(String target, Statement stmt) {
		String sql = "select * from POI where pid=" + target;
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
	 * Updates POI's specified field information
	 * 
	 * @param target
	 *            targeted POI id
	 * @param fieldName
	 *            name of attribute to be updated
	 * @param newValue
	 *            new value of the attribute
	 * @param stmt
	 *            database connection
	 */
	public void updatePOIInfo(String target, String fieldName, String newValue, Statement stmt) {

		if (!fieldName.equals("pname") && !fieldName.equals("address") && !fieldName.equals("telephone")
				&& !fieldName.equals("url") && !fieldName.equals("pyear") && !fieldName.equals("hours")
				&& !fieldName.equals("price") && !fieldName.equals("category")) {
			System.out.println("Sorry, the specified field name cannot be modified" + " or does not exist!");
			return;
		}
		String sql = "UPDATE POI SET " + fieldName + "='" + newValue + "' where pid='" + target + "'";

		try {
			stmt.executeUpdate(sql);
			System.out.println("POI was updated successfully.");
		} catch (Exception e) {
			System.out.println("cannot execute the update.");
		}
	}

	/**
	 * Search the database for POI(s) using specified: keywords and/or category
	 * and/or price range and/or location
	 * 
	 * Then, sorts it (optional) by either: by price, by feedbacks, or by
	 * trusted feeedbacks
	 * 
	 * @param keywords
	 * @param category
	 * @param place
	 * @param price_range
	 * @param sort
	 * @param stmt
	 */
	public void search(String[] keywords, String category, String place, String[] price_range, String sort,
			Statement stmt) {
		String sql = "SELECT distinct P.pid, P.pname, P.address, P.telephone, P.url, P.pyear, P.hours, P.price, P.category "
				+ "FROM POI P";
		StringBuilder sqlsb = new StringBuilder(sql);
		ResultSet rs = null;

		try {
			if (keywords.length > 0 && !keywords[0].equals("")) {
				sqlsb.insert(sql.length(), ", HasKeywords H, Keywords K ");
				sqlsb.insert(sqlsb.length(), "WHERE P.pid=H.pid AND H.wid=K.wid");

				sqlsb.insert(sqlsb.length(), " AND (");
				for (int i = 0; i < keywords.length; i++) {
					if (i < (keywords.length - 1)) {
						sqlsb.insert(sqlsb.length(), "K.word LIKE '%" + keywords[i] + "%' OR ");
					} else {
						sqlsb.insert(sqlsb.length(), "K.word LIKE '%" + keywords[i] + "%')");
					}
				}
			}
			if (!category.equals("")) {
				if (sqlsb.toString().contains("WHERE")) {
					sqlsb.insert(sqlsb.length(), " AND P.category LIKE '%" + category + "%'");
				} else {
					sqlsb.insert(sqlsb.length(), " WHERE P.category LIKE '%" + category + "%'");
				}
			}
			if (!place.equals("")) {
				if (sqlsb.toString().contains("WHERE")) {
					sqlsb.insert(sqlsb.length(), " AND P.address LIKE '%" + place + "%'");
				} else {
					sqlsb.insert(sqlsb.length(), " WHERE P.address LIKE '%" + place + "%'");
				}
			}
			if (price_range.length > 0 && !price_range[0].equals("")) {
				if (sqlsb.toString().contains("WHERE")) {
					sqlsb.insert(sqlsb.length(),
							" AND (P.price >= " + price_range[0] + " AND P.price <= " + price_range[1] + ")");
				} else {
					sqlsb.insert(sqlsb.length(),
							" WHERE (P.price >= " + price_range[0] + " AND P.price <= " + price_range[1] + ")");
				}
			}
			if (!sort.equals("")) {
				if (sort.equals("a")) {
					sqlsb.insert(sqlsb.length(), " ORDER BY P.price");
				} else if (sort.equals("b")) {
					sqlsb.insert(sqlsb.indexOf("P.category") + 10, ", ROUND(AVG(F.score), 2) as score");
					sqlsb.insert(sqlsb.indexOf("POI P") + 5, ", Feedback F");

					if (sqlsb.toString().contains("WHERE")) {
						sqlsb.insert(sqlsb.length(), " AND P.pid=F.pid");
					} else {
						sqlsb.insert(sqlsb.length(), " WHERE P.pid=F.pid");
					}
					sqlsb.insert(sqlsb.length(), " GROUP BY P.pid ORDER BY score DESC");
				} else if (sort.equals("c")) {
					sqlsb.insert(sqlsb.indexOf("P.category") + 10, ", ROUND(AVG(F.score), 2) as score");
					sqlsb.insert(sqlsb.indexOf("POI P") + 5, ", Feedback F");

					if (sqlsb.toString().contains("WHERE")) {
						sqlsb.insert(sqlsb.length(), " AND P.pid=F.pid");
						sqlsb.insert(sqlsb.length(), "\r\nAND F.login IN\r\n");
						sqlsb.insert(sqlsb.length(), "(SELECT login2 FROM Trust WHERE isTrusted=1)\r\n");

					} else {
						sqlsb.insert(sqlsb.length(), " WHERE P.pid=F.pid");
						sqlsb.insert(sqlsb.length(), "\r\nAND F.login IN\r\n");
						sqlsb.insert(sqlsb.length(), "(SELECT login2 FROM Trust WHERE isTrusted=1)\r\n");
					}
					sqlsb.insert(sqlsb.length(), " GROUP BY P.pid ORDER BY score DESC");
				}
			}

			// Executing SQL
			rs = stmt.executeQuery(sqlsb.toString());
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
	 * Shows suggested POIs based on a specified POI
	 * 
	 * @param target
	 *            targeted POI id
	 * @param stmt
	 *            database connection
	 * @return list of all suggested POIs in a string
	 */
	public String showSuggestions(String target, Statement stmt) {
		String sql = "SELECT P.pid, P.pname, P.address, COUNT(*) as visits " + "FROM POI P, Visit V WHERE P.pid!="
				+ target + " AND P.pid=V.pid " + "AND V.login IN " + "(SELECT U2.login "
				+ "FROM POI P, Visit V, Users U2 " + "WHERE P.pid=V.pid AND V.login=U2.login AND P.pid=" + target + " "
				+ "GROUP BY U2.login) " + "GROUP BY P.pid ORDER BY visits DESC";

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
	 * Shows most popular POIs based on the total number of visits
	 * 
	 * @param limit
	 *            maximum records to be provided
	 * @param category
	 *            category of returned POIs
	 * @param stmt
	 *            database connection
	 * @return list of all of most popular POIs in a string
	 */
	public String popularPOI(String limit, String category, Statement stmt) {
		String sql = "SELECT P.pid, P.pname, P.address, COUNT(*) as visits " + "FROM POI P, Visit V "
				+ "WHERE P.pid=V.pid AND P.category='" + category + "' " + "GROUP BY P.pid ORDER BY visits DESC LIMIT "
				+ limit + "";

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
	 * Shows most expensive POIs based on the average price per head of all
	 * visits
	 * 
	 * @param limit
	 *            maximum records to be provided
	 * @param category
	 *            category of returned POIs
	 * @param stmt
	 *            database connection
	 * @return list of all of most expensive POIs in a string
	 */
	public String expensivePOI(String limit, String category, Statement stmt) {
		String sql = "Select pid, pname, address, ROUND(AVG(cost1), 2) as cost FROM "
				+ "(SELECT P.pid, P.pname, P.address, (coalesce(VE.cost)/coalesce(VE.visitors)) as cost1 "
				+ "FROM POI P, Visit V, VisEvent VE " + "WHERE P.pid=V.pid AND P.category='" + category
				+ "' AND V.veid=VE.veid) AS result " + "GROUP BY pid ORDER BY cost DESC LIMIT " + limit + "";

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
	 * Shows most highly rated POIs based on the average scores from all
	 * feedbacks a POI has received
	 * 
	 * @param limit
	 *            maximum records to be provided
	 * @param category
	 *            category of returned POIs
	 * @param stmt
	 *            database connection
	 * @return list of all of most highly rated POIs in a string
	 */
	public String ratedPOI(String limit, String category, Statement stmt) {
		String sql = "SELECT P.pid, P.pname, P.address, ROUND(AVG(F.score), 2) " + "as score "
				+ "FROM POI P, Feedback F " + "WHERE P.pid=F.pid AND P.category='" + category + "' "
				+ "GROUP BY P.pid ORDER BY score DESC LIMIT " + limit + "";

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
	 * Shows all existing categories in the database
	 * 
	 * @param stmt
	 *            database connection
	 * @return list of all categories in a string
	 */
	public String getCats(Statement stmt) {
		String sql = "SELECT distinct category FROM POI";

		ResultSet rs = null;
		String output = "";
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) // for every row returned...
				output += rs.getString("category") + ">";

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
}
