package cs5530;

import java.io.*;

/**
 * Database navigator program. Uses command-line inputs to operate
 * 
 * @Assigment Phase2
 * @author Fahad Alothaimeen
 * @uNid u0824563
 */
public class Utrack {

	/**
	 * Choices message
	 */
	public static void displayMenu() {
		System.out.println("\r\n\r\n1. update my profile:");
		System.out.println("2. record a visit:");
		System.out.println("3. assign admin user(s): (Admin Account Required!)");
		System.out.println("4. add a POI: (Admin Account Required!)");
		System.out.println("5. edit an existing POI: (Admin Account Required!)");
		System.out.println("6. add favorite POI(s):");
		System.out.println("7. record a feedback(s):");
		System.out.println("8. assess a feedback(s):");
		System.out.println("9. trust a user(s):");
		System.out.println("10. search POI(s):");
		System.out.println("11. search feedback(s):");
		System.out.println("12. show useful statistics:");
		System.out.println("13. determine 'degree of separation' (DOS):");
		System.out.println("14. give awards: (Admin Account Required!)");
		System.out.println("15. exit:");
		System.out.println("pleasse enter your choice:");
	}

	@SuppressWarnings("null")
	public static void main(String[] args) {

		Connector con = null;
		String choice;
		String login = "";
		String password = "";
		int c = 0;
		try {
			// connecting to database
			con = new Connector();
			System.out.println("Database connection established");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			// verifying User
			System.out.println("please enter your username:");
			while ((login = in.readLine()) == null && login.length() == 0)
				;
			System.out.println("please enter your password:");
			while ((password = in.readLine()) == null && password.length() == 0)
				;
			User user = new User(login, password);

			if (user.verifyUser(con.stmt)) {
				System.out.print("        Welcome to the UTrack System     ");

				while (true) {
					displayMenu();
					while ((choice = in.readLine()) == null && choice.length() == 0)
						;
					try {
						c = Integer.parseInt(choice);
					} catch (Exception e) {
						System.out.println("your choice shuold be numerical.");
						continue; // ignour and start over
					}
					if (c < 1 | c > 15) {
						System.out.println("choose a number in this range [0-15]");
						continue; // ignour and start over
					}

					if (c == 1) { //update my profile
						System.out.println("\r\nMy Profile:");
						System.out.print(user.getUserInfo(con.stmt));

						String answer1;
						String answer2;
						do {
							System.out.println("\r\nPlease write down the name of the field that you want to update:");
							System.out.println("(full_name/address/telephone)");
							while ((answer1 = in.readLine()) == null && answer1.length() == 0)
								;
							System.out.println("What is the new value?");
							while ((answer2 = in.readLine()) == null && answer2.length() == 0)
								;
							user.updateUserInfo(answer1, answer2, con.stmt);

							System.out.println("\r\nDo you want to update anoher field? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));

					} else if (c == 2) { //record a visit
						String POIid;
						String cost;
						String visitors;
						String date;

						POI poi = new POI();
						Visit visits = new Visit();

						System.out.println("\r\nMy Visits:");
						System.out.println("" 
								+ "######################## Legend #######################\r\n"
								+ "# [ POI_name ][ address ][ cost ][ visitors ][ date ] #\r\n"
								+ "#######################################################" 
								+ "");
						System.out.print(visits.showVisits(login, con.stmt));

						System.out.println("\r\nWould you like a brief list of all POI(s)? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							System.out.println("" 
									+ "############ Legend #############\r\n"
									+ "# [ POI_id ][ name ][ address ] #\r\n" 
									+ "#################################"
									+ "");
							poi.printPOIs(con.stmt);
						}

						System.out.println("\r\nplease enter a POIid:");
						while ((POIid = in.readLine()) == null && POIid.length() == 0)
							;
						System.out.println("please enter the cost:");
						while ((cost = in.readLine()) == null && cost.length() == 0)
							;
						System.out.println("please enter number of visitors:");
						while ((visitors = in.readLine()) == null && visitors.length() == 0)
							;
						System.out.println("please enter the date of visit: (YYYY-MM-DD)");
						while ((date = in.readLine()) == null && date.length() == 0)
							;

						System.out.println("Visit Review:");
						System.out.println("visitor: " + login);
						System.out.println("POIid: " + POIid);
						System.out.println("cost: $" + cost);
						System.out.println("number of visitors: " + visitors);
						System.out.println("date of visit: " + date);
						System.out.println("\r\nDo you still want to add this visit? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							poi.recordVisit(login, POIid, cost, visitors, date, con.stmt);

							// SEGESTIONS POI
							System.out.println("\r\n\r\nSuggested POI you might like:\r\n");
							System.out.println("" 
									+ "############ Legend #############\r\n"
									+ "# [ POI_id ][ name ][ address ] #\r\n" 
									+ "#################################"
									+ "");
							System.out.print(poi.showSuggestions(POIid, con.stmt));
						}
					} else if (c == 3) { //assign admin user(s)
						if (!user.isAdmin()) {
							System.out.println("Sorry, you need administrator permission to access this section!");
							continue;
						}
						System.out.println("Would you like a list of all login(s)? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							System.out.println("" 
									+ "## Legend ###\r\n" 
									+ "# [ login ] #\r\n" 
									+ "#############" 
									+ "");
							user.printUsers(con.stmt);
							System.out.println("");
						}

						String target;
						do {
							System.out.println("\r\nWho would you like to make an administrator? (provide: login)");
							while ((target = in.readLine()) == null && target.length() == 0)
								;
							user.assignAdmin(target, con.stmt);

							System.out.println("\r\nWould you like to assign another user? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else if (c == 4) { // add a POI
						if (!user.isAdmin()) {
							System.out.println("Sorry, you need administrator permission to access this section!");
							continue;
						}
						String pname;
						String address;
						String telephone;
						String url;
						String pyear;
						String hours;
						String price;
						String category;

						System.out.println("please enter POI name: (leave blank to skip)");
						while ((pname = in.readLine()) == null && pname.length() == 0)
							;
						System.out.println("please enter the address: (leave blank to skip)");
						while ((address = in.readLine()) == null && address.length() == 0)
							;
						System.out.println("please enter the telephone number: (leave blank to skip)");
						while ((telephone = in.readLine()) == null && telephone.length() == 0)
							;
						System.out.println("please enter the url: (leave blank to skip)");
						while ((url = in.readLine()) == null && url.length() == 0)
							;
						System.out.println("please enter the year of establishsment: (leave blank to skip)");
						while ((pyear = in.readLine()) == null && pyear.length() == 0)
							;
						System.out.println("please enter the hours of oporation: (leave blank to skip)");
						while ((hours = in.readLine()) == null && hours.length() == 0)
							;
						System.out
								.println("please enter the average price per visit per person: (leave blank to skip)");
						while ((price = in.readLine()) == null && price.length() == 0)
							;
						System.out.println("please enter the category: (leave blank to skip)");
						while ((category = in.readLine()) == null && category.length() == 0)
							;

						System.out.println("POI Review:");
						System.out.println("POI name: " + pname);
						System.out.println("address: " + address);
						System.out.println("telephone: " + telephone);
						System.out.println("url: " + url);
						System.out.println("year built: " + pyear);
						System.out.println("hours: " + hours);
						System.out.println("average price: $" + price);
						System.out.println("category: " + category);
						System.out.println("\r\nDo you still want to add this POI? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							POI poi = new POI();
							poi.addPOI(pname, address, telephone, url, pyear, hours, price, category, con.stmt);
						}
					} else if (c == 5) { // edit an existing POI
						if (!user.isAdmin()) {
							System.out.println("Sorry, you need administrator permission to access this section!");
							continue;
						}
						POI poi = new POI();

						System.out.println("Would you like a brief list of all POI(s)? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							System.out.println("" 
									+ "############ Legend #############\r\n"
									+ "# [ POI_id ][ name ][ address ] #\r\n" 
									+ "#################################"
									+ "");
							poi.printPOIs(con.stmt);
						}

						String target;
						System.out.println("\r\nWhich POI you would like to modify? (provide: POI_id)");
						while ((target = in.readLine()) == null && target.length() == 0)
							;

						System.out.println("POI Preview:");
						System.out.print(poi.showPOI(target, con.stmt));

						String field;
						String value;
						do {
							System.out.println(
									"\r\nPlease write down the name of the field that you want to modify: (provide exact field name)");
							while ((field = in.readLine()) == null && field.length() == 0)
								;

							System.out.println("What is the new value?");
							while ((value = in.readLine()) == null && value.length() == 0)
								;
							poi.updatePOIInfo(target, field, value, con.stmt);

							System.out.println("\r\nWould you like to modify another field? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else if (c == 6) { //add favorite POI(s)
						Favorites fav = new Favorites();
						POI poi = new POI();
						String target;

						System.out.println("\r\nMy favorites:");
						System.out.println("" 
								+ "############## Legend #############\r\n"
								+ "# [ POI_name ][ address ][ date ] #\r\n" 
								+ "###################################"
								+ "");
						System.out.print(fav.showFavs(login, con.stmt));

						System.out.println("\r\nWould you like a brief list of all POI(s)? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							System.out.println("" 
									+ "############ Legend #############\r\n"
									+ "# [ POI_id ][ name ][ address ] #\r\n" 
									+ "#################################"
									+ "");
							poi.printPOIs(con.stmt);
						}
						do {
							System.out.println("\r\nWhich POI would you like to add? (provide: POI_id)");
							while ((target = in.readLine()) == null && target.length() == 0)
								;
							fav.addPOI(login, target, con.stmt);

							System.out.println("\r\nWould you like to add another POI? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else if (c == 7) { //record a feedback(s)
						Feedback fback = new Feedback();
						POI poi = new POI();
						String target;
						String score;
						String comment;

						System.out.println("\r\nMy Feedbacks:");
						System.out.println("" 
								+ "######################## Legend #######################\r\n"
								+ "# [ POI_name ][ address ][ score ][ comment ][ date ] #\r\n"
								+ "#######################################################" 
								+ "");
						System.out.print(fback.showFeeds(login, con.stmt));

						System.out.println("\r\nWould you like a brief list of all POI(s)? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							System.out.println("" 
									+ "############ Legend #############\r\n"
									+ "# [ POI_id ][ name ][ address ] #\r\n" 
									+ "#################################"
									+ "");
							poi.printPOIs(con.stmt);
						}
						do {
							System.out.println(
									"\r\nWhich POI would you like to write a feedback about? (provide: POI_id)");
							while ((target = in.readLine()) == null && target.length() == 0)
								;

							System.out.println("What is the score? [(terrible) 1-10 (excellent)]");
							while ((score = in.readLine()) == null && score.length() == 0)
								;
							try {
								c = Integer.parseInt(score);
							} catch (Exception e) {
								System.out.println("score shuold be numerical.");
								continue; // ignour and start over
							}
							if (c < 1 | c > 10) {
								System.out.println("choose a number in this range [1-10]");
								continue;
							}

							System.out.println(
									"Any additional comments? (write down your comment - leave blank if 'no')");
							while ((comment = in.readLine()) == null && comment.length() == 0)
								;
							fback.addFeed(login, target, score, comment, con.stmt);

							System.out.println("\r\nWould you like to write another feedback? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else if (c == 8) { //assess a feedback(s)
						Feedback fback = new Feedback();
						String target;
						String rating;

						System.out.println("\r\nWould you like a brief list of all feedback(s)? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							System.out.println(""
									+ "############################## Legend #############################\r\n"
									+ "# [ Feed_id ][ score ][ comment ][ login ][ POI_name ][ address ] #\r\n"
									+ "###################################################################" 
									+ "");
							fback.printFeeds(con.stmt);
						}
						do {
							System.out.println("\r\nWhich feedback would you like to assess? (provide: Feed_id)");
							while ((target = in.readLine()) == null && target.length() == 0)
								;

							System.out.println("What is your rating? [0=(useless), 1=(useful), 2=(very useful)]");
							while ((rating = in.readLine()) == null && rating.length() == 0)
								;
							try {
								c = Integer.parseInt(rating);
							} catch (Exception e) {
								System.out.println("rating shuold be numerical.");
								continue; // ignour and start over
							}
							if (c < 0 | c > 2) {
								System.out.println("choose a number in this range [0-2]");
								continue;
							}
							fback.assessFeed(login, target, rating, con.stmt);

							System.out.println("\r\nWould you like to record another assessment? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else if (c == 9) { //trust a user(s)
						Trust trusts = new Trust();
						String target;
						String isTrusted;

						System.out.println("Would you like a list of all login(s)? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							System.out.println("" 
									+ "## Legend ###\r\n" 
									+ "# [ login ] #\r\n" 
									+ "#############" 
									+ "");
							user.printUsers(con.stmt);
						}
						do {
							System.out.println("\r\nWhich User would you like to assess? (provide: login)");
							while ((target = in.readLine()) == null && target.length() == 0)
								;

							System.out.println("Do you trust this user? (0/1) [0=(no), 1=(yes)]");
							while ((isTrusted = in.readLine()) == null && isTrusted.length() == 0)
								;
							try {
								c = Integer.parseInt(isTrusted);
							} catch (Exception e) {
								System.out.println("rating shuold be numerical.");
								continue; // ignour and start over
							}
							if (c < 0 | c > 1) {
								System.out.println("choose [0] for 'no', and [1] for 'yes'");
								continue;
							}
							trusts.assessUser(login, target, isTrusted, con.stmt);

							System.out.println("\r\nWould you like to record another assessment? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else if (c == 10) { //search POI(s)
						POI poi = new POI();
						String keywords_str;
						String category;
						String place;
						String price;
						String sort;

						System.out
								.println("\r\nEnter keywords to search for: (separate by space) (leave blank to skip)");
						while ((keywords_str = in.readLine()) == null && keywords_str.length() == 0)
							;
						String[] keywords = keywords_str.split(" ");
						System.out.println("\r\nIn what category your searching: (leave blank to skip)");
						while ((category = in.readLine()) == null && category.length() == 0)
							;
						System.out.println("\r\nIn what City or State your searching: (leave blank to skip)");
						while ((place = in.readLine()) == null && place.length() == 0)
							;
						System.out.println(
								"\r\nWhat is your price range: (enter two numbers separated by space) (leave blank to skip)");
						while ((price = in.readLine()) == null && price.length() == 0)
							;
						String[] price_range = price.split(" ");
						System.out.println(
								"\r\nWould you like the results to be sorted? (a/b/c) (choose a letter - leave blank to skip)");
						System.out.println(
								"(a=(by price), b=(by the average score of feedbacks), c=(by the average score of trusted feedbacks))");
						while ((sort = in.readLine()) == null && sort.length() == 0)
							;

						System.out.println(
								"\r\n" + "################################################### Legend ##############################################\r\n"
										+ "# [ POI_id ][ name ][ address ][ telephone ][ url ][ year_built ][ hours ][ Average_price ][ category ] #\r\n"
										+ "#########################################################################################################"
										+ "");
						poi.search(keywords, category, place, price_range, sort, con.stmt);
					} else if (c == 11) { //search feedback(s)
						POI poi = new POI();
						Feedback fback = new Feedback();
						String target;
						String limit;

						System.out.println("\r\nWould you like a brief list of all POI(s)? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							System.out.println("" 
									+ "############ Legend #############\r\n"
									+ "# [ POI_id ][ name ][ address ] #\r\n" 
									+ "#################################"
									+ "");
							poi.printPOIs(con.stmt);
						}
						do {
							System.out.println("\r\nWhich POI's feedback(s) would you like to view? (provide: POI_id)");
							while ((target = in.readLine()) == null && target.length() == 0)
								;
							System.out.println("\r\nHow many feedback(s) to view? (provide: POI_id)");
							while ((limit = in.readLine()) == null && limit.length() == 0)
								;

							System.out.println(""
									+ "#################################### Legend ##################################\r\n"
									+ "# [ POI_name ][ address ][ score ][ comment ][ date ][ login ][ Usefulness ] #\r\n"
									+ "##############################################################################"
									+ "");
							fback.search(target, limit, con.stmt);

							System.out.println("\r\nWould you like to view another feedback(s)? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else if (c == 12) { // show useful statistics
						POI poi = new POI();
						String limit;

						System.out.println("\r\nHow many records per qeury to view?");
						while ((limit = in.readLine()) == null && limit.length() == 0)
							;

						do {
							System.out.println("\r\n1. The list of the " + limit
									+ " most popular POIs (in terms of total visits) for each category:");
							System.out.println("2. the list of the " + limit
									+ " most expensive POIs (defined by the average cost per head of all visits to a POI)"
									+ " for each category:");
							System.out.println("3. the list of the " + limit
									+ " most highly rated POIs (defined by the average scores from all feedbacks a POI has received)"
									+ "for each category:");
							System.out.println("Please choose qeury type: (1/2/3)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
							try {
								c = Integer.parseInt(choice);
							} catch (Exception e) {
								System.out.println("your choice shuold be numerical.");
								continue; // ignour and start over
							}
							if (c < 1 | c > 3) {
								System.out.println("choose a number in this range [1-3]");
								continue; // ignour and start over
							}

							// list of cats
							String category_list = poi.getCats(con.stmt);

							String[] Cats = category_list.split(">");
							for (String cat : Cats) {

								if (c == 1) {
									System.out.println("\r\nShowing list of the " + limit + " most popular POIs for "
											+ cat + " category:\r\n");
									System.out.println("" 
											+ "##################### Legend ####################\r\n"
											+ "# [ POI_id ][ name ][ address ][ total visits ] #\r\n"
											+ "#################################################" 
											+ "");
									System.out.println(poi.popularPOI(limit, cat, con.stmt));
								}

								if (c == 2) {
									System.out.println("Showing list of the " + limit + " most expensive POIs for "
											+ cat + " category:\r\n");
									System.out.println("" 
											+ "##################### Legend #####################\r\n"
											+ "# [ POI_id ][ name ][ address ][ average_price ] #\r\n"
											+ "##################################################" 
											+ "");
									System.out.println(poi.expensivePOI(limit, cat, con.stmt));
								}

								if (c == 3) {
									System.out.println("Showing list of the " + limit + " most highly rated POIs for "
											+ cat + " category:\r\n");
									System.out.println("" 
											+ "##################### Legend ######################\r\n"
											+ "# [ POI_id ][ name ][ address ][ average_rating ] #\r\n"
											+ "###################################################" 
											+ "");
									System.out.println(poi.ratedPOI(limit, cat, con.stmt));
								}
							}

							System.out.println("\r\nWould you like to view another qeury? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else if (c == 13) { //determine 'degree of separation' (DOS)
						String target;

						System.out.println("Would you like a list of all login(s)? (yes/no)");
						while ((choice = in.readLine()) == null && choice.length() == 0)
							;
						if (choice.equals("yes")) {
							System.out.println("" 
									+ "## Legend ###\r\n" 
									+ "# [ login ] #\r\n" 
									+ "#############" 
									+ "");
							user.printUsers(con.stmt);
						}

						do {
							System.out
									.println("\r\nWho would you like to figure out your 'DOS' with? (provide: login)");
							while ((target = in.readLine()) == null && target.length() == 0)
								;

							user.DOS(target, con.stmt);

							System.out.println("\r\nWould you like to find out another 'DOS'? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else if (c == 14) { //give awards
						if (!user.isAdmin()) {
							System.out.println("Sorry, you need administrator permission to access this section!");
							continue;
						}
						String limit;

						System.out.println("\r\nHow many records per qeury to view?");
						while ((limit = in.readLine()) == null && limit.length() == 0)
							;

						do {
							System.out.println("\r\n1. the top " + limit + " most ‘trusted’ users:");
							System.out.println("2. the top " + limit + " most ‘useful’ users:");
							System.out.println("Please choose qeury type: (1/2)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
							try {
								c = Integer.parseInt(choice);
							} catch (Exception e) {
								System.out.println("your choice shuold be numerical.");
								continue; // ignour and start over
							}
							if (c < 1 | c > 2) {
								System.out.println("choose a number in this range [1-2]");
								continue; // ignour and start over
							}

							if (c == 1) {
								System.out
										.println("\r\nShowing list of the top " + limit + " most ‘trusted’ users:\r\n");
								System.out.println(""
										+ "############################ Legend ###########################\r\n"
										+ "# [ login ][ full_name ][ telephone ][ address ][ trustness ] #\r\n"
										+ "###############################################################" 
										+ "");
								System.out.println(user.trustedUsers(limit, con.stmt));
							}

							if (c == 2) {
								System.out.println("Showing list of the top " + limit + " most ‘useful’ users:\r\n");
								System.out.println(""
										+ "############################# Legend ###########################\r\n"
										+ "# [ login ][ full_name ][ telephone ][ address ][ usefulness ] #\r\n"
										+ "################################################################" 
										+ "");
								System.out.println(user.usefulUsers(limit, con.stmt));
							}

							System.out.println("\r\nWould you like to view another query? (yes/no)");
							while ((choice = in.readLine()) == null && choice.length() == 0)
								;
						} while (choice.equals("yes"));
					} else { //15: exit
						System.out.println("Remeber to pay us!");
						con.stmt.close();

						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Either connection error or query execution error!");
		} finally {
			if (con != null) {
				try {
					con.closeConnection();
					System.out.println("Database connection terminated");
				}

				catch (Exception e) {
					/* ignore close errors */ }
			}
		}
	}
}
