/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodequeryclient;

/**
 *
 * @author Robert
 */
import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class AccountStats extends Application {

	@Override public void start(Stage stage) throws SQLException {
		DB db3 = new DB(); //instantiate new DB object


		stage.setTitle("NodeQuery Account Statistics"); // set title of window
		final CategoryAxis xAxis = new CategoryAxis(); //create x axis
		final NumberAxis yAxis = new NumberAxis(); //create y axis
		final BarChart < String, Number > AccountBc = new BarChart < String, Number > (xAxis, yAxis); //create barchart
		AccountBc.setTitle("Account Statistics"); //set chart title
		       
		yAxis.setLabel("Value"); //set label of y axis
		xAxis.setLabel("Update Time"); //set label of x axis
                //set number of api requests from database
		int totapi1 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 5));
		int totapi2 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 4));
		int totapi3 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 3));
		int totapi4 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 2));
		int totapi5 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 1));
                 
                //set maximum supported servers from database
		int maxserv1 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 5));
		int maxserv2 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 4));
		int maxserv3 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 3));
		int maxserv4 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 2));
		int maxserv5 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 1));
                
                //create new series fro bar chart
		XYChart.Series series1 = new XYChart.Series();
		
                
		series1.setName("API CALLS"); //set name of series (label)
                //set x axis data on graph from database
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 5), totapi1));
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 4), totapi2));
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 3), totapi3));
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 2), totapi4));
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 1), totapi5));
                
                //create new series of data
		XYChart.Series series2 = new XYChart.Series(); 
		series2.setName("Max Servers"); //set label (name) of series
                //set x axis data from database (time)
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 5), maxserv1));
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 4), maxserv2));
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 3), maxserv3));
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 2), maxserv4));
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 1), maxserv5));


		Scene scene = new Scene(AccountBc, 800, 600); //instantiate new scene
		AccountBc.getData().addAll(series1, series2); // add data to chart
		stage.setScene(scene); //set the scene
		stage.show(); //display the scene
                stage.setOnCloseRequest(e -> Platform.exit());
	}
	public void setVisible(boolean value) {
		setVisible(value);
	}
	public static void main(String[] args) { //main method
		launch(args);

	}
}