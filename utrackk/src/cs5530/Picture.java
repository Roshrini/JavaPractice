package cs5530;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Picture {
	int pic_id;
	String name;
	String image_path;
	int poi_id;
	public Picture(){}
	
	public Boolean insertPicture(Statement stmt)
	{
		String sql = "INSERT INTO Pictures (name, image_path, poi_id) VALUES ('"+this.name+"', '"+this.image_path+"', '"+this.poi_id+"')";
		Boolean output = false;
		try
		{
			stmt.executeUpdate(sql);
			System.out.println("Picture inserted successfully.");
			output = true;
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Picture failed to insert in database.");
	 	}

		return output;
	}
	
	public void getPictures(Statement stmt)
	{
		String query="select * from Pictures where poi_id='"+this.poi_id+"'";
		ResultSet rs=null;
		 	try{
	   		 	rs=stmt.executeQuery(query);
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
	
	public Boolean deletePicture(Statement stmt, int pid)
	{
		Boolean output = false;
		String sql = "DELETE FROM Pictures WHERE pic_id='"+pid+"' and poi_id='"+this.poi_id+"'";
		try
		{
			stmt.executeUpdate(sql);
			output = true;
		}
	 	catch(Exception e)
	 	{
	 		System.out.println("Cannot delete the picture.");
	 	}
		return output;
	}
}
