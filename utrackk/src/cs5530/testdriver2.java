package cs5530;


import java.lang.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.io.*;

public class testdriver2 {

	/**
	 * @param args
	 */
	public static void displayMenu()
	{
		 System.out.println("        Welcome to the UTrack System     ");
    	 System.out.println("1. Login");
    	 System.out.println("2. Register New User");
    	 System.out.println("3. Exit");
    	 System.out.println("Enter your choice:");
	}
	
	public static Boolean displayVisitMenu(Connector con, User user, List<Visit> visits)
	{
		 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		 String choice;
		 int c = 0;
		 
		 System.out.println("Please select what would you like to do from the options below:");
	   	 System.out.println("1. Record another visit");
	   	 System.out.println("2. Review and Insert all recorded visits into the database");
	   	 System.out.println("Press any other Key: Erase all recorded visits and exit visits menu");
	   	 System.out.println("Enter your choice:");
	   	
	   	try{
	   		while ((choice = in.readLine()) == null || choice.length() == 0);
	   		 c = Integer.parseInt(choice);
	   		 
	   		 if(c == 1)
	   		 {
	   			 Visit tempVisit = recordVisit(con, user);
	   			 visits.add(tempVisit);
	   			 displayVisitMenu(con, user, visits);
	   		 }
	   		 if(c==2)
	   		 {
	   			for(int i = 0; i < visits.size(); i++)
	   			{
	   				visits.get(i).insertVisit(con.stmt);
	   			}
	   		 }
	   			 
	   	 }catch (Exception e)
	   	 {
	   	 }
	   	return false;
	}
	
	public static void displayUserMenu(String name, String admin)
	{	
		 System.out.println("Welcome " + name);
		 System.out.println("Available functionality below:");
	   	 System.out.println("1. Visit a POI:");
    	 System.out.println("2. Favorite a POI");
    	 System.out.println("3. POI Feedback");
    	 System.out.println("4. Usefulness Ratings");
    	 System.out.println("5. Trust Recordings");
    	 System.out.println("6. POI Browsing");
    	 System.out.println("7. Useful Feedbacks");
    	 System.out.println("8. Two degrees of separation");
    	 System.out.println("9. Statistics");
    	 System.out.println("10. Exit");
	   	 
	   	 if(admin.equals("1"))
	   	 {
	   		 System.out.println("");
		   	 System.out.println("Administrator Functionality:");
		   	System.out.println("11. User Awards");
		   	 System.out.println("12. New POI");
		   	 System.out.println("13. Update POI");
		   	System.out.println("14. Add Keywords");
		   	System.out.println("15. Delete Keywords");
		   	System.out.println("16. Add Pictures");
		   	System.out.println("17. Delete Pictures");
		   	 System.out.println("");
	   	 }
	   	System.out.println("Please select a number of what would you like to do from the options above:");
	}
	
	public static void displayPOIUpdateMenu()
	{
		System.out.println("Which of the following would you like to update:");
	   	 System.out.println("1. Name:");
	   	 System.out.println("2. Telephone Number");
	   	 System.out.println("3. Street Address");
	   	 System.out.println("4. City");
	   	 System.out.println("5. State");
	   	 System.out.println("6. Zipcode");
	   	 System.out.println("7. Hours of Operation");
	   	 System.out.println("8. Year Established");
	   	 System.out.println("9. Average Price");
	   	 System.out.println("10. URL");
	   	 System.out.println("11. Category");
	   	 System.out.println("12. Exit and send all updates to database (if no updates were made, POI will remain the same)");
	   	 System.out.println("Please enter a number:");
	}
	
