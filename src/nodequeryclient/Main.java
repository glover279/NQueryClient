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
public class Main {
    public static void main(String [] args) throws SQLException, HTTPstatusException
    {
        
    //Account acc1=new Account();
   DB db2=new DB();
    DB.createConnection();
    db2.CreateTables();
    MainUI mui=new MainUI();
    mui.setVisible(true);
    
    
//    System.out.println(acc1.getTimeInzone().toString());
   // System.out.println(db2.getFromAccDBString("TBLACCOUNT","TOTALAPICALLS", 1));
    }
    
}
