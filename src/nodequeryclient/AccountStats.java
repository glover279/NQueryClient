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
    
 
   


 public static int strToInt( String str ){ //converts string to integer
    int i = 0;
    int num = 0;
    boolean isNeg = false;
 
    //Check for negative sign; if it's there, set the isNeg flag 
    if (str.charAt(0) == '-') {
        isNeg = true;
        i = 1;
    } 
 
    //Process each character of the string; 
    while( i < str.length()) {
        num *= 10;
        num += str.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
    } 
 
    if (isNeg)
        num = -num;
    return num;
} 
 
    @Override public void start(Stage stage) throws SQLException {
       DB db3 =new DB();
       
        
        stage.setTitle("NodeQuery Account Statistics");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Account Statistics");
        //xAxis.setLabel("Country");       
        yAxis.setLabel("Value");
        xAxis.setLabel("Update Time");
        int totapi1=strToInt(db3.getFromAccDBString("TBLACCOUNT","TOTALAPICALLS", DB.getNumberOfRows()-5));
        int totapi2=strToInt(db3.getFromAccDBString("TBLACCOUNT","TOTALAPICALLS", DB.getNumberOfRows()-4));
        int totapi3=strToInt(db3.getFromAccDBString("TBLACCOUNT","TOTALAPICALLS", DB.getNumberOfRows()-3));
        int totapi4=strToInt(db3.getFromAccDBString("TBLACCOUNT","TOTALAPICALLS", DB.getNumberOfRows()-2));
        int totapi5=strToInt(db3.getFromAccDBString("TBLACCOUNT","TOTALAPICALLS", DB.getNumberOfRows()-1));
        
        int maxserv1=strToInt(db3.getFromAccDBString("TBLACCOUNT","MAX_ALLOWEDSERVERS", DB.getNumberOfRows()-5));
        int maxserv2=strToInt(db3.getFromAccDBString("TBLACCOUNT","MAX_ALLOWEDSERVERS", DB.getNumberOfRows()-4));
        int maxserv3=strToInt(db3.getFromAccDBString("TBLACCOUNT","MAX_ALLOWEDSERVERS", DB.getNumberOfRows()-3));
        int maxserv4=strToInt(db3.getFromAccDBString("TBLACCOUNT","MAX_ALLOWEDSERVERS", DB.getNumberOfRows()-2));
        int maxserv5=strToInt(db3.getFromAccDBString("TBLACCOUNT","MAX_ALLOWEDSERVERS", DB.getNumberOfRows()-1));
        
        XYChart.Series series1 = new XYChart.Series();
        //System.out.println("UNIXTIME"+db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-4));
        //System.out.println("apicalls:"+totapi2);

        series1.setName("API CALLS");       
        series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-5), totapi1 ));
        series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-4), totapi2));
        series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-3), totapi3));
        series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-2), totapi4));
        series1.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-1), totapi5));      
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Max Servers");
        series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-5), maxserv1));
        series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-4), maxserv2));
        series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-3), maxserv3));
        series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-2), maxserv4));
        series2.getData().add(new XYChart.Data(db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-1), maxserv5));  
        
        
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1, series2);
        stage.setScene(scene);
        stage.show();
    }
 public void setVisible(boolean value){setVisible(value);
                                                            }
    public static void main(String[] args) {
        launch(args);
        
    }
}