	public static Visit recordVisit(Connector con, User user)
	{
		try
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			Visit visit = new Visit();
			String poiName;
			 String date;
			 String cost;
			 String num_ppl;
			 String poi_id;
			 Boolean validPoi = false;
			 Boolean validPoiId = false;
			 Boolean validDate = false;
			 Boolean validCost = false;
			 Boolean validNumPpl = false;
			 POI poi = new POI();
			 System.out.println("Please enter the name of the POI you visited:");
			 while ((poiName = in.readLine()) == null || poiName.length() == 0);
			 validPoi = poi.getPOI(poiName, con.stmt);
			 while(!validPoi)
			 {
				 System.out.println("That POI does not exist in our system.  Please try again:");
				 while ((poiName = in.readLine()) == null || poiName.length() == 0);
				 validPoi = poi.getPOI(poiName, con.stmt);
			 }
			 if(validPoi)
			 {
				 visit.poi_name = poiName;
				 while(!validPoiId)
				 {
					 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you visited from the above list."); 
					 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
					 try {
		       			 visit.poi_id = Integer.parseInt(poi_id);
		       			 validPoiId = true;
		       		 } catch(NumberFormatException e) {
		       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
		       		 }
				 }
			 }
			 System.out.println("Please enter the date you visited in this format YYYY-MM-DD :");
			 while(!validDate)
			 {
				 while ((date = in.readLine()) == null || date.length() == 0);
				 try {
	   			 visit.date = java.sql.Date.valueOf(date);
	   			 validDate = true;
	   		 } catch(IllegalArgumentException e) {
	   			 System.out.println("You inputted the date in the wrong format. Please try again YYYY-MM-DD:");
	   		 }
			 }
			 System.out.println("Please enter the cost of your visit as an integer:");
			 while(!validCost)
			 {
				 while ((cost = in.readLine()) == null || cost.length() == 0);
				 try {
		   			 visit.cost = Integer.parseInt(cost);
		   			 validCost = true;
		   		 } catch(NumberFormatException e) {
		   			 System.out.println("You didn't input the cost as an integer. Please try again:");
		   		 }
			 }
			 System.out.println("Please enter the number of people who visited as an integer:");
			 while(!validNumPpl)
			 {
				 while ((num_ppl = in.readLine()) == null || num_ppl.length() == 0);
				 try {
	   			 visit.num_ppl = Integer.parseInt(num_ppl);
	   			 validNumPpl = true;
	   		 } catch(NumberFormatException e) {
	   			 System.out.println("You didn't input the number of people as an integer. Please try again:");
	   		 } 
			 }
			 visit.login = user.login;
			 System.out.println("Visit recorded and awaiting final confirmation to be inserted into the database.  Please confirm through the visit menu, option 2.");
			 System.out.println("");
			 visit.visitSuggestions(con.stmt);
			 return visit;
		}
		catch(Exception e) {
		}
		return null;
	}
	
	public static void newPOI(Connector con, User user)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		POI poi = new POI();
		String price;
		Boolean validPrice = false;
		
		try
		{
			System.out.println("Please enter the name of the POI:");
			 while ((poi.name = in.readLine()) == null || poi.name.length() == 0);
			 System.out.println("Please enter the telephone number of the POI as 10 digits (do not include parentheses or dashes e.g. 8011234567):");
			 while ((poi.tel_num = in.readLine()) == null || poi.tel_num.length() == 0 || poi.tel_num.length() > 10);
			 System.out.println("Please enter the street address of the POI:");
			 while ((poi.street = in.readLine()) == null || poi.street.length() == 0);
			 System.out.println("Please enter the city where the POI is located:");
			 while ((poi.city = in.readLine()) == null || poi.city.length() == 0);
			 System.out.println("Please enter the state where the POI is located with two letters e.g. Utah = UT:");
			 while ((poi.state = in.readLine()) == null || poi.state.length() == 0 || poi.state.length() > 2);
			 System.out.println("Please enter the zip code of the POI as 5 digits:");
			 while ((poi.zip_code = in.readLine()) == null || poi.zip_code.length() == 0 || poi.zip_code.length() > 5);
			 System.out.println("Please enter the hours of operation for the POI:");
			 while ((poi.hours_op = in.readLine()) == null || poi.hours_op.length() == 0);
			 System.out.println("Please enter the year of establishment for the POI:");
			 while ((poi.year_est = in.readLine()) == null || poi.year_est.length() == 0);
			 System.out.println("Please enter the estimated price of the POI as an integer:");
			 while(!validPrice)
			 {
				 while ((price = in.readLine()) == null || price.length() == 0);
				 try {
	       			 poi.price = Integer.parseInt(price);
	       			 validPrice = true;
	       		 } catch(NumberFormatException e) {
	       			 System.out.println("You didn't input the average price as an integer. Please try again:");
	       		 }
			 }
			 System.out.println("Please enter the url of the POI:");
			 while ((poi.url = in.readLine()) == null || poi.url.length() == 0);
			 System.out.println("Please enter the category of the POI:");
			 while ((poi.category_name = in.readLine()) == null || poi.category_name.length() == 0);
			 poi.insertPOI(con.stmt);
		}
		catch(Exception e){
			System.out.println("New POI failed to insert into the database.  Check the attribute types and please try again.");
		}
		
	}
	
	public static void updatePOI(Connector con, User user)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try{
			String poiName;
			String poi_id;
			Boolean validPoi = false;
			Boolean validPoiId = false;
			POI poi = new POI();
			System.out.println("Please enter the name of the POI you would like to update:");
			 while ((poiName = in.readLine()) == null || poiName.length() == 0);
			 validPoi = poi.getPOI(poiName, con.stmt);
			 while(!validPoi)
			 {
				 System.out.println("That POI does not exist in our system.  Please try again:");
				 while ((poiName = in.readLine()) == null || poiName.length() == 0);
				 validPoi = poi.getPOI(poiName, con.stmt);
			 }
			 if(validPoi)
			 {
				 poi.name = poiName;
				 while(!validPoiId)
				 {
					 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you would like to update from the above list."); 
					 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
					 try {
		       			 poi.poi_id = Integer.parseInt(poi_id);
		       			 validPoiId = true;
		       		 } catch(NumberFormatException e) {
		       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
		       		 }
				 }
				 poi = poi.getPOIForUpdate(poi.poi_id, con.stmt);
				 Boolean updating = true;
				 String choice;
				 int c = 0;
				 while(updating)
	             {
	            	 displayPOIUpdateMenu();
	            	 while ((choice = in.readLine()) == null || choice.length() == 0);
	            	 try{
		            		 c = Integer.parseInt(choice);
		            		 if(c == 1)
		            		 {
		            			 System.out.println("The POI's current name is: " + poi.name);
		            			 System.out.println("Enter updated name:");
		            			 while ((poi.name = in.readLine()) == null || poi.name.length() == 0);
		            		 }
		            		 else if(c == 2)
		            		 {
		            			 System.out.println("The POI's current telephone number is: " + poi.tel_num);
		            			 System.out.println("Enter updated telephone number as 10 digits (do not include parentheses or dashes e.g. 8011234567):");
		            			 while ((poi.tel_num = in.readLine()) == null || poi.tel_num.length() == 0 || poi.tel_num.length() > 10);
		            		 }
		            		 else if(c == 3)
		            		 {
		            			 System.out.println("The POI's current street address is: " + poi.street);
		            			 System.out.println("Enter updated street address:");
		            			 while ((poi.street = in.readLine()) == null || poi.street.length() == 0);
		            		 }
		            		 else if(c == 4)
		            		 {
		            			 System.out.println("The POI's current city is: " + poi.city);
		            			 System.out.println("Enter updated city:");
		            			 while ((poi.city = in.readLine()) == null || poi.city.length() == 0);
		            		 }
		            		 else if(c == 5)
		            		 {
		            			 System.out.println("The POI's current state is: " + poi.state);
		            			 System.out.println("Enter updated state with two letters e.g. Utah = UT:");
		            			 while ((poi.state = in.readLine()) == null || poi.state.length() == 0 || poi.state.length() > 2);
		            		 }
		            		 else if(c == 6)
		            		 {
		            			 System.out.println("The POI's current zipcode is: " + poi.zip_code);
		            			 System.out.println("Enter updated zipcode as 5 digits:");
		            			 while ((poi.zip_code = in.readLine()) == null || poi.zip_code.length() == 0 || poi.zip_code.length() > 5);
		            		 }
		            		 else if(c == 7)
		            		 {
		            			 System.out.println("The POI's current Hours of Operation are: " + poi.hours_op);
		            			 System.out.println("Enter updated Hours of Operation:");
		            			 while ((poi.hours_op = in.readLine()) == null || poi.hours_op.length() == 0);
		            		 }
		            		 else if(c == 8)
		            		 {
		            			 System.out.println("The POI's current year established is: " + poi.year_est);
		            			 System.out.println("Enter updated year established:");
		            			 while ((poi.year_est = in.readLine()) == null || poi.year_est.length() == 0);
		            		 }
		            		 else if(c == 9)
		            		 {
		            			 System.out.println("The POI's current estimated price is: " + poi.price);
		            			 System.out.println("Enter updated price as an integer:");
		            			 Boolean validPrice = false;
		            			 String price;
		            			 while(!validPrice)
		            			 {
		            				 while ((price = in.readLine()) == null || price.length() == 0);
		            				 try {
		            	       			 poi.price = Integer.parseInt(price);
		            	       			 validPrice = true;
		            	       		 } catch(NumberFormatException e) {
		            	       			 System.out.println("You didn't input the average price as an integer. Please refer to the printed list and try again:");
		            	       		 }
		            			 }
		            		 }
		            		 else if(c == 10)
		            		 {
		            			 System.out.println("The POI's current URL is: " + poi.url);
		            			 System.out.println("Enter updated URL:");
		            			 while ((poi.url = in.readLine()) == null || poi.url.length() == 0);
		            		 }
		            		 else if(c == 11)
		            		 {
		            			 System.out.println("The POI's current category is: " + poi.category_name);
		            			 System.out.println("Enter updated category name:");
		            			 while ((poi.category_name = in.readLine()) == null || poi.category_name.length() == 0);
		            		 }
		            		 // Send all updates
		            		 else
		            		 {
		            			 poi.updatePOI(con.stmt);
		            			 updating = false;
		            		 }
	            		 }
		            	 catch (Exception e)
		            	 {
		            		 System.out.println("POI failed to update.  Please check your attribute types and try again.");
		            		 continue;
		            	 }
	             }
			 }}
			 catch(Exception e) {
				 
			 }
		}
	
	public static void addKeywords(Connector con)
	{
		try
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String poiName;
			 String poi_id;
			 Boolean validPoi = false;
			 Boolean validPoiId = false;
			 POI poi = new POI();
			 System.out.println("Please enter the name of the POI you would like to add keywords to:");
			 while ((poiName = in.readLine()) == null || poiName.length() == 0);
			 validPoi = poi.getPOI(poiName, con.stmt);
			 while(!validPoi)
			 {
				 System.out.println("That POI does not exist in our system.  Please try again:");
				 while ((poiName = in.readLine()) == null || poiName.length() == 0);
				 validPoi = poi.getPOI(poiName, con.stmt);
			 }
			 if(validPoi)
			 {
				 poi.name = poiName;
				 while(!validPoiId)
				 {
					 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you would like to add keywords to."); 
					 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
					 try {
		       			 poi.poi_id = Integer.parseInt(poi_id);
		       			 validPoiId = true;
		       		 } catch(NumberFormatException e) {
		       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
		       		 }
				 }
				 poi = poi.getPOIForUpdate(poi.poi_id, con.stmt);
				 String choice;
				 int c = 0;
				 System.out.println("Would you like to enter any keywords associated with this POI?");
				 System.out.println("1: Yes");
				 System.out.println("2: No");
				 System.out.println("Enter your choice below:");
				 Boolean validChoice = false;
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c > 2 || c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
			   		 }
				 }
				 while(c == 1)
				 {
					 System.out.println("The keywords associated with this POI are listed below:");
					 // Show user list of keywords
					 Words words = new Words();
					 words.poi_id = poi.poi_id;
					 System.out.println("");
					 words.getWords(con.stmt);
					 
					 Keyword keyword = new Keyword();
					 Words word = new Words();
					 System.out.println("Please input a new keyword for this POI below:");
					 while ((keyword.word = in.readLine()) == null || keyword.word.length() == 0);
					 
					 keyword.insertKeyword(con.stmt);
					 word.word = keyword.word;
					 word.poi_id = poi.poi_id;
					 word.insertWord(con.stmt);
					 
					 System.out.println("Would you like to enter any more keywords associated with this POI?");
					 System.out.println("1: Yes");
					 System.out.println("2: No");
					 System.out.println("Enter your choice below:");
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c > 2 || c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input the score as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
			   		 }
					 
				 }
			 }
		}
		catch(Exception e) {}
	}
	
	public static void deleteKeywords(Connector con)
	{
		try
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String poiName;
			 String poi_id;
			 Boolean validPoi = false;
			 Boolean validPoiId = false;
			 POI poi = new POI();
			 System.out.println("Please enter the name of the POI you would like to delete keywords from:");
			 while ((poiName = in.readLine()) == null || poiName.length() == 0);
			 validPoi = poi.getPOI(poiName, con.stmt);
			 while(!validPoi)
			 {
				 System.out.println("That POI does not exist in our system.  Please try again:");
				 while ((poiName = in.readLine()) == null || poiName.length() == 0);
				 validPoi = poi.getPOI(poiName, con.stmt);
			 }
			 if(validPoi)
			 {
				 poi.name = poiName;
				 while(!validPoiId)
				 {
					 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you would like to delete keywords from."); 
					 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
					 try {
		       			 poi.poi_id = Integer.parseInt(poi_id);
		       			 validPoiId = true;
		       		 } catch(NumberFormatException e) {
		       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
		       		 }
				 }
				 poi = poi.getPOIForUpdate(poi.poi_id, con.stmt);
				 String choice;
				 int c = 0;
				 Boolean deleting = true;
				 while(deleting)
				 {
					 System.out.println("The keywords associated with this POI are listed below:");
					 // Show user list of keywords
					 Words words = new Words();
					 words.poi_id = poi.poi_id;
					 System.out.println("");
					 words.getWords(con.stmt);
					 System.out.println("Please enter the keyword you would like disassociated with the POI:");
					 while ((words.word = in.readLine()) == null || words.word.length() == 0);
					 if(words.deleteWord(con.stmt))
					 {
						 System.out.println("Would you like to delete another?");
						 System.out.println("1: Yes");
	   					 System.out.println("2: No");
	   					 System.out.println("Enter your choice below:");
   					 while ((choice = in.readLine()) == null || choice.length() == 0);
   					 try {
   			   			 c = Integer.parseInt(choice);
   			   			 if(c > 2 || c < 1)
   			   				 throw new NumberFormatException();
   			   			 if(c == 2)
   			   				 deleting=false;
   			   		 } catch(NumberFormatException e) {
   			   			 System.out.println("Your input was not an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
   			   		 }
					}
				 }
			 }
		}
		catch(Exception e) {}
	}
	
	public static void addPictures(Connector con)
	{
		try
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String poiName;
			 String poi_id;
			 Boolean validPoi = false;
			 Boolean validPoiId = false;
			 POI poi = new POI();
			 System.out.println("Please enter the name of the POI you would like to add pictures to:");
			 while ((poiName = in.readLine()) == null || poiName.length() == 0);
			 validPoi = poi.getPOI(poiName, con.stmt);
			 while(!validPoi)
			 {
				 System.out.println("That POI does not exist in our system.  Please try again:");
				 while ((poiName = in.readLine()) == null || poiName.length() == 0);
				 validPoi = poi.getPOI(poiName, con.stmt);
			 }
			 if(validPoi)
			 {
				 poi.name = poiName;
				 while(!validPoiId)
				 {
					 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you would like to add pictures to."); 
					 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
					 try {
		       			 poi.poi_id = Integer.parseInt(poi_id);
		       			 validPoiId = true;
		       		 } catch(NumberFormatException e) {
		       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
		       		 }
				 }
				 poi = poi.getPOIForUpdate(poi.poi_id, con.stmt);
				 String choice;
				 int c = 0;
				 System.out.println("Would you like to enter any pictures associated with this POI?");
				 System.out.println("1: Yes");
				 System.out.println("2: No");
				 System.out.println("Enter your choice below:");
				 Boolean validChoice = false;
				 choice = "";
				 c = 0;
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c > 2 || c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
			   		 }
				 }
				 while(c == 1)
				 {
					 System.out.println("The pictures associated with this POI are listed below:");
					 // Show user list of keywords
					 Picture pictures = new Picture();
					 pictures.poi_id = poi.poi_id;
					 System.out.println("");
					 pictures.getPictures(con.stmt);
					 
					 Picture picture = new Picture();
					 System.out.println("Please input a name for this picture below:");
					 while ((picture.name = in.readLine()) == null || picture.name.length() == 0);
					 System.out.println("Please input an image path (url) for this picture below:");
					 while ((picture.image_path = in.readLine()) == null || picture.image_path.length() == 0);
					 picture.poi_id = poi.poi_id;
					 picture.insertPicture(con.stmt);
					 
					 System.out.println("Would you like to enter any more pictures associated with this POI?");
					 System.out.println("1: Yes");
					 System.out.println("2: No");
					 System.out.println("Enter your choice below:");
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c > 2 || c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
			   		 }
					 
				 }
			 }
		}
		catch(Exception e) {}
	}
	
	public static void deletePictures(Connector con)
	{
		try
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String poiName;
			 String poi_id;
			 Boolean validPoi = false;
			 Boolean validPoiId = false;
			 POI poi = new POI();
			 System.out.println("Please enter the name of the POI you would like to delete pictures from:");
			 while ((poiName = in.readLine()) == null || poiName.length() == 0);
			 validPoi = poi.getPOI(poiName, con.stmt);
			 while(!validPoi)
			 {
				 System.out.println("That POI does not exist in our system.  Please try again:");
				 while ((poiName = in.readLine()) == null || poiName.length() == 0);
				 validPoi = poi.getPOI(poiName, con.stmt);
			 }
			 if(validPoi)
			 {
				 poi.name = poiName;
				 while(!validPoiId)
				 {
					 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you would like to delete pictures from."); 
					 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
					 try {
		       			 poi.poi_id = Integer.parseInt(poi_id);
		       			 validPoiId = true;
		       		 } catch(NumberFormatException e) {
		       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
		       		 }
				 }
				 poi = poi.getPOIForUpdate(poi.poi_id, con.stmt);
				 String choice;
				 int c = 0;
				 Boolean deleting = true;
				 while(deleting)
				 {
					 System.out.println("The pictures associated with this POI are listed below:");
					 // Show user list of keywords
					 Picture picture = new Picture();
					 picture.poi_id = poi.poi_id;
					 System.out.println("");
					 picture.getPictures(con.stmt);
					 Boolean validPid = false;
					 String pid;
					 while(!validPid)
					 {
						 System.out.println("Please input the Picture Id (the far left integer of each line item) of the picture you would like to delete."); 
						 while ((pid = in.readLine()) == null || pid.length() == 0);
						 try {
			       			 picture.pic_id = Integer.parseInt(pid);
			       			 validPid = true;
			       		 } catch(NumberFormatException e) {
			       			 System.out.println("You didn't input the Picture Id as an integer. Please refer to the printed list and try again:");
			       		 }
					 }
					 if(picture.deletePicture(con.stmt, picture.pic_id))
					 {
						 System.out.println("Would you like to delete another?");
						 System.out.println("1: Yes");
	   					 System.out.println("2: No");
	   					 System.out.println("Enter your choice below:");
   					 while ((choice = in.readLine()) == null || choice.length() == 0);
   					 try {
   			   			 c = Integer.parseInt(choice);
   			   			 if(c > 2 || c < 1)
   			   				 throw new NumberFormatException();
   			   			 if(c == 2)
   			   				 deleting=false;
   			   		 } catch(NumberFormatException e) {
   			   			 System.out.println("Your input was not an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
   			   		 }
					}
				 }
			 }
		}
		catch(Exception e) {}
	}
	
	public static User systemLogin(Connector con)
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			User user = new User();
			System.out.println("Username:");
	   		 while ((user.login = in.readLine()) == null || user.login.length() == 0);
	   		 System.out.println("Password:");
	   		 while ((user.password = in.readLine()) == null || user.password.length() == 0);
	   		 user = user.getUser(con.stmt);
	   		 if(user != null)
	   			 return user;
		}
		catch(Exception e)
		{
		}
		return null;
	}
	
	public static Boolean registerUser(Connector con)
	{
		Boolean output = false;
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			User user = new User();
			
	   		 System.out.println("Please enter a unique login name:");
	   		 while ((user.login = in.readLine()) == null || user.login.length() == 0);
	   		 while(!user.uniqueLogin(con.stmt)) {
	   			 System.out.println("The login name you selected has already been used.  Please try another:");
	   			 while ((user.login = in.readLine()) == null || user.login.length() == 0);
	   		 }
	   		 System.out.println("Please enter your password:");
	   		 while ((user.password = in.readLine()) == null || user.password.length() == 0);
	   			 System.out.println("Please enter your first name:");
	   		 while ((user.first_name = in.readLine()) == null || user.first_name.length() == 0);
	   			 System.out.println("Please enter your last name:");
	   		 while ((user.last_name = in.readLine()) == null || user.last_name.length() == 0);
	   			 System.out.println("Please enter your street address:");
	   		 while ((user.street = in.readLine()) == null || user.street.length() == 0);
	   			 System.out.println("Please enter your city:");
	   		 while ((user.city = in.readLine()) == null || user.city.length() == 0);
	   			 System.out.println("Please enter your state with two letters e.g. Utah = UT:");
	   		 while ((user.state = in.readLine()) == null || user.state.length() == 0 || user.state.length() > 2);
	   			 System.out.println("Please enter your zipcode as 5 digits:");
	   		 while ((user.zip_code = in.readLine()) == null || user.zip_code.length() == 0 || user.zip_code.length() > 5);
	   			 System.out.println("Please enter your telephone number as 10 digits (do not include parentheses or dashes e.g. 8011234567):");
	   		 while ((user.tel_num = in.readLine()) == null || user.tel_num.length() == 0 || user.tel_num.length() > 10);
	   		 
	   		 output = user.insertUser(con.stmt);
			
		}
		catch(Exception e)
		{
		}
		return output;
	}
	
	public static void recordFavorite(Connector con, User user)
	{
		// Ask the user for the poi they want
		try
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String poiName;
			 String poi_id;
			 Boolean validPoi = false;
			 Boolean validPoiId = false;
			 POI poi = new POI();
			 System.out.println("Please enter the name of the POI you would like to favorite:");
			 while ((poiName = in.readLine()) == null || poiName.length() == 0);
			 validPoi = poi.getPOI(poiName, con.stmt);
			 while(!validPoi)
			 {
				 System.out.println("That POI does not exist in our system.  Please try again:");
				 while ((poiName = in.readLine()) == null || poiName.length() == 0);
				 validPoi = poi.getPOI(poiName, con.stmt);
			 }
			 if(validPoi)
			 {
				 poi.name = poiName;
				// Get the poi ID
				 while(!validPoiId)
				 {
					 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you would like to favorite from the above list."); 
					 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
					 try {
		       			 poi.poi_id = Integer.parseInt(poi_id);
		       			 validPoiId = true;
		       		 } catch(NumberFormatException e) {
		       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
		       		 }
				 }
				// Insert into favorites with user login and poi ID
				Favorite fav = new Favorite();
				fav.login = user.login;
				fav.poi_id = poi.poi_id;
				fav.insertFavorite(con.stmt);
			 }
			 
		}
		catch(Exception e){}
		
		
	}
	
	public static void recordFeedback(Connector con, User user)
	{
		// Ask the user for the poi they want
				try
				{ 
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					String poiName;
					 String poi_id;
					 Boolean validPoi = false;
					 Boolean validPoiId = false;
					 POI poi = new POI();
					 System.out.println("Please enter the name of the POI you would like to provide feedback on:");
					 while ((poiName = in.readLine()) == null || poiName.length() == 0);
					 validPoi = poi.getPOIUserHasNotFeedbacked(poiName, con.stmt, user);
					 Boolean exit = false;
					 while(!validPoi)
					 {
						 System.out.println("That POI does not exist in our system or");
						 System.out.println("you have already provided feedback to all POIs with that name in our system.");
						 System.out.println("Please try again or press 0 to exit:");
						 while ((poiName = in.readLine()) == null || poiName.length() == 0);
						 if(poiName.equals("0"))
						 {
							 validPoi = true;
							 exit = true;
						 }
						 else
							 validPoi = poi.getPOI(poiName, con.stmt);
					 }
					 if(!exit)
					 {
						 if(validPoi)
						 {
							 poi.name = poiName;
							 Feedback tempFeed = new Feedback();
							 Hashtable<Integer, Integer> feedbackIds = tempFeed.getFeedbackPOIIds(con.stmt, user);
							// Get the poi ID
							 while(!validPoiId)
							 {
								 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you would like to provide feedback on from the above list."); 
								 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
								 try {
					       			 poi.poi_id = Integer.parseInt(poi_id);
					       			 if(feedbackIds.contains(poi.poi_id))
					       				 throw new NumberFormatException();
					       			 validPoiId = true;
					       		 } catch(NumberFormatException e) {
					       			 System.out.println("You didn't input the POI Id as an integer or you inputted an Id you have already reviewed. Please refer to the printed list and try again.");
					       		 }
							 }
							// Insert into favorites with user login and poi ID
							Feedback feed = new Feedback();
							feed.login = user.login;
							feed.poi_id = poi.poi_id;
							Boolean validDate = false;
							Boolean validScore = false;
							String date;
							String score;
							System.out.println("Please input a short review of the indicated POI:");
							 while ((feed.text = in.readLine()) == null || feed.text.length() == 0);
							 System.out.println("Please input a score for the POI from 0 to 10 as an integer:");
							 while(!validScore)
							 {
								 while ((score = in.readLine()) == null || score.length() == 0);
								 try {
						   			 feed.score = Integer.parseInt(score);
						   			 if(feed.score > 10 || feed.score < 0)
						   				 throw new NumberFormatException();
						   			 validScore = true;
						   		 } catch(NumberFormatException e) {
						   			 System.out.println("You didn't input the score as an integer or the integer you entered is greater than 10 or less than 0. Please try again:");
						   		 }
							 }
							 System.out.println("Please enter the date you visited or today's date in this format YYYY-MM-DD :");
							 while(!validDate)
							 {
								 while ((date = in.readLine()) == null || date.length() == 0);
								 try {
						   			 feed.date = java.sql.Date.valueOf(date);
						   			 validDate = true;
						   		 } catch(IllegalArgumentException e) {
						   			 System.out.println("You inputted the date in the wrong format. Please try again YYYY-MM-DD:");
						   		 }
							 }
							 
							 feed.insertFeedback(con.stmt);
						 }
					 }
					 
				}
				catch(Exception e){}
	}

	public static void recordUsefulness(Connector con, User user)
	{
		// Which POI would you like to record the usefulness of a feedback on?
		try
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String poiName;
			 String poi_id;
			 Boolean validPoi = false;
			 Boolean validPoiId = false;
			 POI poi = new POI();
			 Feedback feed = new Feedback();
			 Boolean containsFeedback = false;
			 Boolean exit = false;
			 while(!containsFeedback && !exit)
			 {
				 validPoi = false;
				 validPoiId = false;
				 System.out.println("Please enter the name of the POI you would like to provide a usefulness rating on its feedback or press 0 to exit:");
				 while ((poiName = in.readLine()) == null || poiName.length() == 0);
				 if(poiName.equals("0"))
					 exit = true;
				 else
					 validPoi = poi.getPOI(poiName, con.stmt);
				 while(!validPoi && !exit)
				 {
					 System.out.println("That POI does not exist in our system.  Please try again:");
					 while ((poiName = in.readLine()) == null || poiName.length() == 0);
					 validPoi = poi.getPOI(poiName, con.stmt);
				 }
				 if(validPoi)
				 {
					 poi.name = poiName;
					// Get the poi ID
					 while(!validPoiId)
					 {
						 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you visited from the above list."); 
						 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
						 try {
			       			 poi.poi_id = Integer.parseInt(poi_id);
			       			 feed.poi_id = poi.poi_id;
			       			 validPoiId = true;
			       		 } catch(NumberFormatException e) {
			       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
			       		 }
					 }
					 
					 ArrayList<Feedback> feedList = feed.getFeedback(con.stmt, user);
					 Boolean validFeedback = false;
					 String feedId;
					 int feedNum;
					 Boolean feedbackExists = feedList.size() > 0;
					 if(feedbackExists)
					 {
						 while(!validFeedback)
						 {
							 System.out.println("Please input the feedback id (the far left integer of each line item) of the feedback you would like to rate from the above list."); 
					
							 while ((feedId = in.readLine()) == null || feedId.length() == 0);
							 try {
				       			 feedNum = Integer.parseInt(feedId);
				       			 feed = feedList.get(feedNum);
				       			 validFeedback = true;
				       		 } catch(NumberFormatException e) {
				       			 System.out.println("You didn't input the feedback Id as an integer. Please refer to the printed list and try again:");
				       		 }
						 }
						 Boolean validRating = false;
						 Rating rating = new Rating();
						 String tempRating;
						 System.out.println("Please input a rating for the feedback from 0 to 2 as an integer:");
						 while(!validRating)
						 {
							 while ((tempRating = in.readLine()) == null || tempRating.length() == 0);
							 try {
					   			 rating.use_val = Integer.parseInt(tempRating);
					   			 if(rating.use_val > 2 || rating.use_val < 0)
					   				 throw new NumberFormatException();
					   			 validRating = true;
					   		 } catch(NumberFormatException e) {
					   			 System.out.println("You didn't input the score as an integer or the integer you entered is greater than 2 or less than 0. Please try again:");
					   		 }
						 }
						 
						 // Put all info into rating
						 rating.feedback_login = feed.login;
						 rating.poi_id = feed.poi_id;
						 rating.rater_login = user.login;
						 rating.insertRating(con.stmt);
						 containsFeedback=true;
					 }
					 else
					 {
						 System.out.println("There are no feedbacks for that POI that you may provide a usefulness rating on at this time.");
					 }
					 
				 }
			 }
		}
		catch(Exception e) {}
		
		// Retrieve feedbacks for that POI
		// Which feedback would you like to record usefulness on?
		// Insert usefulness
	}
	
	public static void poiBrowsing(Connector con, User user)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		POIBrowsing browser = new POIBrowsing();
		try
		{
			// Price
			String choice;
			 int c = 0;
			 System.out.println("Would you like to search by POI price?");
			 System.out.println("1: Yes");
			 System.out.println("2: No");
			 System.out.println("Enter your choice below:");
			 Boolean validChoice = false;
			 while(!validChoice)
			 {
				 while ((choice = in.readLine()) == null || choice.length() == 0);
				 try {
		   			 c = Integer.parseInt(choice);
		   			 if(c > 2 || c < 1)
		   				 throw new NumberFormatException();
		   			 validChoice = true;
		   		 } catch(NumberFormatException e) {
		   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
		   		 }
			 }
			 if(c == 1)
			 {
				 // Reset choice parameters
				 validChoice = false;
				 c = 0;
				 browser.searchPrice = true;
				 // What's the highest price?
				 System.out.println("What's the maximum price (please enter as an integer)?");
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c < 0)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   			 browser.maxPrice = c;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is less than 0. Please try again:");
			   		 }
				 }
				 // Reset choice parameters
				 validChoice = false; 
				 c = 0;
				// What's the lowest price?
				 System.out.println("What's the minimum price (please enter as an integer)?");
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c < 0 || c > browser.maxPrice)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   			 browser.minPrice = c;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is less than 0 or greater than the previously indicated max. Please try again:");
			   		 }
				 }
			 }
			 
			 // Address
			 c = 0;
			 System.out.println("Would you like to search by POI address?");
			 System.out.println("1: Yes");
			 System.out.println("2: No");
			 System.out.println("Enter your choice below:");
			 validChoice = false;
			 while(!validChoice)
			 {
				 while ((choice = in.readLine()) == null || choice.length() == 0);
				 try {
		   			 c = Integer.parseInt(choice);
		   			 if(c > 2 || c < 1)
		   				 throw new NumberFormatException();
		   			 validChoice = true;
		   		 } catch(NumberFormatException e) {
		   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
		   		 }
			 }
			 if(c == 1)
			 {
				 c = 0;
				 System.out.println("Would you like to search by City?");
				 System.out.println("1: Yes");
				 System.out.println("2: No");
				 System.out.println("Enter your choice below:");
				 validChoice = false;
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c > 2 || c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
			   		 }
				 }
				 if(c == 1)
				 {
					 // Reset choice parameters
					 validChoice = false;
					 c = 0;
					 browser.searchCity = true;
					 // What city?
					 System.out.println("What city would you like to search in?");
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 browser.city = choice;
				 }
				 
				 c = 0;
				 System.out.println("Would you like to search by State?");
				 System.out.println("1: Yes");
				 System.out.println("2: No");
				 System.out.println("Enter your choice below:");
				 validChoice = false;
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c > 2 || c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
			   		 }
				 }
				 if(c == 1)
				 {
					 // Reset choice parameters
					 validChoice = false; 
					 c = 0;
					// What state?
					 System.out.println("What state would you like to search in? (please indicate with 2 letter abbreviation, e.g. Utah = UT)");
					 while ((choice = in.readLine()) == null || choice.length() == 0 || choice.length() > 2);
					 browser.state = choice;
				 }
				 
			 }
			 
			// Keywords
			 c = 0;
			 System.out.println("Would you like to search by POI keywords?");
			 System.out.println("1: Yes");
			 System.out.println("2: No");
			 System.out.println("Enter your choice below:");
			 validChoice = false;
			 while(!validChoice)
			 {
				 while ((choice = in.readLine()) == null || choice.length() == 0);
				 try {
		   			 c = Integer.parseInt(choice);
		   			 if(c > 2 || c < 1)
		   				 throw new NumberFormatException();
		   			 validChoice = true;
		   		 } catch(NumberFormatException e) {
		   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
		   		 }
			 }
			 if(c == 1)
			 {
				 browser.searchKeyword = true;
				 Boolean enteringKeywords = true;
				 while(enteringKeywords)
				 {
					 // Reset choice parameters
					 validChoice = false;
					 c = 0;
					 
					// What keyword?
					 System.out.println("What keyword would you like to search with?");
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 browser.keywords.add(choice);
					 
					 System.out.println("Would you like to add another keyword to search by?");
					 System.out.println("1: Yes");
					 System.out.println("2: No");
					 System.out.println("Enter your choice below:");
					 while(!validChoice)
					 {
						 while ((choice = in.readLine()) == null || choice.length() == 0);
						 try {
				   			 c = Integer.parseInt(choice);
				   			 if(c > 2 || c < 1)
				   				 throw new NumberFormatException();
				   			 validChoice = true;
				   		 } catch(NumberFormatException e) {
				   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
				   		 }
					 }
					 if(c == 2)
						 enteringKeywords = false;
				 }	  
			 } 
			 
			// Category
			 c = 0;
			 System.out.println("Would you like to search by POI category?");
			 System.out.println("1: Yes");
			 System.out.println("2: No");
			 System.out.println("Enter your choice below:");
			 validChoice = false;
			 while(!validChoice)
			 {
				 while ((choice = in.readLine()) == null || choice.length() == 0);
				 try {
		   			 c = Integer.parseInt(choice);
		   			 if(c > 2 || c < 1)
		   				 throw new NumberFormatException();
		   			 validChoice = true;
		   		 } catch(NumberFormatException e) {
		   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
		   		 }
			 }
			 if(c == 1)
			 {
				 // Reset choice parameters
				 validChoice = false;
				 c = 0;
				 browser.searchCategory = true;
				 // What category?
				 System.out.println("What category would you like to search by?");
				 while ((choice = in.readLine()) == null || choice.length() == 0);
				 browser.category = choice;				 
			 }
			 
			// Order
			 c = 0;
			 System.out.println("Would you like to order the results?");
			 System.out.println("1: Yes");
			 System.out.println("2: No");
			 System.out.println("Enter your choice below:");
			 validChoice = false;
			 while(!validChoice)
			 {
				 while ((choice = in.readLine()) == null || choice.length() == 0);
				 try {
		   			 c = Integer.parseInt(choice);
		   			 if(c > 2 || c < 1)
		   				 throw new NumberFormatException();
		   			 validChoice = true;
		   		 } catch(NumberFormatException e) {
		   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
		   		 }
			 }
			 if(c == 1)
			 {
				 // Reset choice parameters
				 validChoice = false;
				 c = 0;
				 browser.sorted = true;
				 // What order?
				 System.out.println("How would you like to order the results?");
				 System.out.println("1: By Price");
				 System.out.println("2: By Average Score of Feedbacks");
				 System.out.println("3: By Average Score of Feedbacks by Users you trust");
				 System.out.println("Enter your choice below:");
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c > 3 || c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 3 or less than 1. Please try again:");
			   		 }
				 }
				 if(c == 1)
					 browser.sortedPrice = true;
				 else if(c == 2)
					 browser.sortedFeedbacks = true;
				 else
					 browser.sortedTrustedFeedbacks = true;
				 
			 }
			 
			 // Complete Browsing
			 browser.user = user;
			 browser.browse(con.stmt);
		}
		catch(Exception e) {
			System.out.println("POI Browsing failed.  Please try again.");
		}
	}
	
	public static void usefulFeedbacks(Connector con, User user)
	{
		try
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String poiName;
			 String poi_id;
			 Boolean validPoi = false;
			 Boolean validPoiId = false;
			 POI poi = new POI();
			 System.out.println("Please enter the name of the POI you would like to find the most useful feedbacks for:");
			 while ((poiName = in.readLine()) == null || poiName.length() == 0);
			 validPoi = poi.getPOI(poiName, con.stmt);
			 while(!validPoi)
			 {
				 System.out.println("That POI does not exist in our system.  Please try again:");
				 while ((poiName = in.readLine()) == null || poiName.length() == 0);
				 validPoi = poi.getPOI(poiName, con.stmt);
			 }
			 if(validPoi)
			 {
				 poi.name = poiName;
				// Get the poi ID
				 while(!validPoiId)
				 {
					 System.out.println("Please input the POI Id (the far left integer of each line item) of the POI you want the top feedbacks for."); 
					 while ((poi_id = in.readLine()) == null || poi_id.length() == 0);
					 try {
		       			 poi.poi_id = Integer.parseInt(poi_id);
		       			 validPoiId = true;
		       		 } catch(NumberFormatException e) {
		       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
		       		 }
				 }
				
				// Get the limit
				 Boolean validLimit = false;
				 String limit;
				 int limit_num = 0;
				 while(!validLimit)
				 {
					 System.out.println("Please input the number of top feedbacks you want:"); 
					 while ((limit = in.readLine()) == null || limit.length() == 0);
					 try {
		       			 limit_num = Integer.parseInt(limit);
		       			 validLimit = true;
		       		 } catch(NumberFormatException e) {
		       			 System.out.println("You didn't input the POI Id as an integer. Please refer to the printed list and try again:");
		       		 }
				 }
				 
				 Feedback feed = new Feedback();
				 feed.topUsefulFeedbacks(con.stmt, poi.poi_id, limit_num);
				 
			 }
			 
		}
		catch(Exception e){}
	}
	
	public static void twoDegrees(Connector con, User user)
	{
		System.out.println("This is the list of users you are 2 degrees separated from (login, first name, last name):");
		user.twoDegreesSeparation(con.stmt);
	}
	
	public static void statistics(Connector con, User user)
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String choice;
			 int c = 0;
			 System.out.println("Which statistics would you like?");
			 System.out.println("1: Most Popular POIs");
			 System.out.println("2: Most Expensive POIs");
			 System.out.println("3: Most Highly Rated POIs");
			 System.out.println("Enter your choice below:");
			 Boolean validChoice = false;
			 while(!validChoice)
			 {
				 while ((choice = in.readLine()) == null || choice.length() == 0);
				 try {
		   			 c = Integer.parseInt(choice);
		   			 if(c > 3 || c < 1)
		   				 throw new NumberFormatException();
		   			 validChoice = true;
		   		 } catch(NumberFormatException e) {
		   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 3 or less than 1. Please try again:");
		   		 }
			 }
			 if(c == 1)
			 {
				 //Reset values
				 c = 0;
				 validChoice = false;
				 System.out.println("How many of the most popular POIs would you like to view?");
				 System.out.println("Enter your choice as an integer below:");
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is less than 1. Please try again:");
			   		 }
				 }
				 System.out.println("The most popular POIs are:");
				 POI poi = new POI();
				 poi.mostPopularPois(con.stmt, c);
				 c = 0;
			 }
			 else if(c == 2)
			 {
				//Reset values
				 c = 0;
				 validChoice = false;
				 System.out.println("How many of the most expensive POIs would you like to view?");
				 System.out.println("Enter your choice as an integer below:");
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is less than 1. Please try again:");
			   		 }
				 }
				 System.out.println("The most expensive POIs are:");
				 POI poi = new POI();
				 poi.mostExpensivePois(con.stmt, c);
				 c = 0;
			 }
			 else
			 {
				//Reset values
				 c = 0;
				 validChoice = false;
				 System.out.println("How many of the most highly rated POIs would you like to view?");
				 System.out.println("Enter your choice as an integer below:");
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is less than 1. Please try again:");
			   		 }
				 }
				 System.out.println("The most highly rated POIs are:");
				 POI poi = new POI();
				 poi.mostHighlyRatedPois(con.stmt, c);
				 c = 0;
			 }
		}
		catch(Exception e) {}
	}
	
	public static void userAwards(Connector con, User user)
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String choice;
			 int c = 0;
			 System.out.println("Which user awards would you like?");
			 System.out.println("1: Most Trusted Users");
			 System.out.println("2: Most Useful Users");
			 System.out.println("Enter your choice below:");
			 Boolean validChoice = false;
			 while(!validChoice)
			 {
				 while ((choice = in.readLine()) == null || choice.length() == 0);
				 try {
		   			 c = Integer.parseInt(choice);
		   			 if(c > 2 || c < 1)
		   				 throw new NumberFormatException();
		   			 validChoice = true;
		   		 } catch(NumberFormatException e) {
		   			 System.out.println("You didn't input your choice as an integer or the integer you entered is greater than 2 or less than 1. Please try again:");
		   		 }
			 }
			 if(c == 1)
			 {
				 //Reset values
				 c = 0;
				 validChoice = false;
				 System.out.println("How many of the most trusted users would you like to view?");
				 System.out.println("Enter your choice as an integer below:");
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is less than 1. Please try again:");
			   		 }
				 }
				 user.mostTrustedUsers(con.stmt, c);
				 c = 0;
			 }
			 else
			 {
				//Reset values
				 c = 0;
				 validChoice = false;
				 System.out.println("How many of the most useful users would you like to view?");
				 System.out.println("Enter your choice as an integer below:");
				 while(!validChoice)
				 {
					 while ((choice = in.readLine()) == null || choice.length() == 0);
					 try {
			   			 c = Integer.parseInt(choice);
			   			 if(c < 1)
			   				 throw new NumberFormatException();
			   			 validChoice = true;
			   		 } catch(NumberFormatException e) {
			   			 System.out.println("You didn't input your choice as an integer or the integer you entered is less than 1. Please try again:");
			   		 }
				 }
				 System.out.println("The most useful users are:");
				 user.mostUsefulUsers(con.stmt, c);
				 c = 0;
			 }
		}
		catch(Exception e) {}
	}
	
	public static void recordTrust(Connector con, User user)
	{
		try
		{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			User trustUser = new User();
			// Ask for the first name and last name of the desired user
			System.out.println("Please enter the first name of the user you would like to trust:");
	  		 while ((trustUser.first_name = in.readLine()) == null || trustUser.first_name.length() == 0);
	  			 System.out.println("Please enter the last name of the user you would like to trust:");
	  		 while ((trustUser.last_name = in.readLine()) == null || trustUser.last_name.length() == 0);
			// Display the users with those names
	  		 trustUser.getUserByName(con.stmt, user);
			// Ask for the login
	  		System.out.println("Please enter the login name of the user you would like to trust from the above list (login name is on the far left of each line item):");
	  		 while ((trustUser.login = in.readLine()) == null || trustUser.login.length() == 0);
			// Ask for a trust rating
	  		 Trust tempTrust = new Trust();
	  		 tempTrust.rated_login = trustUser.login;
	  		 tempTrust.rater_login = user.login;
	  		 System.out.println("Please indicate your trust rating for this user:");
	  		 System.out.println("-1: Don't trust");
	  		 System.out.println("1: Trust");
	  		 System.out.println("Enter your choice as an integer from the above options:");
	  		 String trust;
	  		 Boolean validTrust = false;
	  		 while(!validTrust)
			 {
				 while ((trust = in.readLine()) == null || trust.length() == 0);
				 try {
		   			 tempTrust.trust_val = Integer.parseInt(trust);
		   			 if(tempTrust.trust_val > 1 || tempTrust.trust_val < -1 || tempTrust.trust_val == 0)
		   				 throw new NumberFormatException();
		   			 validTrust = true;
		   		 } catch(NumberFormatException e) {
		   			 System.out.println("You didn't input the score as an integer or the integer you entered is greater than 1 or less than 0. Please try again:");
		   		 }
			 }
	  		 tempTrust.insertTrust(con.stmt);
		}
		catch(Exception e){}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to UTrack");
		Connector con=null;
		String choice;
        String login;
        String password;
        Boolean validUser = false;
        String sql=null;
        int c=0;
         try
		 {
			 	 con= new Connector();
	             System.out.println ("Database connection established");
	         
	             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	             
	             while(!validUser)
	             {
	            	 displayMenu();
	            	 while ((choice = in.readLine()) == null || choice.length() == 0);
	            	 try{
	            		 c = Integer.parseInt(choice);
	            	 }catch (Exception e)
	            	 {
	            		 continue;
	            	 }
	            	 if (c<1 | c>3)
	            		 continue;
	            	 // Login
	            	 if (c==1)
	            	 {
	            		 User user = systemLogin(con);
	            		 if(user != null)
	            		 {
	            			 System.out.println("Login successful.");
	            			 validUser = true;
	            			 while(validUser)
	            			 {
	            				 displayUserMenu(user.login, user.admin);
	        	            	 while ((choice = in.readLine()) == null || choice.length() == 0);
	        	            	 try{
	        	            		 c = Integer.parseInt(choice);
	        	            		 
	        	            		 // Visit a POI
	        	            		 if(c == 1)
	        	            		 {
	        	            			 List<Visit> visits = new ArrayList<Visit>();
	        	            			 Boolean submitVisits = true;
	        	            			 while(submitVisits)
	        	            			 {
	        	            				 Visit tempVisit = recordVisit(con, user);
	        	            				 if(tempVisit != null)
	        	            					 visits.add(tempVisit);
	        	            				 submitVisits = displayVisitMenu(con, user, visits);
	        	            			 }
	        	            		 }
	        	            		 else if(c == 2)
	        	            		 {
	        	            			 recordFavorite(con, user);
	        	            		 }
	        	            		 else if(c == 3)
	        	            		 {
	        	            			 recordFeedback(con,user);
	        	            		 }
	        	            		 else if(c == 4)
	        	            		 {
	        	            			 recordUsefulness(con, user);
	        	            		 }
	        	            		 else if(c == 5)
	        	            		 {
	        	            			 recordTrust(con, user);
	        	            		 }
	        	            		 else if(c == 6)
	        	            		 {
	        	            			 poiBrowsing(con,user);
	        	            		 }
	        	            		 else if(c == 7)
	        	            		 {
	        	            			 usefulFeedbacks(con,user);
	        	            		 }
	        	            		 else if(c == 8)
	        	            		 {
	        	            			 twoDegrees(con, user);
	        	            		 }
	        	            		 else if(c == 9)
	        	            		 {
	        	            			 statistics(con, user);
	        	            		 }
	        	            		 else if(c == 10)
	        	            		 {
	        	            			 System.out.println("Remember to give me an A!");
	        		            		 con.stmt.close(); 
	        		        
	        		            		 break;
	        	            		 }
	        	            		 else if(c == 11 && user.admin.equals("1"))
	        	            		 {
	        	            			 userAwards(con, user);
	        	            		 }
	        	            		 else if(c == 12 && user.admin.equals("1"))
	        	            		 {
	        	            			 newPOI(con, user);
	        	            		 }
	        	            		 else if(c == 13 && user.admin.equals("1"))
	        	            		 {
	        	            			 updatePOI(con, user);
	        	            		 }
	        	            		 else if(c == 14 && user.admin.equals("1"))
	        	            		 {
	        	            			 addKeywords(con);
	        	            		 }
	        	            		 else if(c == 15 && user.admin.equals("1"))
	        	            		 {
	        	            			 deleteKeywords(con);
	        	            		 }
	        	            		 else if(c == 16 && user.admin.equals("1"))
	        	            		 {
	        	            			 addPictures(con);
	        	            		 }
	        	            		 else if(c == 17 && user.admin.equals("1"))
	        	            		 {
	        	            			 deletePictures(con);
	        	            		 }
	        	            	 }
	        	            	 catch (Exception e)
	        	            	 {
	        	            		 continue;
	        	            	 }
	            			 }
	            		 }
	            		 else
	            		 {
	            			 System.out.println("Invalid login name or password.  Please try again or register as a new user.");
	            		 }
	            	 }
	            	 // Register a New User
	            	 else if (c==2)
	            	 {	 
	            		 if(registerUser(con))
	            		 {
	            			 System.out.println("You are now registered!  Please login on main menu.");
	            		 }
	            		 else
	            		 {
	            			 System.out.println("Registration failed.  Please try again.");
	            		 }	 
	            	 }
	            	 else
	            	 {   
	            		 System.out.println("Remember to give me an A!");
	            		 con.stmt.close(); 
	        
	            		 break;
	            	 }
	             }
		 }
         catch (Exception e)
         {
        	 e.printStackTrace();
        	 System.err.println ("Either connection error or query execution error!");
         }
         finally
         {
        	 if (con != null)
        	 {
        		 try
        		 {
        			 con.closeConnection();
        			 System.out.println ("Database connection terminated");
        		 }
        	 
        		 catch (Exception e) { /* ignore close errors */ }
        	 }	 
         }
	}
}
