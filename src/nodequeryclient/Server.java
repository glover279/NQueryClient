/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodequeryclient;

import java.sql.SQLException;

/**
 *
 * @author Robert
 */
public class Server {
	private int id;
	private String status;
	private Double availability;
	private double update_time;
	private String name;
	private double loadpercentage;
	private String load_average;
	private String ipv4;

	public Server(int id, Double availability, java.sql.Timestamp update_time, String name, double loadpercentage, String load_average, String ipv4) throws SQLException { //constructor method which takes in data in the parameters for further processing
            //and database storage
		this.id = id;
		this.availability = availability;
		//this.update_time = update_time;
		this.name = name;
		this.loadpercentage = loadpercentage;
		this.load_average = load_average;
		this.ipv4 = ipv4;

		DB.insertIntoSL(id, availability, update_time, name, loadpercentage, load_average, ipv4); //static method called which inserts the server data into the TBLSERVERS table
	}




	public int getId() { //accessor method which returns an integer ID
		return id;
	}

	public void setId(int id) { //mutator method which sets the ID
		this.id = id;
	}

	public String getStatus() { //accessor method which returns the String with the status of the request
		return status;
	}

	public void setStatus(String status) { // mutator method which sets the status
		this.status = status;
	}

	public Double getAvailability() { //accessor method which sets the Availability of the server (percentage)
		return availability;
	}

	public void setAvailability(Double availability) { //mutator method which sets the availability percentage (double)
		this.availability = availability;
	}

	public double getUpdate_time() { //accessor method which returns the Update time (milliseconds) as a double
		return update_time;
	}

	public void setUpdate_time(double update_time) { //mutator method which sets the update time double
		this.update_time = update_time;
	}

	public String getName() { //accessor method which returns the nameas a String
		return name;
	}

	public void setName(String name) { // mutator method which sets the name string
		this.name = name;
	}

	public double getLoadpercentage() { //accessor method which returns the load percentage as a double
		return loadpercentage;
	}

	public void setLoadpercentage(int loadpercentage) { //mutator method which sets the load percentage integer
		this.loadpercentage = loadpercentage;
	}

	public String getLoad_average() { //accessor method which returns the load average for 3 time periods as a string
		return load_average;
	}

	public void setLoad_average(String load_average) {  //mutator method which sets the load average string
		this.load_average = load_average;
	}

	public String getIpv4() { //accessor method which returns the IPv4 address
		return ipv4;
	}

	public void setIpv4(String ipv4) { //mutator method which sets the IPv4 address
		this.ipv4 = ipv4;
	}





}