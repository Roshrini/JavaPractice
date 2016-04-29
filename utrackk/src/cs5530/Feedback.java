package cs5530;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Feedback {
	int poi_id;
	String login;
	int score;
	String text;
	Date date = null;
	public Feedback() {}
	
	public String toString()
	{
		String temp = "";
		temp += "POI Id:" + this.poi_id + ", Login:" + this.login + ", Score:" + this.score +
				", Review Text:" + this.text + ", Date:" + this.date;
		
		return temp;
	}
	
	public ArrayList<Feedback> getFeedback(Statement stmt, User user)
	{
		Feedback tempFeed = null;
		ArrayList<Feedback> feedList = null;
		String query="select F.poi_id, F.login, F.score, F.text, F.date from Feedback F "
				+ "where F.poi_id='"+this.poi_id+"' and F.login <> '" + user.login + "' "
						+ "and F.login NOT IN(SELECT R.feedback_login FROM Rating R WHERE R.poi_id='"+this.poi_id+"')";
		ResultSet rs=null;
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 	System.out.println("Feedback available for you to rate:");
	   		 	System.out.println("");
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	feedList = new ArrayList<Feedback>();
	   		 	int tempFeedPos = 0;
	   		 	while (rs.next())
				 {
	   		 		tempFeed = new Feedback();
	   		 		tempFeed.poi_id = rs.getInt("poi_id");
		   		 	tempFeed.login = rs.getString("login");
			   		tempFeed.score = rs.getInt("score");
			   		tempFeed.text = rs.getString("text");
			   		tempFeed.date = rs.getDate("date");
			   		feedList.add(tempFeed);
			   		System.out.print("Feedback Id:"+tempFeedPos + ", ");
			   		tempFeedPos++;
		   		 	System.out.println(tempFeed.toString());
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
	    return feedList;
	}
	
	public Hashtable<Integer, Integer> getFeedbackPOIIds(Statement stmt, User user)
	{
		Hashtable<Integer,Integer> feedbackIds = new Hashtable<Integer,Integer>();
		
		String query="select poi_id from Feedback where login='"+user.login+"'";
		ResultSet rs=null;
	 	try{
   		 	rs=stmt.executeQuery(query);
   		 	ResultSetMetaData rsmd = rs.getMetaData();
   		 	while (rs.next())
			 {
   		 		int id = rs.getInt("poi_id");
   		 		feedbackIds.put(id,id);
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
    return feedbackIds;
	}
	
	public Boolean insertFeedback(Statement stmt)
	{
		String sql = "INSERT INTO Feedback (poi_id, login, score, text, date) VALUES ('"+this.poi_id+"', '"+this.login+"', '"+this.score+"', '"+this.text+"', '"+this.date+"')";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			System.out.println("Feedback inserted successfully.");
			output = true;
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Cannot insert the Feedback.");
	 	}

		return output;
	}
	
	public ArrayList<Integer> getPoiUserAlreadyFeedbacked(Statement stmt, User user)
	{
		ArrayList<Integer> poi_list = new ArrayList<Integer>();
		String query="select poi_id from Feedback where login='"+user.login+"'";
		ResultSet rs=null;
	 	try{
   		 	rs=stmt.executeQuery(query);
   		 	ResultSetMetaData rsmd = rs.getMetaData();
   		 	while (rs.next())
			 {
   		 		poi_list.add(rs.getInt("poi_id"));
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
    return poi_list;
	}

	public void topUsefulFeedbacks(Statement stmt, int pid, int limit)
	{
		String query="";
		String select = "SELECT AverageFeedbackRatings.avg_rating, F.poi_id, F.login, F.text, F.score, F.date ";
		String from = "FROM Feedback F, ";
		String subFrom = "(SELECT R.poi_id, R.feedback_login AS ratedLogin, AVG(R.use_val) AS avg_rating "
				+ "FROM Rating R "
				+ "WHERE R.poi_id = '"+pid+"' "
						+ "GROUP BY R.poi_id, R.feedback_login) AverageFeedbackRatings ";
		String where = "WHERE F.login = AverageFeedbackRatings.ratedLogin "
				+ "AND F.poi_id = AverageFeedbackRatings.poi_id ";
		String order = "ORDER BY AverageFeedbackRatings.avg_rating DESC LIMIT " + limit;
		
		query += select;
		query += from;
		query += subFrom;
		query += where;
		query += order;
				
		ResultSet rs=null;
		
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 System.out.println("Top Useful Feedbacks (avg_rating, poi_id, user login, feedback text, score, date):");
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
