/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodequeryclient;

import java.sql.SQLException;
import java.util.Scanner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Robert
 */
public class ServerList {
	

	DB db10 = new DB(); //instantiate new DB object
	public ServerList() throws SQLException {
		setServer(); //call set server method
	}

	public String getServerList() //accessor method which returns a string, the list of servers

	{
		String slist; //new strinf
		Account acc6 = new Account(); //instantiate new account obkect
		RestTemplate resttemplate = new RestTemplate(); //instantiate new resttemplate
		String url = "https://nodequery.com/api/servers?api_key=" + acc6.getAPI(); //url for connection
		slist = resttemplate.getForObject(url, String.class); //store the result in the string

		System.out.println(slist); //for debugging purposes
		return slist;

	}

	public void setServer() throws SQLException {

		
		String tempSL = getServerList().substring(66); //cut off unneccessary data
		System.out.println("TEMP: " + tempSL); //for debugging purposes
		String[] sList = new String[500]; //new string arrar of servers with 500 elements possible
		Scanner scan = new Scanner(tempSL).useDelimiter("},"); //new scanner to seperate each server in the JSON object and
		String temp2 = scan.next(); //next string
		
		int i = 0; //set counter to 0
		while (scan.hasNext()) { //iterate through the the file with the scanner
			sList[i] = scan.next(); //populate the array from the scanner
			i++; //increment i by one
			//scan.close();
		}

		int p = 0; //set counter to 0 
		while (sList[p] != null && sList[p].length() > 0) { //run while the array at the position has a value
			Scanner scan3 = new Scanner(sList[p]).useDelimiter(","); //new scanner to seperate line which is a value of data
			System.out.println("SCAN3:  " + sList[0]); //for debugging purposes

			String id = scan3.next();               //set next string = id
			String status = scan3.next();           //set next string = status
			String availability = scan3.next();     //set next string = availability
			String update_time = scan3.next();      //set next string = update_time
			String name = scan3.next();             //set next string = name
			String loadpercentage = scan3.next();   //set next string = loadpercentage
			String load_average = scan3.next();     //set next string = load_average
			String ram_total = scan3.next();        //set next string = ram tot (for future expansion)
			String ram_usage_ = scan3.next();       //set next string = ram usage (for future expansion)
			String disk_total = scan3.next();       //set next string = disk tot (for future expansion)
			String disk_usage = scan3.next();       //set next string = disk usage (for future expansion)
			String ipv4 = scan3.next();             //set next string = ipv4
			


			int idIndex = id.indexOf(":"); //find index location of the : character to remove it and the characters which preceed it
			int statusIndex = status.indexOf(":"); //repeat this till line 81
			int availabilityIndex = availability.indexOf(":");
			int update_timeIndex = update_time.indexOf(":");
			int nameIndex = name.indexOf(":");
			int loadpercentageIndex = loadpercentage.indexOf(":");
			int load_averageIndex = load_average.indexOf(":");
			int ipv4Index = ipv4.indexOf(":");

			System.out.println("IDdebug " + id); //for debugging purposes
			id = id.substring(idIndex + 2).replace('"', ' '); //replace quotation mark with space
			System.out.println("ID: " + id); //for debugging purposes
			int idInt = Integer.parseInt(id);
			status = status.substring(statusIndex + 2).replace('"', ' ');
			System.out.println("STATUS: " + status); //for debugging purposes
			availability = availability.substring(availabilityIndex + 2).replace('"', ' ');
			int alen = availability.length();
			String availabilityConv = availability.substring(0, alen - 2);

			System.out.println("AVAIL: " + availabilityConv);
			Double availabilityDouble = Double.parseDouble(availabilityConv);

			update_time = update_time.substring(update_timeIndex + 2).replace('"', ' ');
			System.out.println(update_time);
			int update_timeInt = Integer.parseInt(update_time);
			name = name.substring(nameIndex + 2).replace('"', ' ');
			loadpercentage = loadpercentage.substring(loadpercentageIndex + 2).replace('"', ' ');
			double loadpercentageDouble = Double.parseDouble(loadpercentage);
			load_average = load_average.substring(load_averageIndex + 2).replace('"', ' ');
			ipv4 = ipv4.substring(ipv4Index + 2).replace('"', ' ');
			java.sql.Timestamp time = new java.sql.Timestamp((long) update_timeInt * 1000);

			System.out.println("ID: " + idInt);
			// print these, ip incorrect match
			System.out.println(idInt + "STAT: " + status + "ava: " + availabilityDouble + " updaTM: " + time + " NM: " + name + " LPERC: " + loadpercentageDouble + " LA: " + load_average + "ip: " + ipv4);
			Server temp5 = new Server(idInt, availabilityDouble, time, name, loadpercentageDouble, load_average, ipv4);
			System.out.println("LA: " + temp5.getLoad_average());
			p++;

			//id,  availability,  update_time,  name,  loadpercentage,  load_average,  ipv4
			//first server not added, check delimeter
			//scan3.close();
		}
		scan.close();

	}

}