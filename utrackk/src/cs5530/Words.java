package cs5530;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Words {
	String word;
	int poi_id;
	public Words(){}
	
	public Boolean deleteWord(Statement stmt)
	{
		Boolean output = false;
		String sql = "DELETE FROM Words WHERE word='"+this.word+"' and poi_id='"+this.poi_id+"'";
		try
		{
			stmt.executeUpdate(sql);
			output = true;
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Cannot delete the word.");
	 	}
		return output;
	}
	
	public Boolean insertWord(Statement stmt)
	{
		String sql = "INSERT INTO Words (word, poi_id) VALUES ('"+this.word+"','"+this.poi_id+"')";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			System.out.println("Word and poi_id inserted successfully.");
			output = true;
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Word is too long or word is already associated with this POI.");
	 	}

		return output;
	}
	
	public void getWords(Statement stmt)
	{
		String query="select * from Words where poi_id='"+this.poi_id+"'";
		ResultSet rs=null;
		 	try{
	   		 	rs=stmt.executeQuery(query);
	   		 	ResultSetMetaData rsmd = rs.getMetaData();
	   		 	int numCols = rsmd.getColumnCount();
	   		 	while (rs.next())
				 {
		   		 	for (int i=1; i<numCols;i++)
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
