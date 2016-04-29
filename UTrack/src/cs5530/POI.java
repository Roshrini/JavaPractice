package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//Alex Smith u0814844
public class POI 
{

	public String addPOI(String name, String address, String city, String state, String URL, String phoneNumber, String yearEstablished, String hours, String category, Statement stmt)
	{
		String sql = "insert into cs5530db78.POI (Name, Address, City, State, URL, Phone_Number, Year_Established, Hours, Category) VALUES ('"+name+"', '"+address+"', '"+city+"', '"+state+"', '"+URL+"', '"+phoneNumber+"', '"+yearEstablished+"', '"+hours+"', '"+category+"')";
		String output="";
		
		try
		{
   		 	stmt.executeUpdate(sql);
   		 	output = "POI successfully added";
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	output = e.toString();
		}
	    return output;
	}
	
	public String updatePOI(String PID, String name, String address, String city, String state, String URL, String phoneNumber, String yearEstablished, String hours, String category, Statement stmt)
	{
		String sql = "update cs5530db78.POI p SET Name='"+name+"', Address='"+address+"', City='"+city+"', State='"+state+"', URL='"+URL+"', Phone_Number='"+phoneNumber+"', Year_Established='"+yearEstablished+"', Hours='"+hours+"', Category='"+category+"' where p.PID = '"+PID+"'";
		String output="";
		
		try
		{
   		 	stmt.executeUpdate(sql);
   		 	output = "POI successfully updated";
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	output = e.toString();
		}
	    return output;

	}

	public boolean isReviewed(String login, String PID, Statement stmt) 
	{
		String sql = "select f.FID from cs5530db78.Feedback f where f.Login = \""+login+"\" AND f.PID =\""+PID+"\"";
		
		ResultSet rs=null;
		
		try
		{
			stmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
   		 	while (rs.next())
			{
			    if(rs.getString("FID").equals(""))
			    {
			    	return false;
			    }
			    else
			    {
			    	return true;
			    }
			}
			
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	System.out.println(e.toString());
		}
		return false;
	}

	public String addFeedback(String score, String review, String PID, String login, Statement stmt) 
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String dateString = dateFormat.format(date); // 03/21/2016
		
		String sql = "insert into cs5530db78.Feedback (Score, Text, Fdate, PID, Login) VALUES ('"+score+"', '"+review+"', '"+dateString+"', '"+PID+"', '"+login+"')";
		String output="";
		
