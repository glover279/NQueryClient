/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodequeryclient;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Robert
 */
public class ServerList {
    private String key;
   
    DB db10=new DB();
    public ServerList() throws SQLException {setServer();}
   
    public String getServerList()
    
    {    String slist;
        Account acc6=new Account();
        RestTemplate resttemplate = new RestTemplate();
        String url="https://nodequery.com/api/servers?api_key="+acc6.getAPI();
     slist = resttemplate.getForObject(url, String.class);
     
     System.out.println(slist);
    return slist;
    
    }
    
    public void setServer() throws SQLException     
    {
        
        //JSONObject obj = new JSONObject(getServerList());
        //String temp = obj.getJSONObject("data").toString();
        String tempSL=getServerList().substring(66);
        System.out.println("TEMP: "+tempSL);
        String[] sList=new String[500];
        Scanner scan=new Scanner(tempSL).useDelimiter("},");
        String temp2=scan.next();
        //Scanner scan2=new Scanner(temp2).useDelimiter("},");
       int i=0; 
       while (scan.hasNext()){
       sList[i]=scan.next();
       i++;
       //scan.close();
       }
       
       int p=0;
       while (sList[p]!=null && sList[p].length()>0)
       {
       Scanner scan3=new Scanner(sList[p]).useDelimiter(",");
       System.out.println("SCAN3:  "+sList[0]);
       
     String  id=scan3.next();
     
     String status=scan3.next();
    String  availability=scan3.next();
    String  update_time=scan3.next();
    String  name=scan3.next();
    String  loadpercentage=scan3.next();
    String  load_average=scan3.next();
    String ram_total=scan3.next();
    String ram_usage_=scan3.next();
    String disk_total=scan3.next();
    String disk_usage =scan3.next();
    
    String  ipv4=scan3.next();
    //scan3.close();
       
       
      int idIndex=id.indexOf(":");
      int statusIndex=status.indexOf(":");
      int availabilityIndex=availability.indexOf(":");
      int update_timeIndex=update_time.indexOf(":");
      int nameIndex=name.indexOf(":");
      int loadpercentageIndex=loadpercentage.indexOf(":");
      int load_averageIndex=load_average.indexOf(":");
      int ipv4Index=ipv4.indexOf(":");
       
       System.out.println("IDdebug "+id);
      id=id.substring(idIndex+2).replace('"', ' ');
      System.out.println("ID: "+id);
      int idInt=Integer.parseInt(id);
      status=status.substring(statusIndex+2).replace('"', ' ');
      System.out.println("STATUS: "+status);
      availability=availability.substring(availabilityIndex+2).replace('"', ' ');
      int alen=availability.length();
      String availabilityConv=availability.substring(0, alen-2);
      
      System.out.println("AVAIL: "+availabilityConv);
      Double availabilityDouble=Double.parseDouble(availabilityConv);
      
      update_time=update_time.substring(update_timeIndex+2).replace('"', ' ');
      System.out.println(update_time);
      int update_timeInt=Integer.parseInt(update_time);
      name=name.substring(nameIndex+2).replace('"', ' ');
      loadpercentage=loadpercentage.substring(loadpercentageIndex+2).replace('"', ' ');
      double loadpercentageDouble=Double.parseDouble(loadpercentage);
      load_average=load_average.substring(load_averageIndex+2).replace('"', ' ');
      ipv4=ipv4.substring(ipv4Index+2).replace('"', ' ');
      java.sql.Timestamp time=new java.sql.Timestamp((long)update_timeInt*1000);
     
      System.out.println("ID: "+idInt);
     // print these, ip incorrect match
      System.out.println(idInt+"STAT: "+status+"ava: "+availabilityDouble+" updaTM: "+time+" NM: "+name+" LPERC: "+loadpercentageDouble+" LA: "+load_average+"ip: "+ipv4);
      Server temp5=new Server(idInt,availabilityDouble,time,name,loadpercentageDouble,load_average,ipv4);
      System.out.println("LA: "+temp5.getLoad_average());
       p++;//id,  availability,  update_time,  name,  loadpercentage,  load_average,  ipv4
       //first server not added, check delimeter
       //scan3.close();
       }
       scan.close();
       
       
    }
   
}
