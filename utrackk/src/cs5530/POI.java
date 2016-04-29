package cs5530;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class POI {
	int poi_id;
	String name;
	String tel_num;
	String street;
	String city;
	String state;
	String zip_code;
	String hours_op;
	String year_est;
	int price;
	String url;
	String category_name;
	
	public POI() {}
	
	public String toString()
	{
		String temp = "";
		temp += "POI Id:" + this.poi_id + ", Name:" + this.name + ", Phone:" + this.tel_num +
				", Street:" + this.street + ", City:" + this.city + ", State:" + this.state +
				", Zipcode:" + this.zip_code + ", Hours of Operation:" + this.hours_op + 
				", Year Established:" + this.year_est + ", Estimated Price:" + this.price +
				", Category:" + this.category_name + ", URL:" + this.url;
		
		return temp;
	}
	
	public Boolean insertPOI(Statement stmt)
	{
		String sql = "INSERT INTO POI (name, tel_num, street, city, state, zip_code, hours_op, year_est, price, url, category_name) VALUES ('"+this.name+"', '"+this.tel_num+"', '"+this.street+"', '"+this.city+"', '"+this.state+"', '"+this.zip_code+"', '"+this.hours_op+"', '"+this.year_est+"', '"+this.price+"', '"+this.url+"', '"+this.category_name+"')";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			output = true;
		}
		catch(Exception e)
	 	{
	 		System.out.println("Cannot execute the query");
	 	}
		return output;
	}
	
	public Boolean updatePOI(Statement stmt)
	{
		String sql = "UPDATE POI SET name='"+this.name+"', tel_num='"+this.tel_num+"', street='"+this.street+"', city='"+this.city+"', state='"+this.state+"', zip_code='"+this.zip_code+"', hours_op='"+this.hours_op+"', year_est='"+this.year_est+"', price='"+this.price+"', url='"+this.url+"', category_name='"+this.category_name+"' WHERE poi_id='"+this.poi_id+"'";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			System.out.println("Update successful");
			output = true;
		}
		catch(Exception e)
	 	{
	 		System.out.println("Cannot execute the update");
	 	}

		return output;
	}
	
	public POI getPOIForUpdate(int id, Statement stmt)
	{
		POI poi = new POI();
		poi.poi_id = id;
		String query="select * from POI where poi_id='"+id+"'";
		ResultSet rs=null;
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 	while (rs.next())
				 {
	   		 		poi.poi_id = rs.getInt("poi_id");
	   		 		poi.name = rs.getString("name");
	   		 		poi.tel_num = rs.getString("tel_num");
	   		 		poi.street = rs.getString("street");
	   		 		poi.city = rs.getString("city");
	   		 		poi.state = rs.getString("state");
	   		 		poi.zip_code = rs.getString("zip_code");
	   		 		poi.hours_op = rs.getString("hours_op");
	   		 		poi.year_est = rs.getString("year_est");
	   		 		poi.price = rs.getInt("price");
	   		 		poi.url = rs.getString("url");
	   		 		poi.category_name = rs.getString("category_name");
				 }
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
		
	    return poi;
	}
	public boolean getPOI(String poi_name, Statement stmt)
	{
		String query="select * from POI where name='"+poi_name+"'";
		Boolean output=false;
		ResultSet rs=null;
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	while (rs.next())
				 {
	   		 		this.poi_id = rs.getInt("poi_id");
	   		 		this.name = rs.getString("name");
	   		 		this.tel_num = rs.getString("tel_num");
	   		 		this.street = rs.getString("street");
	   		 		this.city = rs.getString("city");
	   		 		this.state = rs.getString("state");
	   		 		this.zip_code = rs.getString("zip_code");
	   		 		this.hours_op = rs.getString("hours_op");
	   		 		this.year_est = rs.getString("year_est");
	   		 		this.price = rs.getInt("price");
	   		 		this.url = rs.getString("url");
	   		 		this.category_name = rs.getString("category_name");
	   		 		output = true;
		   		 	System.out.println(this.toString());
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
	    return output;
	}
	
	public boolean getPOIUserHasNotFeedbacked(String poi_name, Statement stmt, User user)
	{
		String query="select P.poi_id, P.name, P.tel_num, P.street, P.city, P.state, P.zip_code, P.hours_op, P.year_est, P.price, P.url, P.category_name "
				+ "from POI P, Feedback F where P.name='"+poi_name+"' AND F.login = '"+user.login+"' AND P.poi_id <> F.poi_id";
		Boolean output=false;
		ResultSet rs=null;
		 	try{
		 		System.out.println("");
		 		System.out.println("POIs available for feedback from you with name of " + poi_name +":");
	   		 	System.out.println("");
		 		rs=stmt.executeQuery(query);
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	while (rs.next())
				 {
		   		 	this.poi_id = rs.getInt("poi_id");
	   		 		this.name = rs.getString("name");
	   		 		this.tel_num = rs.getString("tel_num");
	   		 		this.street = rs.getString("street");
	   		 		this.city = rs.getString("city");
	   		 		this.state = rs.getString("state");
	   		 		this.zip_code = rs.getString("zip_code");
	   		 		this.hours_op = rs.getString("hours_op");
	   		 		this.year_est = rs.getString("year_est");
	   		 		this.price = rs.getInt("price");
	   		 		this.url = rs.getString("url");
	   		 		this.category_name = rs.getString("category_name");
	   		 		output = true;
		   		 	System.out.println(this.toString());
		   		 	System.out.println("");
				 }
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
	    return output;
	}

	
	
	public void mostExpensivePois(Statement stmt, int limit)
	{
		ArrayList<String> categories = this.getCategories(stmt);
		
		for(int j = 0; j < categories.size(); j++)
		{
		String query="";
		String select = "SELECT T.avg_cost, P.poi_id, P.name, P.tel_num, P.street, P.city, P.state, P.zip_code, P.hours_op, P.year_est, P.price, P.url, P.category_name ";
		String from = "FROM POI P, ";
		String subFrom = "(SELECT AVG(V.cost/V.num_ppl) AS avg_cost, V.poi_id AS poi_id "
				+ "FROM Visit_Instance V "
						+ "GROUP BY V.poi_id) T ";
		String where = "WHERE T.poi_id = P.poi_id "
				+ "AND P.category_name='"+categories.get(j)+"' ";
		String order = "ORDER BY T.avg_cost DESC LIMIT " + limit;
		
		query += select;
		query += from;
		query += subFrom;
		query += where;
		query += order;
				
		ResultSet rs=null;
		
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 	System.out.println("Category:" + categories.get(j));
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	while (rs.next())
				 {
	   		 		String stat = rs.getString("avg_cost");
		   		 	this.poi_id = rs.getInt("poi_id");
	   		 		this.name = rs.getString("name");
	   		 		this.tel_num = rs.getString("tel_num");
	   		 		this.street = rs.getString("street");
	   		 		this.city = rs.getString("city");
	   		 		this.state = rs.getString("state");
	   		 		this.zip_code = rs.getString("zip_code");
	   		 		this.hours_op = rs.getString("hours_op");
	   		 		this.year_est = rs.getString("year_est");
	   		 		this.price = rs.getInt("price");
	   		 		this.url = rs.getString("url");
	   		 		this.category_name = rs.getString("category_name");
		   		 	System.out.println("Average Cost:" +stat+", "+this.toString());
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
	
	public void mostHighlyRatedPois(Statement stmt, int limit)
	{
		ArrayList<String> categories = this.getCategories(stmt);
		
		for(int j = 0; j < categories.size(); j++)
		{
		String query="";
		String select = "SELECT T.avg_feedback, P.poi_id, P.name, P.tel_num, P.street, P.city, P.state, P.zip_code, P.hours_op, P.year_est, P.price, P.url, P.category_name ";
		String from = "FROM POI P, ";
		String subFrom = "(SELECT AVG(F.score) AS avg_feedback, F.poi_id AS poi_id "
				+ "FROM Feedback F "
						+ "GROUP BY F.poi_id) T ";
		String where = "WHERE T.poi_id = P.poi_id "
				+ "AND P.category_name='"+categories.get(j)+"' ";
		String order = "ORDER BY T.avg_feedback DESC LIMIT " + limit;
		
		query += select;
		query += from;
		query += subFrom;
		query += where;
		query += order;
				
		ResultSet rs=null;
		
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 	System.out.println("Category:" + categories.get(j));
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	while (rs.next())
				 {
	   		 		String stat = rs.getString("avg_feedback");
		   		 	this.poi_id = rs.getInt("poi_id");
	   		 		this.name = rs.getString("name");
	   		 		this.tel_num = rs.getString("tel_num");
	   		 		this.street = rs.getString("street");
	   		 		this.city = rs.getString("city");
	   		 		this.state = rs.getString("state");
	   		 		this.zip_code = rs.getString("zip_code");
	   		 		this.hours_op = rs.getString("hours_op");
	   		 		this.year_est = rs.getString("year_est");
	   		 		this.price = rs.getInt("price");
	   		 		this.url = rs.getString("url");
	   		 		this.category_name = rs.getString("category_name");
		   		 	System.out.println("Average Feedback Score:" +stat+", "+this.toString());
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
	
	public void mostPopularPois(Statement stmt, int limit)
	{
		ArrayList<String> categories = this.getCategories(stmt);
		
		for(int j = 0; j < categories.size(); j++)
		{
		String query="";
		String select = "SELECT T.visits, P.poi_id, P.name, P.tel_num, P.street, P.city, P.state, P.zip_code, P.hours_op, P.year_est, P.price, P.url, P.category_name ";
		String from = "FROM POI P, ";
		String subFrom = "(SELECT COUNT(*) AS visits, V.poi_id AS poi_id "
				+ "FROM Visit_Instance V "
						+ "GROUP BY V.poi_id) T ";
		String where = "WHERE T.poi_id = P.poi_id "
				+ "AND P.category_name='"+categories.get(j)+"' ";
		String order = "ORDER BY T.visits DESC LIMIT " + limit;
		
		query += select;
		query += from;
		query += subFrom;
		query += where;
		query += order;
				
		ResultSet rs=null;
		
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 	System.out.println("Category:" + categories.get(j));
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	while (rs.next())
				 {
	   		 		String stat = rs.getString("visits");
		   		 	this.poi_id = rs.getInt("poi_id");
	   		 		this.name = rs.getString("name");
	   		 		this.tel_num = rs.getString("tel_num");
	   		 		this.street = rs.getString("street");
	   		 		this.city = rs.getString("city");
	   		 		this.state = rs.getString("state");
	   		 		this.zip_code = rs.getString("zip_code");
	   		 		this.hours_op = rs.getString("hours_op");
	   		 		this.year_est = rs.getString("year_est");
	   		 		this.price = rs.getInt("price");
	   		 		this.url = rs.getString("url");
	   		 		this.category_name = rs.getString("category_name");
		   		 	System.out.println("Visit Count:" +stat+", "+this.toString());
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
	
	public ArrayList<String> getCategories(Statement stmt)
	{
		String query = "SELECT DISTINCT P.category_name FROM POI P";
		
		ArrayList<String> categories = new ArrayList<String>();
		ResultSet rs=null;
		
	 	try{
   		 	rs=stmt.executeQuery(query);
   		 	while (rs.next())
			 {
	   		 	categories.add(rs.getString("category_name"));
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
	 	return categories;
	}
	
}
