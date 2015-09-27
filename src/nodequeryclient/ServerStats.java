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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServerStats extends Application { 
	String[] names = DB.getFromServDBName();
	XYChart.Series series1 = new XYChart.Series(); // create series one (colour coded column) for bar chart
	XYChart.Series series2 = new XYChart.Series(); // create series two (colour coded column) for bar chart
	ChoiceBox < String > SelectServerCb = new ChoiceBox < > (); // create a new drop down box to select the server


	public void setStats() throws SQLException { //mutator method which sets the values for the bar chart
		SelectServerCb.setLayoutX(500);
		SelectServerCb.setLayoutY(500);


		String temp = SelectServerCb.getValue(); //get currently selected value from choicebox
		

		
                // integer variables which stores the percentage availability for each server, these values are retrieved from the database using the getFromServDBint method in the DB class
		int ava1 = DB.getFromServDBint("AVAILABILITY", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 5); 
		int ava2 = DB.getFromServDBint("AVAILABILITY", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 4);
		int ava3 = DB.getFromServDBint("AVAILABILITY", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 3);
		int ava4 = DB.getFromServDBint("AVAILABILITY", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 2);
		int ava5 = DB.getFromServDBint("AVAILABILITY", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 1);

                // integer variables which stores the percentage load for each server, these values are retrieved from the database using the getFromServDBint method in the DB class
		int load1 = DB.getFromServDBint("LOADPERC", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 5);
		int load2 = DB.getFromServDBint("LOADPERC", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 4);
		int load3 = DB.getFromServDBint("LOADPERC", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 3);
		int load4 = DB.getFromServDBint("LOADPERC", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 2);
		int load5 = DB.getFromServDBint("LOADPERC", SelectServerCb.getValue(), DB.getNumberOfRowsTBLSERVERS() - 1);


		

		series1.setName("Availability Percent"); //sets the label of the first series to indicate the percentage availability
                //these next lines set the values for each column in series one
		series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 5), ava1));
		series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 4), ava2));
		series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 3), ava3));
		series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 2), ava4));
		series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 1), ava5));


		series2.setName("Load %"); //sets the label of the second series to indicate the percentage load
                //these next lines set the values for each column in series two
		series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 5), load1));
		series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 4), load2));
		series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 3), load3));
		series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 2), load4));
		series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS() - 1), load5));




	}

	@Override public void start(Stage stage) throws SQLException { //this method creates a new stage and lays out components that the end user will see
		
		SelectServerCb.setValue(names[0]); //set choicebox default value to the first value on start
		int i = 0; // set counter to start at 0
		while (names[i] != null) {
			SelectServerCb.getItems().add(names[i]); //sets values of data in choicebox by using while loop to run until the names array element doesnt have a value
			i++; //increment counter by one
		}
		setStats(); // call setStats to set values for chart



		stage.setTitle("Server Statistics"); //set title of chart
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart < String, Number > ServStatBc;
		ServStatBc = new BarChart < > (xAxis, yAxis);
		ServStatBc.autosize();

		ServStatBc.setTitle("Server Statistics reported by NodeQuery agents");
		//xAxis.setLabel("Country");       
		yAxis.setLabel("Value");
		xAxis.setLabel("Update Time");



		VBox root = new VBox();
		root.getChildren().addAll(SelectServerCb, ServStatBc);

		Scene scene = new Scene(root, 800, 400);
		ServStatBc.getData().addAll(series1, series2);
		stage.setScene(scene);
		stage.show();

		SelectServerCb.valueProperty().addListener(new ChangeListener < String > () {@Override public void changed(ObservableValue ov, String t, String t1) {
				System.out.println(ov);
				System.out.println(t);
				System.out.println(t1);

				try {
					setStats();
				} catch (SQLException ex) {
					Logger.getLogger(ServerStats.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void setVisible(boolean value) {
		setVisible(value);
	}
	public static void main(String[] args) {
		launch(args);

	}
}