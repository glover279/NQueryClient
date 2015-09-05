/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodequeryclient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public Server(int id, Double availability, java.sql.Timestamp update_time, String name, double loadpercentage, String load_average, String ipv4) throws SQLException {
        this.id = id;
        this.availability = availability;
        //this.update_time = update_time;
        this.name = name;
        this.loadpercentage = loadpercentage;
        this.load_average = load_average;
        this.ipv4 = ipv4;
        
        DB.insertIntoSL( id,  availability,  update_time,  name,  loadpercentage,  load_average,  ipv4);
    }

   

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAvailability() {
        return availability;
    }

    public void setAvailability(Double availability) {
        this.availability = availability;
    }

    public double getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(double update_time) {
        this.update_time = update_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLoadpercentage() {
        return loadpercentage;
    }

    public void setLoadpercentage(int loadpercentage) {
        this.loadpercentage = loadpercentage;
    }

    public String getLoad_average() {
        return load_average;
    }

    public void setLoad_average(String load_average) {
        this.load_average = load_average;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }
    
    
    
    
    
}
