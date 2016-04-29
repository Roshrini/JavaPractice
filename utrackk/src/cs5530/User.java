package cs5530;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
	String login;
	String first_name;
	String last_name;
	String password;
	String street;
	String city;
	String state;
	String zip_code;
	String tel_num;
	String admin;
	public User()
	{
	}
	
	public String toString()
	{
		String temp = "";
		temp += "Login:" + this.login + ", First Name:" + this.first_name + ", Last Name:" + this.last_name +
				", Password:" + this.password + ", Street:" + this.street + ", City:" + this.city +
				", State:" + this.state + ", Zipcode:" + this.zip_code + 
				", Phone:" + this.tel_num + ", Admin:" + this.admin;
		
		return temp;
	}
	
	public User getUser(Statement stmt)
	{
		String sql="select * from Users where login='"+this.login+"' and password='"+this.password+"'";
		Boolean output=false;
		ResultSet rs=null;
		 	System.out.println("executing "+sql);
		 	try{
   		 	rs=stmt.executeQuery(sql);
   		 	while(rs.next())
			 {
   		 		this.login = rs.getString("login");
   		 		this.first_name = rs.getString("first_name");
   		 		this.last_name = rs.getString("last_name");
   		 		this.password = rs.getString("password");
   		 		this.street = rs.getString("street");
   		 		this.city = rs.getString("city");
   		 		this.state = rs.getString("state");
   		 		this.zip_code = rs.getString("zip_code");
   		 		this.tel_num = rs.getString("tel_num");
   		 		this.admin = rs.getString("admin");
			 }
		     rs.close();
		     return this;
		 	}
		 	catch(Exception e)
		 	{
		 		System.out.println("Cannot execute the query");
		 	}
		 	finally
		 	{
		 		try{
   		 		if (rs!=null && !rs.isClosed())
   		 			rs.close();
		 		}
		 		catch(Exception e)
		 		{
		 			System.out.println("Cannot close resultset");
		 		}
		 	}
	    return null;
	}

	public void getUserByName(Statement stmt, User user)
	{
			String query="select * from Users where first_name='"+this.first_name+"' and last_name='" + this.last_name + "' and login <> '" + user.login + "'";
			ResultSet rs=null;
			 	System.out.println("executing "+query);
			 	try{
		   		 	rs=stmt.executeQuery(query);
		   		 	ResultSetMetaData rsmd = rs.getMetaData();
		   		 	int numCols = rsmd.getColumnCount();
		   		 	while (rs.next())
					 {
			   		 	for (int i=1; i<=numCols;i++)
			   		 		System.out.print(rs.getString(i)+"  ");
			   		 	System.out.println("");
					 }
		   		 System.out.println(" ");
			     rs.close();
			 	}
			 	catch(Exception e)
			 	{
			 		System.out.println("Cannot execute the query");
			 	}
			 	finally
			 	{
			 		try{
	   		 		if (rs!=null && !rs.isClosed())
	   		 			rs.close();
			 		}
			 		catch(Exception e)
			 		{
			 			System.out.println("Cannot close resultset");
			 		}
			 	}
	}
	
	public Boolean uniqueLogin(Statement stmt)
	{
		String query = "SELECT login FROM Users";
		ResultSet rs = null;
		Boolean output = true;
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	while (rs.next())
				 {
		   		 	for (int i=1; i<=numCols;i++)
		   		 		if(rs.getString(i).equals(this.login))
		   		 			output = false;
				 }
			     
			     rs.close();
   		 	}
   		 	catch(Exception e)
   		 	{
   		 		System.out.println("cannot execute the query");
   		 	}
   		 	finally
   		 	{
   		 		try{
	   		 		if (rs!=null && !rs.isClosed())
	   		 			rs.close();
   		 		}
   		 		catch(Exception e)
   		 		{
   		 			System.out.println("cannot close resultset");
   		 		}
   		 	}
		 	return output;
	}
	
	public Boolean insertUser(Statement stmt)
	{
		String sql = "INSERT INTO Users (login, first_name, last_name, password, street, city, state, zip_code, tel_num) VALUES ('"+login+"', '"+first_name+"', '"+last_name+"', '"+password+"', '"+street+"', '"+city+"', '"+state+"', '"+zip_code+"', '"+tel_num+"')";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			output = true;
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Cannot register the user because the login name is not unique.");
	 	}

		return output;
	}
	
	public void twoDegreesSeparation(Statement stmt)
	{
		String query = "SELECT U.login, U.first_name, U.last_name " +
				"FROM Users U, Favorite TwoDegree, Favorite OneDegree2, " +
				"(SELECT oneDegreeFromUser.login AS login, oneDegreeFromUser.poi_id AS poi_id " +
                "FROM Favorite oneDegreeFromUser, Favorite UserFaves "+
                "WHERE UserFaves.login = '"+this.login+"' AND oneDegreeFromUser.login <> '"+this.login+"' " +
						"AND oneDegreeFromUser.poi_id = UserFaves.poi_id) OneDegree " +
				"WHERE TwoDegree.login <> OneDegree.login AND TwoDegree.login <> '"+this.login+"' " +
				"AND OneDegree.login = OneDegree2.login " +
				"AND OneDegree2.poi_id <> OneDegree.poi_id " +
				"AND TwoDegree.poi_id = OneDegree2.poi_id " +
				"AND TwoDegree.login = U.login";
		
		ResultSet rs=null;
		
	 	try{
	 		System.out.println("");
   		 	rs=stmt.executeQuery(query);
   		 	ResultSetMetaData rsmd = rs.getMetaData();
   		 	int numCols = rsmd.getColumnCount();
   		 	while (rs.next())
			 {
	   		 	for (int i=1; i<=numCols;i++)
	   		 		System.out.print(rs.getString(i)+"  ");
	   		 	System.out.println("");
			 }
   		 System.out.println(" ");
	     rs.close();
	 	}
	 	catch(Exception e)
	 	{
	 		System.out.println("Cannot execute the query");
	 	}
	 	finally
	 	{
	 		try{
		 		if (rs!=null && !rs.isClosed())
		 			rs.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
	}
	
	public void mostTrustedUsers(Statement stmt, int limit)
	{
		String query="";
		String select = "SELECT SUM(T.trust_val) AS score, U.login, U.first_name, U.last_name ";
		String from = "FROM Trust T, Users U ";
		String where = "WHERE T.rated_login = U.login ";
		String order = "GROUP BY T.rated_login ORDER BY score DESC LIMIT " + limit;
		
		query += select;
		query += from;
		query += where;
		query += order;
				
		ResultSet rs=null;
		
		 	try{
		 		
	   		 	rs=stmt.executeQuery(query);
	   		 System.out.println("Most Trusted Users (trust score, login, first name, last name):");
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	while (rs.next())
				 {
		   		 	for (int i=1; i<=numCols;i++)
		   		 		System.out.print(rs.getString(i)+"  ");
		   		 	System.out.println("");
				 }
	   		 System.out.println(" ");
		     rs.close();
		 	}
		 	catch(Exception e)
		 	{
		 		System.out.println("Cannot execute the query");
		 	}
		 	finally
		 	{
		 		try{
   		 		if (rs!=null && !rs.isClosed())
   		 			rs.close();
		 		}
		 		catch(Exception e)
		 		{
		 			System.out.println("Cannot close resultset");
		 		}
		 	}
	}
	
	public void mostUsefulUsers(Statement stmt, int limit)
	{
		String query="";
		String select = "SELECT T.avg_useval, U.login, U.first_name, U.last_name ";
		String from = "FROM Users U, ";
		String subFrom = "(SELECT AVG(R.use_val) AS avg_useval, R.feedback_login AS login "
				+ "FROM Rating R "
						+ "GROUP BY R.feedback_login) T ";
		String where = "WHERE T.login = U.login ";
		String order = "ORDER BY T.avg_useval DESC LIMIT " + limit;
		
		query += select;
		query += from;
		query += subFrom;
		query += where;
		query += order;
				
		ResultSet rs=null;
		
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 	System.out.println("Most Useful Users (avg_useval, login, first name, last name):");
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	while (rs.next())
				 {
		   		 	for (int i=1; i<=numCols;i++)
		   		 		System.out.print(rs.getString(i)+"  ");
		   		 	System.out.println("");
				 }
	   		 System.out.println(" ");
		     rs.close();
		 	}
		 	catch(Exception e)
		 	{
		 		System.out.println("Cannot execute the query");
		 	}
		 	finally
		 	{
		 		try{
   		 		if (rs!=null && !rs.isClosed())
   		 			rs.close();
		 		}
		 		catch(Exception e)
		 		{
		 			System.out.println("Cannot close resultset");
		 		}
		 	}
	}
}
