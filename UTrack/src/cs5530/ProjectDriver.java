package cs5530;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Alex Smith u0814844
public class ProjectDriver 
{
	//TODO put password in readme
	public static final String ADMIN_PASS = "admin1";
	
	String classLogin = "";
	
	public ProjectDriver()
	{
		
		Connector con=null;
		String choice;
        String password, login, name, phoneNumber, address, city, state, URL, yearEstablished, hours, category, otherUser, trustVal, PID, score, review, FID, adminAttempt, cost, num, minPrice, maxPrice;
        String sql=null;
        int c=0;
        
        try
        {
        	con= new Connector();
            System.out.println ("Database connection established");
        
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            
            while(true)
            {
            	displayMenu();
            	while ((choice = in.readLine()) == null && choice.length() == 0);
           	 	try
           	 	{
           	 		c = Integer.parseInt(choice);
           	 	}
           	 	catch (Exception e)
           	 	{ 
           	 		continue;
           	 	}
	           	if (c<1 | c>15)
	           	{
	           		continue;
	           	}
	           	//User selected user registration
           		if (c==1)
           		{
	           		//INPUT
           			login = getInputString(in, "please enter a Login:");
	           		password = getInputString(in, "please enter a password:");
	           		name = getInputString(in, "please enter a name:");
	           		//check phone number is actually a number
	           		phoneNumber = "";
	           		while(true)
	           		{
	           			phoneNumber = getInputString(in, "please enter a phone number:");
		           		if(valueIsNumber(phoneNumber))
		           		{
		           			break;
		           		}
		           		else
		           		{
		           			System.out.println("error not a vaild phone number (no dashes or spaces):");
		           		}
	           		}
	           		
	           		adminAttempt = getInputString(in, "Enter secret admin password to declare user admin, leave blank if not admin:");
	           		boolean admin = false;
	           		if(adminAttempt.equals(ADMIN_PASS))
	           		{
	           			admin = true;
	           		}
	           		
	           		User user = new User();
	           		System.out.println(user.createUser(login, password, name, phoneNumber, admin, con.stmt));
           		}
           		else if (c==2)
           		{
           			if(userLogin(in, con.stmt, true))
           			{
           				ArrayList<Visit> v = new ArrayList<Visit>();
           				login = classLogin;
           				
           				System.out.println("You may enter as many visit as you like. They will be tagged with today's date. Type done into the PID field to review your entries");
           				
           				while(true)
           				{
           					PID = getInputString(in, "Enter the location's PID:");
           					if(PID.equals("done"))
           					{
           						break;
           					}
               				cost = "";
    		           		while(true)
    		           		{
    		           			cost = getInputString(in, "Enter the amount spent (round to nearest int):");
    			           		if(valueIsNumber(cost))
    			           		{
    			           			break;
    			           		}
    			           		else
    			           		{
    			           			System.out.println("error not a vaild number:");
    			           		}
    		           		}
    		           		num = "";
    		           		while(true)
    		           		{
    		           			num = getInputString(in, "Enter the number of visitors:");
    			           		if(valueIsNumber(num))
    			           		{
    			           			break;
    			           		}
    			           		else
    			           		{
    			           			System.out.println("error not a vaild number:");
    			           		}
    		           		}
    		           		Visit visit = new Visit(login, PID, cost, num);
    		           		v.add(visit);
           				}
           				for(Visit i: v)
           				{
           					i.displayEntry();
           				}
           				review = "";
           				while(true)
           				{
           					review = getInputString(in, "Enter yes to submit entries or no to cancel:");
           					
           					if(review.equals("yes"))
           					{
           						for(Visit i: v)
                   				{
                   					System.out.println(i.addVisit(con.stmt));
                   				}
           						break;
           					}
           					else if(review.equals("no"))
           					{
           						break;
           					}
           					else
           					{
           						System.out.println("Error not a vaild response:");
           					}
           				}
           			}

           		}
           		else if(c == 3)
           		{
	           		if(adminLogin(in, con.stmt))
	           		{
	           			name = getInputString(in, "please enter the POI name:");
		           		address = getInputString(in,"please enter the POI address:");
		           		city = getInputString(in,"please enter the POI city:");
		           		state = getInputString(in,"please enter the POI state:");
		           		URL = getInputString(in,"please enter the POI URL:");
		           		//check phone number is a number
		           		phoneNumber = "";
		           		while(true)
		           		{
		           			phoneNumber = getInputString(in,"please enter the POI phone number(no dashes or spaces):");
			           		if(valueIsNumber(phoneNumber))
			           		{
			           			break;
			           		}
			           		else
			           		{
			           			System.out.println("error not a vaild phone number:");
			           		}
		           		}
		           		//check year is valid
		           		yearEstablished = "";
		           		while(true)
		           		{
		           			yearEstablished = getInputString(in,"please enter the POI year established:");
			           		if(valueIsNumber(yearEstablished))
			           		{
			           			break;
			           		}
			           		else
			           		{
			           			System.out.println("error not a vaild year:");
			           		}
		           		}
		           		hours = getInputString(in,"please enter the POI hours:");
		           		category = getInputString(in,"please enter the POI category:");
		           		
		           		POI p = new POI();
		           		System.out.println(p.addPOI(name, address, city, state, URL, phoneNumber, yearEstablished, hours, category, con.stmt));
	           		}
           		}
           		else if(c==4)
           		{
           			if(adminLogin(in, con.stmt))
           			{
           				PID = getInputString(in, "please enter POI's unique ID number:");
           				name = getInputString(in, "please enter the POI's updated name:");
		           		address = getInputString(in,"please enter the POI's updated address:");
		           		city = getInputString(in,"please enter the POI updated city:");
		           		state = getInputString(in,"please enter the POI updated state:");
		           		URL = getInputString(in,"please enter the POI's updated URL:");
		           		//check phone number is a number
		           		phoneNumber = "";
		           		while(true)
		           		{
		           			phoneNumber = getInputString(in,"please enter the new POI phone number(no dashes or spaces):");
			           		if(valueIsNumber(phoneNumber))
			           		{
			           			break;
			           		}
			           		else
			           		{
			           			System.out.println("error not a vaild phone number:");
			           		}
		           		}
		           		//check year is valid
		           		yearEstablished = "";
		           		while(true)
		           		{
		           			yearEstablished = getInputString(in,"please enter the new POI year established:");
			           		if(valueIsNumber(yearEstablished))
			           		{
			           			break;
			           		}
			           		else
			           		{
			           			System.out.println("error not a vaild year:");
			           		}
		           		}
		           		hours = getInputString(in,"please enter the POI's new hours:");
		           		category = getInputString(in,"please enter the POI's new category:");
		           		
		           		POI p = new POI();
		           		System.out.println(p.updatePOI(PID, name, address, city, state, URL, phoneNumber, yearEstablished, hours, category, con.stmt));
           			}
           			
           		}
           		else if(c==5)
           		{
           			if(userLogin(in, con.stmt, true))
           			{
           				login = classLogin;
           				
           				PID = getInputString(in, "please enter the POI's unique id number:");
           				
           				Favorite f = new Favorite();
           				System.out.println(f.addFavorite(PID, login, con.stmt));
           			}

           		}
           		else if(c==6)
           		{
           			if(userLogin(in, con.stmt, true))
           			{
           				login = classLogin;
           				
           				PID = getInputString(in, "please enter the POI's unique id number:");
           				
           				POI p = new POI();
           				if(!p.isReviewed(login, PID, con.stmt))
           				{
           					score ="";
           					while(true)
           					{
           						score = getInputString(in, "please enter a score for the POI in range 0 to 10 (0= terrible, 10= excellent):");
           						if(!valueIsNumber(score))
           						{
           							System.out.println("Error not a valid number");
           							continue;
           						}
           						if(Integer.parseInt(score) < 0 || Integer.parseInt(score) > 10)
           						{
           							System.out.println("Error number not in range");
           							continue;
           						}
           						break;
           					}
           					review = getInputString(in, "Enter a brief description with you feedback (optional):");
           					System.out.println(p.addFeedback(score, review, PID, login, con.stmt));
           				}
           				else
           				{
           					System.out.println("You have already given this POI feedback");
           				}
           				
           			}
           		}
           		else if(c==7)
           		{
           			if(userLogin(in, con.stmt, true))
           			{
           				login = classLogin;
           				
           				FID = getInputString(in, "Enter the feedback's unique ID number:");
           				
           				POI p = new POI();
           				
           				if(!p.isOwnReview(login, FID, con.stmt))
           				{
           					score ="";
           					while(true)
           					{
           						score = getInputString(in, "please enter a score for the POI in feedback, 0, 1, or 2 (’useless’, ’useful’, ’very useful’ respectively):");
           						if(!valueIsNumber(score))
           						{
           							System.out.println("Error not an valid number");
           							continue;
           						}
           						if(Integer.parseInt(score) < 0 || Integer.parseInt(score) > 3)
           						{
           							System.out.println("Error number not in range");
           							continue;
           						}
           						break;
           					}
           					System.out.println(p.addRating(FID, login, score, con.stmt));
           				}
           				else
           				{
           					System.out.println("You cannot rate your own feedback");
           				}
           			}
           			
           		}
           		else if(c==8)
           		{
           			if(userLogin(in, con.stmt, true))
           			{
           				login = classLogin;
           				System.out.println("Login successful, enter trusted user's login names. Type exit to close session.");
           				Trusted t = new Trusted();
           				while(true)
           				{
           					otherUser = getInputString(in, "Enter user's login name:");
           					if(otherUser.equals("exit"))
           					{
           						break;
           					}
           					String isTrusted = "0";
           					while(true)
           					{
           						trustVal = getInputString(in, "Is user trusted? Enter 'trusted' or 'not-trusted' (no quotations):");
               					if(trustVal.equals("trusted"))
               					{
               						isTrusted = "1";
               						break;
               					}
               					else if(trustVal.equals("not-trusted"))
               					{
               						break;
               					}
               					else
               					{
               						System.out.println("Error not a vaild flag");
               					}
           					}
           					t.trustUser(login, otherUser, isTrusted, con.stmt);
           				}
           				
           			}
           		}
           		else if(c==9)
           		{
           			System.out.println("You can leave any search attributes you do not care about blank, except for sort.");
           			minPrice = getInputString(in, "Enter minimum price for range:");
           			maxPrice = getInputString(in, "Enter maximum price for range:");
           			address = getInputString(in, "Enter an address to search:");
           			city = getInputString(in, "Enter a city to search:");
           			state = getInputString(in, "Enter a state to search:");
           			category = getInputString(in, "Enter a category to search:");
           			
           			System.out.println();
           			System.out.println("1. Sort POIs by price:");
           			System.out.println("2. Sort POIs by average feedback scores:");
           			System.out.println("3. Sort POIs by average feedback scores from trusted users:");
           			choice = getInputString(in, "enter your choice:");
           			try
               	 	{
           				c = Integer.parseInt(choice);
           				if(c<1 || c>3)
           				{
           					System.out.println("Not a choice");
           					throw new Exception();
           				}
               	 	}
               	 	catch (Exception e)
               	 	{ 
               	 		continue;
               	 	}
           			
           			POI p = new POI();
           			p.searchPOI(minPrice, maxPrice, address, city, state, category, c, con.stmt);
           		}
           		else if(c==10)
           		{
           			PID = getInputString(in, "Enter the PID of the POI:");
           			num = getInputString(in, "How many results should be displayed:");
           			System.out.println("The following are the request reviews displayed as Login, FID, User's given score, User's text feedback, and the average rating.");
           			
           			POI p = new POI();
           			p.getTopFeedbacks(PID, num, con.stmt);
           			
           		}
           		else if(c==11)
           		{
           			
           		}
           		else if(c ==12)
           		{
           			
           		}
           		else if(c ==13)
           		{
           			System.out.println("1. Find most popular POIs by category:");
           			System.out.println("2. Find most expensive POIs by category:");
           			System.out.println("3. Find highest rated POIs by category:");
           			choice = getInputString(in, "enter your choice:");
           			try
               	 	{
           				if(c<1 || c>3)
           				{
           					System.out.println("Not a choice");
           					throw new Exception();
           				}
               	 		c = Integer.parseInt(choice);
               	 	}
               	 	catch (Exception e)
               	 	{ 
               	 		continue;
               	 	}
           			category = getInputString(in, "Which category would you like to search:");
           			num = getInputString(in, "How many results should be displayed:");
           			System.out.println("Results are dislpayed as PID, POI name, and average");
           			POI p = new POI();
           			if(c==1)
           			{
           				p.getMostPopular(category, num, con.stmt);
           			}
           			else if(c==2)
           			{
           				p.getMostExpensive(category, num, con.stmt);
           			}
           			else
           			{
           				p.getHighestRated(category, num, con.stmt);
           			}
           		}
           		else if(c==14)
           		{
           			
           		}
           		else
           		{
           			System.out.println("Thank you for using UTrack");
           			con.stmt.close(); 
           			break;
           		}
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
       	 	System.err.println ("Either connection error or query execution error!");
        }
        //always make sure connection is closed
        finally
        {
        	if (con != null)
       	 	{
	       		 try
	       		 {
	       			 con.closeConnection();
	       			 System.out.println ("Database connection terminated");
	       		 }
	       		 catch (Exception e) 
	       		 { /* ignore close errors */ }
       	 	}
        }
	}
	
	private boolean valueIsNumber(String phoneNumber) 
	{
		try
   		{
   			Integer.parseInt(phoneNumber);
   			return true;
   		}
   		catch(Exception e)
   		{
   			return false;
   		}
	}

	private boolean adminLogin(BufferedReader in, Statement stmt)
	{
		User user = new User();
		String login, password;
		
		while(true)
		{
			login = getInputString(in, "please enter a Login:");
			
			if(!login.equals(""))
			{
				break;
			}
			else
			{
				System.out.println("Error no login given");
			}
		}
		
		if(user.isAdmin(login, stmt))
   		{
			//get the user's password and check it's correct
   			String userPass = user.getUserPassword(login, stmt);
   			password = getInputString(in, "please enter a password:");
       		//successful user login now get info
       		if(userPass.equals(password))
       		{
       			return true;
       		}
       		else
       		{
       			System.out.println("Error invalid password");
       			return false;
       		}
   		}
		else
		{
   			System.out.println("Error not an admin");
   			return false;
   		}
	}
	
	private boolean userLogin(BufferedReader in, Statement stmt, boolean delgate)
	{
		User user = new User();
		String login, password;
		while(true)
		{
			login = getInputString(in, "please enter a Login:");
			
			if(!login.equals(""))
			{
				break;
			}
			else
			{
				System.out.println("Error no login given");
			}
		}
		
		//get the user's password and check it's correct
		String userPass = user.getUserPassword(login, stmt);
		password = getInputString(in, "please enter a password:");
   		//successful user login now get info
   		if(userPass.equals(password))
   		{	
   			if(delgate)
   			{
   				classLogin = login;
   			}
   			return true;
   		}
   		else
   		{
   			System.out.println("Error invalid password");
   			return false;
   		}
	}
	
	private String getInputString(BufferedReader in, String promptMessage)
	{
		String input ="";
		System.out.println(promptMessage);
   		try 
   		{
			while ((input = in.readLine()) == null && input.length() == 0);
		} 
   		catch (IOException e) 
   		{
			e.printStackTrace();
		}
		return input;
	}

	private void displayMenu()
	{
		 System.out.println("        Welcome to the UTrack System     ");
    	 System.out.println("1. Register new user:");
    	 System.out.println("2. Log a POI vist:");
    	 System.out.println("3. Admin user can add new POI information:");
    	 System.out.println("4. Admin user can update a POI's information:");
    	 System.out.println("5. Users can declare a POI as a favorite location:"); 
    	 System.out.println("6. Users can leave feedback on a POI:"); 
    	 System.out.println("7. Users declare the usefullness of a feedback entry:"); 
    	 System.out.println("8. Users can declare others as trusted or not trusted:"); 
    	 System.out.println("9. Search for a POI based on a price range, location, or category. The results can be ordered by price, average score on feedback or average score on trusted user feedbacks:"); 
    	 System.out.println("10. Request n number of most useful feedbacks for a POI:"); 
    	 System.out.println("11. Incomplete:");
    	 System.out.println("12. Incomplete:");
    	 System.out.println("13. Find POIs by most popular, most expensive, highest rated:");
    	 System.out.println("14. Incomplete:");
    	 System.out.println("15. EXIT"); 
    	 System.out.println("please enter your choice:");
	}
	
	
	
	public static void main(String[] args) 
	{
		ProjectDriver p = new ProjectDriver();
	}
}
