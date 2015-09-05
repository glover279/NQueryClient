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
    private static String host="jdbc:derby:nodequery;create=true";
    private static String uname="robert";
    private static String pass="password";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String key1="";
public DB(){
//createConnection();

}
 public static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            //conn = DriverManager.getConnection(host, uname, pass); 
            conn = DriverManager.getConnection(host); 
            System.out.println("Successfully Connected to Database");
            JOptionPane.showMessageDialog(null, "Database Connection Successful");
        }
        catch (Exception except)
        {
            except.printStackTrace();
            System.out.println("Database connection failed: "+except.getMessage());
            Component frame = null;
            JOptionPane.showMessageDialog(null, "Database connection failed: "+except.getMessage());
        }
    }
 
 public void CreateTables() throws SQLException
 {      DatabaseMetaData dbm = conn.getMetaData();
    
    
        try {
            ResultSet rs = dbm.getTables(null, null, "TBLSERVERS", null);
    if (rs.next()) {
      System.out.println("Table exists"); 
    } else {
      System.out.println("Table does not exist"); 
      conn.createStatement().execute("create table TBLSERVERS(ID INTEGER not null,AVAILABILITY DOUBLE not null,UPDATE_TIME TIMESTAMP not null,NAME VARCHAR(255) not null,LOADPERC DOUBLE not null,LOAD_AVE VARCHAR(255) not null,IPV4 VARCHAR(255),NUMBER INTEGER not null,primary key (UPDATE_TIME, NUMBER))");
    }
    
    ResultSet rs1 = dbm.getTables(null, null, "TBLACCOUNT", null);
    if (rs1.next()) {
      System.out.println("Table exists"); 
    } else {
      System.out.println("Table does not exist"); 
      conn.createStatement().execute("create table TBLACCOUNT(NAME VARCHAR(255),TIMEZONE INTEGER,TOTALAPICALLS INTEGER,DETECTEDRATE_LIMIT INTEGER,MAX_ALLOWEDSERVERS INTEGER,APIKEY VARCHAR(255),UNIXTIME TIMESTAMP not null,NUMBER INTEGER not null,primary key (UNIXTIME, NUMBER))");
    }
    
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
  public static void insertIntoAcc(String Name,int tz,int totreq,int ratelim, int maxserv, String API) throws SQLException, MalformedURLException
    {
        try
        {   Account acc1=new Account();
            if(acc1.checkSuccessfulResponse()==true)
            {
            JOptionPane.showMessageDialog(null, "API Key Valid");
            stmt = conn.createStatement();
            Statement stmt3 = conn.createStatement();
            Account acc=new Account();
            ResultSet rownumber=stmt3.executeQuery("SELECT COUNT (*) AS count1 FROM TBLACCOUNT");
            rownumber.next();
           
            int rnum=rownumber.getInt(1);
            System.out.println(rnum);
            String I_into="INSERT INTO TBLACCOUNT (NAME, TIMEZONE, TOTALAPICALLS, DETECTEDRATE_LIMIT, MAX_ALLOWEDSERVERS, APIKEY, UNIXTIME, NUMBER) "+"VALUES("+"'"+Name+"'"+","+tz+","+totreq+","+ratelim+","+maxserv+",'"+API+"'"+",'"+acc.getUnixTime()+"', "+rnum+")";
            ResultSet rs3=stmt3.executeQuery("SELECT COUNT (*) AS count FROM TBLACCOUNT WHERE UNIXTIME="+"'"+acc.getUnixTime()+"'");
            
           
            rs3.next();
            
            System.out.println(rnum);
            if(rs3.getInt(1)==0){
                stmt.executeUpdate(I_into);}
            else{System.out.println("CANNOT INSERT, UPDATING");
            stmt.execute("UPDATE TBLACCOUNT" + " SET"+" NAME='"+Name+"', TIMEZONE="+tz+", TOTALAPICALLS="+totreq+", DETECTEDRATE_LIMIT="+ratelim+", MAX_ALLOWEDSERVERS="+maxserv+", APIKEY='"+API+"'"+", UNIXTIME='"+acc.getUnixTime()+"',"+" NUMBER="+rnum ); }
            
         
            stmt.close();
            System.out.println("NodeQuery Account Sucessfully Updated");
            JOptionPane.showMessageDialog(null, "NodeQuery Account Statistics Successfully Updated");
            }else{JOptionPane.showMessageDialog(null, "API Key Invalid");}
            
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            System.out.println(sqlExcept.getMessage());
            System.out.println("Failed to update account"); 

        }
    }
    
  public static void setAPIkey(String key)
        {
            key1=key;
        
           
        }
public String getAPIkey()
{
return key1;
}
  
public static int getNumberOfRows() throws SQLException{
    stmt = conn.createStatement();
            Statement stmt3 = conn.createStatement();
     ResultSet rownumber=stmt3.executeQuery("SELECT COUNT (*) AS count1 FROM TBLACCOUNT");
            rownumber.next();
           
            int rnum=rownumber.getInt(1);
            System.out.println(rnum);
            return rnum;

}

public static int getNumberOfRowsTBLSERVERS() throws SQLException{
    stmt = conn.createStatement();
            Statement stmt4 = conn.createStatement();
     ResultSet rownumber=stmt4.executeQuery("SELECT COUNT (*) AS count1 FROM TBLSERVERS");
            rownumber.next();
           
            int rnum=rownumber.getInt(1);
            System.out.println(rnum);
            return rnum;

}


public static int getFromServDBint(String Column, String Server, int number) //single value output supported, default record value should be set to 1
  {
       int temp = 0;
        try {
            Statement get =conn.createStatement();
             String sql= "SELECT "+Column+" FROM TBLSERVERS WHERE NAME='"+Server+"'" ;
             ResultSet rs = get.executeQuery(sql);
             rs.next();
             rs.getInt(Column);
 temp = rs.getInt(Column); 
 
 

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        return temp;
  
  }

public static String getFromServDBString (String Column, int number) //single value output supported, default record value should be set to 1
  {
       String temp = null;
        try {
            Statement get =conn.createStatement();
             String sql= "SELECT "+Column+" FROM TBLSERVERS WHERE NUMBER="+number;
             ResultSet rs = get.executeQuery(sql);
             rs.next();
             rs.getString(1);
 temp = rs.getString(1); 
 
 

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
       return temp;
  
  }

  public static String getFromAccDBString(String table,String Column, int record) //single value output supported, default record value should be set to 1
  {
       String temp = null;
        try {
            Statement get =conn.createStatement();
             String sql= "SELECT "+Column+" FROM "+table+" WHERE NUMBER ="+record;
             ResultSet rs = get.executeQuery(sql);
             rs.next();
             rs.getString(Column);
 temp = rs.getString(Column); 
 
 

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        return temp;
  
  }
  
  
  public static String[] getFromServDBName()  //single value output supported, default record value should be set to 1
  {
      Account acc=new Account();
       String temp = null;
       String[] names = null;
       names = new String[100];
        try {
            
            
            Statement get =conn.createStatement();
             String sql= "SELECT NAME FROM TBLSERVERS"; //distinct and group by produce missing values
             ResultSet rs = get.executeQuery(sql);
             rs.next();
             int i=0;
            while(rs.next()==true)
            {names[i]=rs.getString(1);
            rs.next();
            i++;
            }
System.out.println("SERVER"+names[1]);
 System.out.println("SERVER"+names[0]);
 System.out.println("SERVER"+names[2]);
 System.out.println("SERVER"+names[3]);
System.out.println("SERVER"+names[4]);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        return names;
  
  }
  
   public static int getFromAccDBint(String table,String Column, int record) //single value output supported, default record value should be set to 1
  {
       int temp = 0;
        try {
            Statement get =conn.createStatement();
             String sql= "SELECT "+Column+" FROM "+table+"WHERE NUMBER="+record;
             ResultSet rs = get.executeQuery(sql);
             rs.getString(Column);
 temp = rs.getInt(Column); 
 
 

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        return temp;
  
  }
   
   
   public static void insertIntoSL(int id, Double avail, java.sql.Timestamp update_tm, String name, double load_percentage, String load_average, String ipv4) throws SQLException
    {
        try {
           
            stmt = conn.createStatement();
            Statement stmt7 = conn.createStatement();
           
            String I_into="INSERT INTO TBLSERVERS (ID, AVAILABILITY, UPDATE_TIME, NAME, LOADPERC, LOAD_AVE, IPV4, NUMBER) "+"VALUES("+id+","+avail+",'"+update_tm+"','"+name+"',"+load_percentage+",'"+load_average+"'"+",'"+ipv4+"',"+getNumberOfRowsServer()+")";
           ResultSet rs4=stmt7.executeQuery("SELECT COUNT (*) AS count FROM TBLSERVERS WHERE UPDATE_TIME='"+update_tm+"'");
           rs4.next();
           System.out.println("VALUE1= "+rs4.getInt(1));
            
           if(rs4.getInt(1)==0){
                stmt.executeUpdate(I_into,  Statement.RETURN_GENERATED_KEYS);
           
           System.out.println("NodeQuery Servers Sucessfully Inserted");
           }
            else{System.out.println("CANNOT INSERT, UPDATING");
           //stmt.execute("UPDATE TBLSERVERS" + " SET"+" ID="+id+", AVAILABILITY="+avail+", UPDATE_TIME="+update_tm+", NAME='"+name+"', LOADPERC="+load_percentage+", LOAD_AVE='"+load_average+"'"+", IPV4='"+ipv4+"'");
            stmt.close();
            System.out.println("Account already up to date");
           //System.out.println(stmt);
           }
        }
            catch (SQLException sqlExcept)
        {
            System.out.println(stmt.toString());
            sqlExcept.printStackTrace();
            System.out.println("MESSAGE: "+ sqlExcept.getMessage());
            System.out.println("Failed to update Server"); 
        
        } 

        
    }
  
   public static int getNumberOfRowsServer() throws SQLException{
    stmt = conn.createStatement();
            Statement stmt3 = conn.createStatement();
     ResultSet rownumber=stmt3.executeQuery("SELECT COUNT (*) AS count1 FROM TBLSERVERS");
            rownumber.next();
           
            int rnum=rownumber.getInt(1);
            System.out.println(rnum);
            return rnum;

}
   
   public ResultSet ServerData()
   {
       ResultSet rs = null;
        try {
            ServerData sd=new ServerData();
            System.out.println("TEXT:"+sd.getSQLquery());
            
            String query="SELECT * FROM TBLSERVERS";
            PreparedStatement pst=conn.prepareStatement(query);
             rs=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
           
        }
  return rs;
   }
}    
    

   
   


    


    

