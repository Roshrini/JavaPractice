package cs5530;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//Alex Smith u0814844
public class Favorite 
{
	public String addFavorite(String PID, String login, Statement stmt)
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String dateString = dateFormat.format(date); // 03/21/2016
			
		String sql = "insert into cs5530db78.Favorites (PID, Login, Fvdate) VALUES ('"+PID+"', '"+login+"', '"+dateString+"')";
		String output="";
		
		try
		{
   		 	stmt.executeUpdate(sql);
   		 	output = "Favorite successfully added";
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	output = e.toString();
		}
	    return output;
	}
}
