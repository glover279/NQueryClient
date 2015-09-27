/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodequeryclient;
import java.awt.Component;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert
 */
public class DB {
	//private static String host="jdbc:derby://localhost:1527/nqc";
	private static String host = "jdbc:derby:nodequery;create=true"; //this host string contains the name and database type to use.
	private static String uname = "robert"; //username for database authentication
	private static String pass = "password"; //password for database authentication
	private static Connection conn = null; //conncetion object
	private static Statement stmt = null; //statement object for SQL queries
	private static String key1 = ""; // string which stores the API key in use

	public DB() {
	
	}
	public static void createConnection() {     //method which creates a connection to the database
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); //specifies driver
			
			conn = DriverManager.getConnection(host); //Get a connection 
			JOptionPane.showMessageDialog(null, "Database Connection Successful"); //this notifies the user that the program has sucessfully connected to the database
		} catch (Exception except) {
			except.printStackTrace();  //for debugging purposes
			System.out.println("Database connection failed: " + except.getMessage()); //for debugging purposes
			Component frame = null; //new frame
			JOptionPane.showMessageDialog(null, "Database connection failed: " + except.getMessage()); //this notifies the user that the program has not sucessfully connected to the database
		}
	}

	public void CreateTables() throws SQLException {
		DatabaseMetaData dbm = conn.getMetaData(); //gets metadata from the table which allows us to determine if the database already exists


		try {
			ResultSet rs = dbm.getTables(null, null, "TBLSERVERS", null); //resultset from TBLSERVERS table
			if (rs.next()) {
				System.out.println("Table exists"); //if the resultset has a value then inform the user via the console that th table "TBLSERVERS" already exists
			} else {
				System.out.println("Table does not exist"); 
				conn.createStatement().execute("create table TBLSERVERS(ID INTEGER not null,AVAILABILITY DOUBLE not null,UPDATE_TIME TIMESTAMP not null,NAME VARCHAR(255) not null,LOADPERC DOUBLE not null,LOAD_AVE VARCHAR(255) not null,IPV4 VARCHAR(255),NUMBER INTEGER not null,primary key (UPDATE_TIME, NUMBER))");
			}       //if the resultset doesnt have a value then we can conclude that this table does not exist and we can create it

			ResultSet rs1 = dbm.getTables(null, null, "TBLACCOUNT", null); //resultset from TBLACCOUNT table
			if (rs1.next()) {
				System.out.println("Table exists"); //if the resultset has a value then inform the user via the console that th table "TBLACCOUNT" already exists
			} else {
				System.out.println("Table does not exist");
				conn.createStatement().execute("create table TBLACCOUNT(NAME VARCHAR(255),TIMEZONE INTEGER,TOTALAPICALLS INTEGER,DETECTEDRATE_LIMIT INTEGER,MAX_ALLOWEDSERVERS INTEGER,APIKEY VARCHAR(255),UNIXTIME TIMESTAMP not null,NUMBER INTEGER not null,primary key (UNIXTIME, NUMBER))");
			}       //if the resultset doesnt have a value then we can conclude that this table does not exist and we can creat



		} catch (SQLException ex) { //catch a SQL exception to prevent a possibility of the program crashing
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void insertIntoAcc(String Name, int tz, int totreq, int ratelim, int maxserv, String API) throws SQLException, MalformedURLException {
		try {   //this method inserts data into the TBLACCOUNT table
			Account acc1 = new Account(); //instantiate a new instance of the Account class
			if (acc1.checkSuccessfulResponse() == true) {
				JOptionPane.showMessageDialog(null, "API Key Valid"); //if the response from the API was true then inform the user that the API key is valid
				stmt = conn.createStatement();  //create statement
				Statement stmt3 = conn.createStatement(); //create statement
				Account acc = new Account(); //instantiate a new instance of the Account class
				ResultSet rownumber = stmt3.executeQuery("SELECT COUNT (*) AS count1 FROM TBLACCOUNT"); //counts the number of rows in the TBLACCOUNT table
				rownumber.next(); //move "cursor" to first position

				int rnum = rownumber.getInt(1); //store value of the count
				System.out.println(rnum); //for debugging purposes
				String I_into = "INSERT INTO TBLACCOUNT (NAME, TIMEZONE, TOTALAPICALLS, DETECTEDRATE_LIMIT, MAX_ALLOWEDSERVERS, APIKEY, UNIXTIME, NUMBER) " + "VALUES(" + "'" + Name + "'" + "," + tz + "," + totreq + "," + ratelim + "," + maxserv + ",'" + API + "'" + ",'" + acc.getUnixTime() + "', " + rnum + ")";
                                //String which contains the insert statement for the TBLACCOUNT table
				ResultSet rs3 = stmt3.executeQuery("SELECT COUNT (*) AS count FROM TBLACCOUNT WHERE UNIXTIME=" + "'" + acc.getUnixTime() + "'");
                                //count to check the primary key which will be inserted is unique

				rs3.next(); //move cursor to first position

				System.out.println(rnum); //for debugging purposes
				if (rs3.getInt(1) == 0 ) { //if the count resulted in a value of zero then execute the statement
					stmt.executeUpdate(I_into);
				} else {
					System.out.println("CANNOT INSERT, UPDATING"); //if the count did not result in a 0 then do not insert the data as the primary key will have duplicate values, instead execute a SQL update
					stmt.execute("UPDATE TBLACCOUNT" + " SET" + " NAME='" + Name + "', TIMEZONE=" + tz + ", TOTALAPICALLS=" + totreq + ", DETECTEDRATE_LIMIT=" + ratelim + ", MAX_ALLOWEDSERVERS=" + maxserv + ", APIKEY='" + API + "'" + ", UNIXTIME='" + acc.getUnixTime() + "'," + " NUMBER=" + rnum);
				}


				stmt.close(); //close statement
				System.out.println("NodeQuery Account Successfully Updated"); //informs console that the account data was successfully updated
				JOptionPane.showMessageDialog(null, "NodeQuery Account Statistics Successfully Updated"); //informs console that the server data was successfully updated
			} else {
				JOptionPane.showMessageDialog(null, "API Key Invalid or Connection Failure (check your internet connection and API key)"); //informs the user that the API key provided is not valid
			}

		} catch (SQLException sqlExcept) { //catches SQL exception to prevent an application crash in this case
			sqlExcept.printStackTrace(); // for debugging purposes
			System.out.println(sqlExcept.getMessage()); //for debugging purposes
			System.out.println("Failed to update account"); //informs the user that the account data could not be updated as a result ot a SQL exception

		}
	}

	public static void setAPIkey(String key) { //mutator method which sets the API key value
		key1 = key;


	}
	public String getAPIkey() { //accessor method which returns the API key value
		return key1;
	}

	public static int getNumberOfRows() throws SQLException { //accessor method which counts the number of rows in the TBLACCOUNT  table and returns the result
		stmt = conn.createStatement();
		Statement stmt3 = conn.createStatement();
		ResultSet rownumber = stmt3.executeQuery("SELECT COUNT (*) AS count1 FROM TBLACCOUNT"); // SQL Count query
		rownumber.next();

		int rnum = rownumber.getInt(1);
		System.out.println(rnum); // for debugging purposes
		return rnum; //return value

	}

	public static int getNumberOfRowsTBLSERVERS() throws SQLException { //accessor method which counts the number of rows in the TBLSERVERS table and returns the result
		stmt = conn.createStatement();
		Statement stmt4 = conn.createStatement();
		ResultSet rownumber = stmt4.executeQuery("SELECT COUNT (*) AS count1 FROM TBLSERVERS"); // SQL Count query
		rownumber.next();

		int rnum = rownumber.getInt(1);
		System.out.println(rnum); // for debugging purposes
		return rnum; //return value

	}


	public static int getFromServDBint(String Column, String Server, int number) //accessor method which returns an integer value from te TBLSERVERS table
	{
		int temp = 0;
		try {
			Statement get = conn.createStatement();
			String sql = "SELECT " + Column + " FROM TBLSERVERS WHERE NAME='" + Server + "'"; //sql query, user must specify the server name and the unique record numnber
			ResultSet rs = get.executeQuery(sql); //stores result as a resultset
			rs.next(); //move cursor to position 1
			rs.getInt(Column); 
			temp = rs.getInt(Column);//get the integer value at this position



		} catch (SQLException ex) { //catches SQL exception to prevent an application crash in this case
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
		}

		return temp; //returns integer value

	}

	public static String getFromServDBString(String Column, int number) //accessor method which returns an string value from te TBLSERVERS table
	{
		String temp = null;
		try {
			Statement get = conn.createStatement();
			String sql = "SELECT " + Column + " FROM TBLSERVERS WHERE NUMBER=" + number; //sql query, user must specify the unique record numnber
			ResultSet rs = get.executeQuery(sql); //execute query and store as a resultset
			rs.next(); //move cursor to first position
			rs.getString(1); 
			temp = rs.getString(1); //get the string value at this position and store it in temp variable



		} catch (SQLException ex) {  //catches SQL exception to prevent an application crash in this case
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
		}

		return temp; //return string value

	}

	public static String getFromAccDBString(String table, String Column, int record) //accessor method which returns an string value from te TBLACCOUNT table
	{
		String temp = null;
		try {
			Statement get = conn.createStatement();
			String sql = "SELECT " + Column + " FROM " + table + " WHERE NUMBER =" + record; //sql query, user must specify the unique record numnber
			ResultSet rs = get.executeQuery(sql);
			rs.next();
			rs.getString(Column);
			temp = rs.getString(Column);



		} catch (SQLException ex) { //catches SQL exception to prevent an application crash in this case
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
		}

		return temp; //return string value

	}


	public static String[] getFromServDBName() //accessor method which returns an array of strings which contains each server name
	{


		String[] names; //string array of names
		names = new String[100]; // 100 elements in array
		try {


			Statement get = conn.createStatement();
			String sql = "SELECT NAME FROM TBLSERVERS"; //distinct and group by produce missing values, therefore duplicates must be tolerated
			ResultSet rs = get.executeQuery(sql); // store result of query in resultset
			rs.next(); // move cursor to first position
			int i = 0; // set i (counter) to 0

			while (rs.next() == true) { //while the resultset has a next value, execute:
				names[i] = rs.getString(1); //set name value in array position to resultset string value at equivalent position
				rs.next(); // move cursor up one place
				i++; //increment counter by one
			}

			//for debugging purposes            
			System.out.println("SERVER" + names[1]);
			System.out.println("SERVER" + names[0]);
			System.out.println("SERVER" + names[2]);
			System.out.println("SERVER" + names[3]);
			System.out.println("SERVER" + names[4]);
                        
		} catch (SQLException ex) { //catches SQL exception to prevent an application crash in this case
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
		}

		return names; //return array of strings

	}

	public static int getFromAccDBint(String table, String Column, int record) //accessor method which returns an integer from Account table
	{
		int temp = 0;
		try {
			Statement get = conn.createStatement();
			String sql = "SELECT " + Column + " FROM " + table + "WHERE NUMBER=" + record; //sql query, user must specify the unique record numnber
			ResultSet rs = get.executeQuery(sql); //execute query and store as a resultset
			rs.getString(Column); 
			temp = rs.getInt(Column); //



		} catch (SQLException ex) { //catches SQL exception to prevent an application crash in this case
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
		}

		return temp; //return integer

	}


	public static void insertIntoSL(int id, Double avail, java.sql.Timestamp update_tm, String name, double load_percentage, String load_average, String ipv4) throws SQLException {
		try { //method which inserts data into the TBLSERVERS database

			stmt = conn.createStatement(); //create statement
			Statement stmt7 = conn.createStatement(); //create statement

			String I_into = "INSERT INTO TBLSERVERS (ID, AVAILABILITY, UPDATE_TIME, NAME, LOADPERC, LOAD_AVE, IPV4, NUMBER) " + "VALUES(" + id + "," + avail + ",'" + update_tm + "','" + name + "'," + load_percentage + ",'" + load_average + "'" + ",'" + ipv4 + "'," + getNumberOfRowsServer() + ")"; //insert into TBLSERVERS table statement
			ResultSet rs4 = stmt7.executeQuery("SELECT COUNT (*) AS count FROM TBLSERVERS WHERE UPDATE_TIME='" + update_tm + "'"); //count, which checks primary key is unique
			rs4.next(); //move cursor to first position
			System.out.println("VALUE1= " + rs4.getInt(1)); //for debugging purposes

			if (rs4.getInt(1) == 0) { //if the primary key is unique then exexute the SQL update
				stmt.executeUpdate(I_into, Statement.RETURN_GENERATED_KEYS);

				System.out.println("NodeQuery Servers Sucessfully Inserted"); // inform the user that the data was updated successfully
			} else {
				System.out.println("CANNOT INSERT, ALREADY EXISTS");  // if the count resulted in a value of more than 0 then the primary key value already exists and the inster will be abandoned
				
				stmt.close(); // close the statement
				System.out.println("Account already up to date"); // inform the user that the account is already up to date as the data the api provided already exists in the database
				
			}
		} catch (SQLException sqlExcept) { //catches SQL exception to prevent an application crash in this case
			System.out.println(stmt.toString());
			sqlExcept.printStackTrace(); // for debugging purposes
			System.out.println("MESSAGE: " + sqlExcept.getMessage());
			System.out.println("Failed to update Server");

		}


	}

	public static int getNumberOfRowsServer() throws SQLException { //accessor method which counts the number of rows int the TBLSERBERS and returns the result as an integer value
		stmt = conn.createStatement();
		Statement stmt3 = conn.createStatement();
		ResultSet rownumber = stmt3.executeQuery("SELECT COUNT (*) AS count1 FROM TBLSERVERS"); // sql count statement
		rownumber.next(); // move cursor to first position

		int rnum = rownumber.getInt(1); //store result in an integer variable
		System.out.println(rnum); // for debugging purposes
		return rnum; //return integer

	}

	public ResultSet ServerData() { //method ServerData which returns a resultset used to poputate jTable
		ResultSet rs = null; //new resultset of default value null
		try {


			String query = "SELECT * FROM TBLSERVERS"; //get all data in the TBLSERVERS table
			PreparedStatement pst = conn.prepareStatement(query); //prepare a new statement
			rs = pst.executeQuery(); //execute statement and store result in a resultset 
		} catch (SQLException ex) { //catches SQL exception to prevent an application crash in this case
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);

		}
		return rs;
	}

	public static int strToInt(String str) { //converts string to integer
		int i = 0;
		int num = 0;
		boolean isNeg = false;

		//Check for negative sign; if it's there, set the isNeg flag 
		if (str.charAt(0) == '-') {
			isNeg = true;
			i = 1;
		}

		//Process each character of the string; 
		while (i < str.length()) {
			num *= 10;
			num += str.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
		}

		if (isNeg) num = -num;
		return num;
	}
}