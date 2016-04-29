package cs5530;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class POIBrowsing {

	int minPrice;
	int maxPrice;
	String city;
	String state;
	ArrayList<String> keywords;
	String category;
	User user;
	Boolean searchPrice = false;
	Boolean searchCity = false;
	Boolean searchState = false;
	Boolean searchKeyword = false; 
	Boolean searchCategory = false; 
	Boolean sorted = false;
	Boolean sortedPrice = false;
	Boolean sortedFeedbacks = false;
	Boolean sortedTrustedFeedbacks = false;
	
	public POIBrowsing() {
		keywords = new ArrayList<String>();
	}
	
	public void browse(Statement stmt)
	{
		String query="";
		String orderSelect ="";
		String select = "P.poi_id, P.name, P.tel_num, P.street, P.city, P.state, P.zip_code, P.hours_op, P.year_est, P.price, P.url, P.category_name ";
		String from = "FROM POI P ";
		String where = "WHERE 2=2 ";
		String priceWhere = "";
		String addressWhere = "";
		String keywordWhere = "";
		String categoryWhere = "";
		String order = "";
		String subquery = "";
		
		// If user wants to search by price
		if(searchPrice)
		{
			priceWhere += "AND P.price <= '"+this.maxPrice+"' AND P.price >= '"+this.minPrice+"' ";
		}
		
		// If user wants to search by city
		if(searchCity)
		{
			addressWhere += "AND P.city = '"+this.city+"' ";
		}
		
		// If user wants to search by city
		if(searchState && !searchCity)
		{
			addressWhere += "AND P.state = '"+this.state+"' ";
		}
		
		if(searchState && searchCity)
		{
			addressWhere += "AND P.state = '"+this.state+"' ";
		}
		
		// If user wants to search by keywords
		if(searchKeyword)
		{
			from += ",Words K ";
			keywordWhere += "AND K.poi_id = P.poi_id AND (";
			ArrayList<String> whereKeys = new ArrayList<String>();
			for(int i = 0; i < keywords.size(); i++)
			{
				whereKeys.add("K.word = '"+keywords.get(i)+"' ");
			}
			
			if(whereKeys.size() > 1)
			{
				for(int j = 0; j < whereKeys.size() - 1; j++)
				{
					keywordWhere += whereKeys.get(j) + "OR ";
				}
				// Last key
				keywordWhere += whereKeys.get(whereKeys.size()-1) + ")";
			}
			else
			{
				keywordWhere += whereKeys.get(0) + ")";
			}
		}
		
		// If user wants to search by category
		if(searchCategory)
		{
			categoryWhere += "AND P.category_name = '"+this.category+"' ";
		}
		
		// If user wants to sort
		if(sorted)
		{
			if(sortedPrice)
			{
				order += "ORDER BY P.price";
			}
			else if(sortedFeedbacks)
			{
				orderSelect += "Feed.avg_score, ";
				subquery += "(SELECT F.poi_id AS poi_id, AVG(F.score) AS avg_score "
						+ "FROM Feedback F "
						+ "GROUP BY F.poi_id) Feed ";
				from += (", " + subquery);
				where += "AND P.poi_id = Feed.poi_id ";
				order += "ORDER BY Feed.avg_score DESC";
			}
			else if(sortedTrustedFeedbacks)
			{
				orderSelect += "TrustFeed.avg_score, ";
				subquery += "(SELECT F.poi_id, AVG(F.score) AS avg_score "
						+ "FROM Feedback F, Trust T "
						+ "WHERE T.rater_login = '"+user.login+"' "
								+ "AND T.trust_val='1' AND T.rated_login = F.login "
						+ "GROUP BY F.poi_id) TrustFeed ";
				from += (", " + subquery);
				where += "AND P.poi_id = TrustFeed.poi_id ";
				order += "ORDER BY TrustFeed.avg_score DESC";
			}
		}
		
		// Put all queries together
		query += "SELECT ";
		query += orderSelect;
		query += select;
		query += from;
		if(!priceWhere.isEmpty() || !addressWhere.isEmpty() || !keywordWhere.isEmpty() || !categoryWhere.isEmpty())
		{
			where += priceWhere;
			where += addressWhere;
			where += keywordWhere;
			where += categoryWhere;
		}
		query += where;
		
		
		if(sorted)
		{
			query += order;
		}
		
		ResultSet rs=null;
		 	try{
		 		System.out.println(query);
	   		 	rs=stmt.executeQuery(query);
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	System.out.println("");
	   		 	System.out.println("Your results are as follows:");
	   		 	System.out.println("");
	   		 	while (rs.next())
				 {
	   		 		String stat = "";
	   		 		if(sortedFeedbacks)
	   		 		{
	   		 			stat = "Avg Feedback Score:" + rs.getString("avg_score") + ", ";
	   		 		}
	   		 		if(sortedTrustedFeedbacks)
	   		 		{
	   		 			stat = "Avg Trusted Feedback Score:" + rs.getString("avg_score") + ", ";
	   		 		}
	   		 		POI poi = new POI();
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
		   		 	System.out.println(stat + poi.toString());
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
