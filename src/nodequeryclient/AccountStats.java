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
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class AccountStats extends Application {

	@Override public void start(Stage stage) throws SQLException {
		DB db3 = new DB(); //instantiate new DB object


		stage.setTitle("NodeQuery Account Statistics");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart < String, Number > AccountBc = new BarChart < String, Number > (xAxis, yAxis);
		AccountBc.setTitle("Account Statistics");
		//xAxis.setLabel("Country");       
		yAxis.setLabel("Value");
		xAxis.setLabel("Update Time");
		int totapi1 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 5));
		int totapi2 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 4));
		int totapi3 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 3));
		int totapi4 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 2));
		int totapi5 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "TOTALAPICALLS", DB.getNumberOfRows() - 1));

		int maxserv1 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 5));
		int maxserv2 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 4));
		int maxserv3 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 3));
		int maxserv4 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 2));
		int maxserv5 = DB.strToInt(db3.getFromAccDBString("TBLACCOUNT", "MAX_ALLOWEDSERVERS", DB.getNumberOfRows() - 1));

		XYChart.Series series1 = new XYChart.Series();
		//System.out.println("UNIXTIME"+db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-4));
		//System.out.println("apicalls:"+totapi2);

		series1.setName("API CALLS");
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 5), totapi1));
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 4), totapi2));
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 3), totapi3));
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 2), totapi4));
		series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 1), totapi5));

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Max Servers");
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 5), maxserv1));
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 4), maxserv2));
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 3), maxserv3));
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 2), maxserv4));
		series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT", "UNIXTIME", DB.getNumberOfRows() - 1), maxserv5));



		Scene scene = new Scene(AccountBc, 800, 600);
		AccountBc.getData().addAll(series1, series2);
		stage.setScene(scene);
		stage.show();
	}
	public void setVisible(boolean value) {
		setVisible(value);
	}
	public static void main(String[] args) {
		launch(args);

	}
}