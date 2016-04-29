package cs5530;

import java.sql.*;

//Alex Smith u0814844
public class Trusted 
{
	public String trustUser(String user1, String user2, String isTrusted, Statement stmt)
	{
		String sql = "insert into cs5530db78.Trust (Login1, Login2, Trusted) VALUES ('"+user1+"', '"+user2+"', '"+isTrusted+"')";
		String output="";
		
		try
		{
   		 	stmt.executeUpdate(sql);
   		 	output = "User trust values added";
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	output = e.toString();
		}
	    return output;
	}
}