		try
		{
   		 	stmt.executeUpdate(sql);
   		 	output = "Feedback successfully added";
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	output = e.toString();
		}
	    return output;
		
	}

	public boolean isOwnReview(String login, String FID, Statement stmt) 
	{
		String sql = "select f.Login from cs5530db78.Feedback f where f.FID = \""+FID+"\"";
		
		ResultSet rs=null;
		
		try
		{
			stmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
   		 	while (rs.next())
			{
			    if(rs.getString("Login").equals(login))
			    {
			    	return true;
			    }
			    else
			    {
			    	return false;
			    }
			}
			
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	System.out.println(e.toString());
		}
		return false;
	}

	public String addRating(String FID, String login, String score, Statement stmt) 
	{
		String sql = "insert into cs5530db78.Rates (Login, FID, Rating) VALUES ('"+login+"', '"+FID+"', '"+score+"')";
		String output="";
		
		try
		{
   		 	stmt.executeUpdate(sql);
   		 	output = "Rating successfully added";
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	output = e.toString();
		}
	    return output;
	}

	public void getTopFeedbacks(String pID, String num, Statement stmt) 
	{
		String sql = "select f.Login, f.FID, f.Score, f.Text, AVG(r.Rating) AS AvgRating from cs5530db78.Rates r, cs5530db78.Feedback f where f.PID = "+pID+" AND f.FID = r.FID ORDER BY AVG(r.Rating) desc LIMIT "+num;
		
		ResultSet rs=null;
		
		try
		{
			stmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
   		 	while (rs.next())
			{
			    System.out.println(rs.getString("Login")+"\t"+rs.getString("FID")+"\t"+rs.getString("Score")+"\t"+rs.getString("Text")+"\t"+rs.getString("AvgRating"));
			}
			
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	System.out.println(e.toString());
		}
		
	}
	
	public void getMostExpensive(String category, String num, Statement stmt)
	{
		String sql = "SELECT *, v.Cost / v.Number_of_Visitors AS AvgCost FROM cs5530db78.Visit v, cs5530db78.POI p WHERE p.PID = v.PID AND p.Category = '"+category+"' ORDER BY v.Cost / v.Number_of_Visitors DESC LIMIT "+num;
		ResultSet rs=null;
		
		try
		{
			stmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
   		 	while (rs.next())
			{
			    System.out.println(rs.getString("PID")+"\t"+rs.getString("Name")+"\t"+rs.getString("AvgCost"));
			}
			
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	System.out.println(e.toString());
		}
	}
	
	public void getMostPopular(String category, String num, Statement stmt)
	{
		String sql = "SELECT *, COUNT(v.PID) FROM cs5530db78.Visit v, cs5530db78.POI p WHERE p.PID = v.PID AND p.Category = '"+category+"' ORDER BY COUNT(v.PID) DESC LIMIT "+num;
		ResultSet rs=null;
		
		try
		{
			stmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
   		 	while (rs.next())
			{
			    System.out.println(rs.getString("PID")+"\t"+rs.getString("Name")+"\t"+rs.getString("COUNT(v.PID)"));
			}
			
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	System.out.println(e.toString());
		}
	}
	
	public void getHighestRated(String category, String num, Statement stmt)
	{
		String sql = "SELECT *, avg(f.Score) FROM cs5530db78.Feedback f, cs5530db78.POI p WHERE p.PID = f.PID AND p.Category = '"+category+"' ORDER BY avg(f.Score) DESC LIMIT "+num;
		ResultSet rs=null;
		
		try
		{
			stmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
   		 	while (rs.next())
			{
			    System.out.println(rs.getString("PID")+"\t"+rs.getString("Name")+"\t"+rs.getString("avg(f.Score)"));
			}
			
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	System.out.println(e.toString());
		}
	}
	
	public void searchPOI(String minPrice, String maxPrice, String address, String city, String state, String category, int sort, Statement stmt)
	{
		String sql = "";
		if(sort == 1)
		{
			sql = "select *, v.cost / v.Number_of_Visitors AS AvgS from cs5530db78.Visit v, cs5530db78.POI p where p.PID = v.PID";
		}
		else if(sort == 2)
		{
			sql = "select *, AVG(f.Score) as AvgS from cs5530db78.Feedback f, cs5530db78.POI p where p.PID = f.PID";
		}
		else
		{
			sql = "select *, AVG(f.Score) as AvgS from cs5530db78.Feedback f, cs5530db78.Trust t, cs5530db78.POI p where p.PID = f.PID AND f.login = t.login2";
		}
		//String sql = "select * from cs55330db78.POI p where p.PID != 0";
		
		if(!minPrice.equals(""))
		{
			sql+= " and p.Price >"+minPrice;
		}
		if(!maxPrice.equals(""))
		{
			sql+= " and p.Price <"+maxPrice;
		}
		if(!address.equals(""))
		{
			sql+= " and p.Address like '%"+address+"%'";
		}
		if(!city.equals(""))
		{
			sql+= " and p.City like '%"+city+"%'";
		}
		if(!state.equals(""))
		{
			sql+= " and p.State like '%"+state+"%'";
		}
		if(!category.equals(""))
		{
			sql+= " and p.Category like '%"+category+"%'";
		}
		
		if(sort == 1)
		{
			sql += " group by v.PID order by v.cost / v.Number_of_Visitors asc";
		}
		else if(sort == 2)
		{
			sql += "group by f.PID order by AVG(f.Score) desc";
		}
		else
		{
			sql += "group by f.PID order by AVG(f.Score) DESC";
		}
		
		ResultSet rs=null;
		String out = "";
		try
		{
			stmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
   		 	while (rs.next())
			{
   		 		out += rs.getString("PID")+"\t"+rs.getString("Name")+"\t"+rs.getString("Address")+"\t"+rs.getString("City")+
   		 				"\t"+rs.getString("State")+"\t"+rs.getString("URl")+"\t"+rs.getString("Phone_Number")+"\t"+rs.getString("Year_Established")+"\t"
   		 				+rs.getString("Hours")+"\t"+rs.getString("Category")+"\t" + rs.getString("AvgS");
			    System.out.println(out);
			}
			
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	System.out.println(e.toString());
		}
		System.out.println();
	}
	
}
