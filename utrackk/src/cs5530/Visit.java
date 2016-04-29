package cs5530;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class Visit {
	Date date = null;
	int cost;
	int num_ppl;
	String login;
	String poi_name;
	int poi_id; 
	
	public Visit() {}
	
	public Boolean insertVisit(Statement stmt)
	{	
		String sql = "INSERT INTO Visit_Instance (date, cost, num_ppl, login, poi_id) VALUES ('"+this.date+"', '"+this.cost+"', '"+this.num_ppl+"', '"+this.login+"', '"+this.poi_id+"')";
		Boolean output = false;
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String choice;
			int c = 0;
			System.out.println("You visited " + poi_name + " on " + date + " with a total number of people of " + num_ppl + " and a total cost of " + cost);
			System.out.println("Would you like to insert this into the database?");
			System.out.println("Press 1: Yes");
			System.out.println("Press any other key: No");
			while ((choice = in.readLine()) == null || choice.length() == 0);
	   		 c = Integer.parseInt(choice);
	   		 
	   		 if(c == 1)
	   		 {
				stmt.executeUpdate(sql);
				System.out.println("Visit inserted");
				output = true;
	   		 }
	   		 else
	   		 {
	   			 System.out.println("Visit deleted.");
	   			 output = false;
	   		 }
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Cannot insert the visit to this POI.");
	 	}

		return output;
	}
	
	public void visitSuggestions(Statement stmt)
	{	
		String sql = "SELECT COUNT(*) AS visit_count, P.poi_id, P.name, P.tel_num, P.street, P.city, P.state, P.zip_code, P.hours_op,"
				+ "P.year_est, P.price, P.url, P.category_name "
				+ "FROM POI P, Visit_Instance V1, Visit_Instance V2"
				+ " WHERE V1.poi_id ='"+this.poi_id+"' AND V1.login = V2.login "
						+ "AND V2.poi_id <>'"+this.poi_id+"' AND P.poi_id = V2.poi_id "
								+ "GROUP BY V2.poi_id "
								+ "ORDER BY COUNT(*) DESC";
		ResultSet rs=null;
	 	System.out.println("");
	 	try{
   		 	rs=stmt.executeQuery(sql);
   		 	System.out.println("Here are some additional POIs you may want to visit based on the one you just recorded:");
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
