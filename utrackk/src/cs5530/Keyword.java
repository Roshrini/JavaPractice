package cs5530;

import java.sql.Statement;

public class Keyword {
	String word;
	public Keyword(){}
	
	public Boolean insertKeyword(Statement stmt)
	{
		String sql = "INSERT INTO Keywords (word) VALUES ('"+this.word+"')";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			System.out.println("Keyword inserted successfully.");
			output = true;
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Keyword exists in database.  Associating with this POI.");
	 	}

		return output;
	}
}
