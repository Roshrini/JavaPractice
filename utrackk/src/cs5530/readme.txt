My application is started through testdriver2.java (Feifei's initial file).
The main method in testdriver2.java is what initiates calls to all other methods.

My database contains a variety of test users and test POIs, but it is not densely populated.
I only inserted what was needed for simple and edge case testing of SQL functionality.
Feel free to insert more if needed.

The administrator account is as follows:

login: admin
password: admin

The admin account will give you access to all programmed functionality.
If you would like a new admin account, you have to go into the database and manually
make one of the users an admin by setting admin=1.  
Users do not have the ability to make themselves admins through registration to avoid
insecure privileges.

Clarifications (things I discussed with Feifei that he stated were acceptable):

1. When adding a POI, you must add the keywords associated with that POI separately after
inserting the POI through the "14. Add Keywords" option.  This goes for "16. Add Pictures"
as well.

2. Two degrees of separation: the output you will receive is a list of users logins and names
who are two degrees separated from whomever the current user is.

3. In POI Browsing, I interpreted keywords to mean the keywords associated with the POIs.
So, make sure to add keywords to your POIs if you choose to test that functionality on
new POIs.

Other than that, the menu is fairly user friendly, so you shouldn't run into trouble.
Feel free to email me at brandon.gregory.koch@gmail.com if you have any questions.