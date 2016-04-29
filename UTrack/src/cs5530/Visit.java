package cs5530;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//Alex Smith u0814844
public class Visit 
{
	private String login;
	private String PID;
	private String cost;
	private String numberOfVisitors;
	private String date;
	
	public Visit(String l,String p,String c,String n)
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date d = new Date();
		date = dateFormat.format(d); // 03/21/2016
		
		login = l;
		PID = p;
		cost = c;
		numberOfVisitors = n;
	}
	
	public String addVisit(Statement stmt)
	{
		String sql = "insert into cs5530db78.Visit (Login, PID, Visit_Date, Cost, Number_of_Visitors) VALUES ('"+login+"', '"+PID+"', '"+date+"', '"+cost+"', '"+numberOfVisitors+"')";
		String output="";
		
		try
		{
   		 	stmt.executeUpdate(sql);
   		 	output = "Visit by "+login+" at PID "+PID+" successfully added";
		}
		catch(Exception e)
		{
		 	System.out.println("cannot execute the query");
		 	output = e.toString();
		}
	    return output;
	}

	public void displayEntry() 
	{
		System.out.println("Visit for user: "+login+" at POI ID: "+PID+" on date "+date+" with cost: "+cost+" with "+numberOfVisitors+" visitors.");
		
	}
}
