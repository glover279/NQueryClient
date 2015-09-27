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
	public static void main(String[] args) throws SQLException, HTTPstatusException {

		//Account acc1=new Account();
		DB db2 = new DB();  //instantiates a new DB object, called db2
		DB.createConnection(); //creates a new connection to the database
		db2.CreateTables(); //checks if the tables exist and creates them if they do not
		MainUI mui = new MainUI(); //creates a new instance of the main UI
		mui.setVisible(true); //makes the main UI visible 


		
	}

}