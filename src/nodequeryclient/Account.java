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
    DB db9=new DB(); 
    private String api=db9.getAPIkey();
    
    
    
    public void setAPI()
    {
    
    }
public boolean checkSuccessfulResponse() throws MalformedURLException{
URL url=new URL("https://nodequery.com/api/account?api_key="+api);
HttpURLConnection http = null;
int statusCode = 0;

        try {
            http = (HttpURLConnection)url.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            statusCode = http.getResponseCode();
        } catch (IOException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
boolean success;
System.out.println("STATUS CODE: "+statusCode);
if (statusCode==200){success=true;}
else{success=false;}
return success;
}


    public String getAccount() throws HTTPstatusException
{
     String uri = "https://nodequery.com/api/account?api_key="+api;
     
     boolean t2 = false;
        try {
             t2=checkSuccessfulResponse();
             System.out.println("RESP: "+t2);
        } catch (IOException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = null;
      if (t2==true)  {
    RestTemplate restTemplate = new RestTemplate();
     result = restTemplate.getForObject(uri, String.class);}
    else{JOptionPane.showMessageDialog(null, "API Key Invalid");throw new HTTPstatusException("HTTP RESPONSE ERROR, CHECK API KEY");
      }
     System.out.println(result);
    return result;
}

    public String getAPI(){return api;}
    
   
    
    public static void main(String[] args) {
        DB db1=new DB();
        //getAccount();
      //  getName();
        //getRequests();
        
        
        // TODO code application logic here
    }
    
    public boolean getStatus() throws HTTPstatusException
    {
    JSONObject obj = new JSONObject(getAccount());
    String t= obj.toString();
    CharSequence cs1 = "ERROR";
    boolean chk=t.contains(cs1);
    String Name=obj.getJSONObject("data").getString("name");
 //Name = Name.replaceAll("\\s",""); 

    System.out.println("Name: "+Name);
        return chk;
    
    }
    public Timestamp getUnixTime() {
         //Date object
	 Date date= new Date();
         //getTime() returns current time in milliseconds
	 long time = date.getTime();
         //Passed the milliseconds to constructor of Timestamp class 
	 Timestamp ts = new Timestamp(time);
	 System.out.println("Current Time Stamp: "+ts);
return ts;}
    
    
    public String getName() throws HTTPstatusException
    {
    JSONObject obj = new JSONObject(getAccount());
    String Name=obj.getJSONObject("data").getString("name");
 //Name = Name.replaceAll("\\s",""); 

    System.out.println("Name: "+Name);
        return Name;
    
    }
    public int getMaxServ() throws HTTPstatusException
    {
    JSONObject obj = new JSONObject(getAccount());
int sl = obj.getJSONObject("data").getInt("server_limit");

    System.out.println("Timezone: "+sl);
        return sl;
    
    }
    public int getTimezone() throws HTTPstatusException
    {
    JSONObject obj = new JSONObject(getAccount());
int tz = obj.getJSONObject("data").getInt("timezone");

    System.out.println("Timezone: "+tz);
        return tz;
    
    }
    public int getRequests() throws HTTPstatusException
    {
    JSONObject obj = new JSONObject(getAccount());
int req = obj.getJSONObject("data").getJSONObject("api").getInt("requests");

    System.out.println("Requests: "+req);
        return req;
    
    }
    public int getRateLimit() throws HTTPstatusException
    {
    JSONObject obj = new JSONObject(getAccount());
int rl = obj.getJSONObject("data").getJSONObject("api").getInt("rate_limit");

    System.out.println("Requests: "+rl);
        return rl;
    
    }
    public DateTimeZone getTimeInzone() throws HTTPstatusException
    {
        DateTimeZone zoneUTC = DateTimeZone.forOffsetHours(getTimezone());
        return zoneUTC;
    }
    
    
    
    public void InsertAccount() throws SQLException, HTTPstatusException, MalformedURLException
    {
    DB db3=new DB();
    System.out.println("KEY: "+db3.getAPIkey());
    db3.insertIntoAcc(getName(),getTimezone(),getRequests(),getRateLimit(),getMaxServ(),db3.getAPIkey());
    }
    
}
