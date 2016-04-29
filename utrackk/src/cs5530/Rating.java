package cs5530;

import java.sql.Statement;

public class Rating {
	int poi_id;
	String rater_login;
	String feedback_login;
	int use_val;
	public Rating(){}
	
	public Boolean insertRating(Statement stmt)
	{
		String sql = "INSERT INTO Rating (poi_id, rater_login, feedback_login, use_val) VALUES ('"+this.poi_id+"', '"+this.rater_login+"', '"+this.feedback_login+"', '"+this.use_val+"')";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			System.out.println("Rating inserted successfully");
			output = true;
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Cannot insert the Rating.");
	 	}

		return output;
	}
	
}
