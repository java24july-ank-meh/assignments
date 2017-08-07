This is very much a work in progress. Most of the work is done in the sql file. 
I took the approach of implementing most of the application through the use of stored procedures,
which is a more secure approach than having everything handled by the Java client. Unfortunately,
this proved to be extremely time consuming, so I was only able to get user login setup on the Java-side
(though that too still is giving me trouble, such as still returning a status of 0 (not logged in) for
the user after running the login function and entering a username and password in the USERS table 
(in this case, that of a dummy user I added in)). All of this effort should give me a good
start on project 1 at least.