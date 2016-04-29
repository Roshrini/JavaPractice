package cs5530;

import java.sql.Statement;

public class Trust {
	String rater_login;
	String rated_login;
	int trust_val;
	public Trust(){}
	
	public Boolean insertTrust(Statement stmt)
	{
		String sql = "INSERT INTO Trust (rater_login, rated_login, trust_val) VALUES ('"+this.rater_login+"', '"+this.rated_login+"', '"+this.trust_val+"')";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			System.out.println("Trust rating inserted");
			output = true;
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Cannot insert the Trust Rating.");
	 	}

		return output;
	}
}
