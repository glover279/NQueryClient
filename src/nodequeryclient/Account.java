/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodequeryclient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.joda.time.DateTimeZone;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Robert
 */

public class Account {
	DB db9 = new DB(); //instantiation of a new DB object names db9                   
	private String api = db9.getAPIkey();



	public void setAPI() {

	}
	public boolean checkSuccessfulResponse() throws MalformedURLException { //this accessor method checks if the response from the API was successful and returns a boolean value of true if it was
            // this is done by checking the value of the HTTP response code, 200 means the response was ok
		URL url = new URL("https://nodequery.com/api/account?api_key=" + api); // this is the URL to connect to the API and retrieve the account info
		HttpURLConnection http = null; //new HttpURLConnection
		int statusCode = 0; //set status code initial value to zero

		try { 
			http = (HttpURLConnection) url.openConnection(); //open the connection
		} catch (IOException ex) { //catch if an IOException occurs
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			statusCode = http.getResponseCode(); // get the response code value and store it in the integer variable
		} catch (IOException ex) { // catch if an IOException occurs
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		}
		boolean success; // create new boolean variable
		System.out.println("STATUS CODE: " + statusCode); // for debugging purposes
		if (statusCode == 200) {   //if the status code is 200 then set the boolean value to true
			success = true;
		} else { // if the status code is not 200 then set the boolean value to false as the response was unsuccessful
			success = false;
		}
		return success;
	}


	public String getAccount() throws HTTPstatusException { //method to get account data
		String uri = "https://nodequery.com/api/account?api_key=" + api; 

		boolean t2 = false;
		try {
			t2 = checkSuccessfulResponse(); //set the boolean value to the result of the checkSuccessfulResponse() method
			System.out.println("RESP: " + t2); // for debugging purposes
		} catch (IOException ex) { //catch an IOException if it occurs
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		}
		String result = null;
		if (t2 == true) { //if the response is successful
			RestTemplate restTemplate = new RestTemplate(); //instantiate new RestTemplate
			result = restTemplate.getForObject(uri, String.class); //set the string to the result from the API
		} else {
			JOptionPane.showMessageDialog(null, "API Key Invalid"); //inform the user that the API key is invalid
			throw new HTTPstatusException("HTTP RESPONSE ERROR, CHECK API KEY"); // throw a new HTTPstatusException
		}
		System.out.println(result); // for debugging purposes
		return result; //return the string
	}

	public String getAPI() { //accessor method which returns the API key that was set
		return api;
	}



	public static void main(String[] args) { //main method
		// TODO code application logic here
	}

	public boolean getStatus() throws HTTPstatusException {
		JSONObject obj = new JSONObject(getAccount());
		String t = obj.toString();
		CharSequence cs1 = "ERROR";
		boolean chk;
		if (t.contains(cs1)) {
			chk = false;
		} else {
			chk = true;
		}
		String Name = obj.getJSONObject("data").getString("name");


		System.out.println("Name: " + Name);
		return chk;

	}
	public Timestamp getUnixTime() {
		//Date object
		Date date = new Date();
		//getTime() returns current time in milliseconds
		long time = date.getTime(); //The java.util.Date.getTime() method returns how many milliseconds have passed since January 1, 1970, 00:00:00 GMT, this time in seconds is often referred to as UNIX Time
		//Passed the milliseconds to constructor of Timestamp class 
		Timestamp ts = new Timestamp(time);
		System.out.println("Current Time Stamp: " + ts); //for debugging purposes
		return ts; //return the timestamp value
	}


	public String getName() throws HTTPstatusException {
		JSONObject obj = new JSONObject(getAccount());
		String Name = obj.getJSONObject("data").getString("name");
		//Name = Name.replaceAll("\\s",""); 

		System.out.println("Name: " + Name);
		return Name;

	}
	public int getMaxServ() throws HTTPstatusException {
		JSONObject obj = new JSONObject(getAccount());
		int sl = obj.getJSONObject("data").getInt("server_limit");

		System.out.println("Timezone: " + sl);
		return sl;

	}
	public int getTimezone() throws HTTPstatusException {
		JSONObject obj = new JSONObject(getAccount());
		int tz = obj.getJSONObject("data").getInt("timezone");

		System.out.println("Timezone: " + tz);
		return tz;

	}
	public int getRequests() throws HTTPstatusException {
		JSONObject obj = new JSONObject(getAccount());
		int req = obj.getJSONObject("data").getJSONObject("api").getInt("requests");

		System.out.println("Requests: " + req);
		return req;

	}
	public int getRateLimit() throws HTTPstatusException {
		JSONObject obj = new JSONObject(getAccount());
		int rl = obj.getJSONObject("data").getJSONObject("api").getInt("rate_limit");

		System.out.println("Requests: " + rl);
		return rl;

	}
	public DateTimeZone getTimeInzone() throws HTTPstatusException {
		DateTimeZone zoneUTC = DateTimeZone.forOffsetHours(getTimezone());
		return zoneUTC;
	}



	public void InsertAccount() throws SQLException, HTTPstatusException, MalformedURLException {
		DB db3 = new DB();
		System.out.println("KEY: " + db3.getAPIkey());
		db3.insertIntoAcc(getName(), getTimezone(), getRequests(), getRateLimit(), getMaxServ(), db3.getAPIkey());
	}

}