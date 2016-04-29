package cs5530;

import java.sql.Statement;

public class Favorite {
	String login;
	int poi_id;
	public Favorite() {}
	
	public Boolean insertFavorite(Statement stmt)
	{
		String sql = "INSERT INTO Favorite (poi_id, login) VALUES ('"+this.poi_id+"', '"+this.login+"')";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			output = true;
			System.out.println("Favorite inserted.");
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Cannot insert the Favorite due to SQL error or because this Favorite already exists for this user.");
	 	}

		return output;
	}
}
