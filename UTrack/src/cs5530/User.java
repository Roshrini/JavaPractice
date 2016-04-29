package cs5530;

import java.sql.*;

//Alex Smith u0814844
public class User 
{
	public String createUser(String login, String password, String name, String phoneNumber, boolean admin, Statement stmt)
	{
		int adminVal = 0;
		if(admin)
		{
			adminVal = 1;
		}
		String sql="insert into cs5530db78.Users values ('"+login+"', '"+name+"','"+adminVal+"','"+password+"','"+phoneNumber+"');";
		String output="";
		
		try
		{
   		 	stmt.executeUpdate(sql);
   		 	output = "User successfully added";
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	output = e.toString();
		}
	    return output;
	}
	
	public boolean isAdmin(String login, Statement stmt)
	{
		String sql = "select u.User_Type from cs5530db78.Users u where u.Login = \""+login+"\"";
		
		ResultSet rs=null;
		
		try
		{
			stmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
   		 	while (rs.next())
			{
			    if(rs.getString("User_Type").equals("1"))
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
	
	public String getUserPassword(String login, Statement stmt)
	{
		String sql = "select u.Password from cs5530db78.Users u where u.Login = \""+login+"\"";
		
		ResultSet rs=null;
		String output = "";
		try
		{
			stmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
   		 	while (rs.next())
			{
			    output = rs.getString("Password");
			}
			
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	System.out.println(e.toString());
		}
		return output;
	}
}
