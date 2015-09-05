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
    String [] names=DB.getFromServDBName();
    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
     ChoiceBox<String> choicebox =new ChoiceBox<>();
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
 
  public void setStats() throws SQLException
  {     
       choicebox.setLayoutX(500);
       choicebox.setLayoutY(500);
      
       
        String temp = choicebox.getValue();
       // choicebox.getSelectionModel().clearSelection();
       //  choicebox.getItems().clear();
       
       //choicebox.setValue(temp);
      
        int totapi1=DB.getFromServDBint("AVAILABILITY",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-5);
        int totapi2=DB.getFromServDBint("AVAILABILITY",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-4);
        int totapi3=DB.getFromServDBint("AVAILABILITY",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-3);
        int totapi4=DB.getFromServDBint("AVAILABILITY",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-2);
        int totapi5=DB.getFromServDBint("AVAILABILITY",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-1);
        
        int load1=DB.getFromServDBint("LOADPERC",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-5);
        int load2=DB.getFromServDBint("LOADPERC",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-4);
        int load3=DB.getFromServDBint("LOADPERC",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-3);
        int load4=DB.getFromServDBint("LOADPERC",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-2);
        int load5=DB.getFromServDBint("LOADPERC",choicebox.getValue(), DB.getNumberOfRowsTBLSERVERS()-1);
        
        
        //System.out.println("UNIXTIME"+db3.getFromAccDBString("TBLACCOUNT","UNIXTIME", DB.getNumberOfRows()-4));
        //System.out.println("apicalls:"+totapi2);

        series1.setName("Availability Percent");       
        series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-5), totapi1 ));
        series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-4), totapi2 ));
        series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-3), totapi3 ));
        series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-2), totapi4 ));
        series1.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-1), totapi5 ));
        
        
        series2.setName("Load %");
        series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-5), load1 ));
        series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-4), load2 ));
        series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-3), load3 ));
        series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-2), load4 ));
        series2.getData().add(new XYChart.Data(DB.getFromServDBString("UPDATE_TIME", DB.getNumberOfRowsTBLSERVERS()-1), load5 ));
        
       
         
        
  }
  
    @Override public void start(Stage stage) throws SQLException  {
       DB db3 =new DB();
       choicebox.setValue(names[0]);
        int i=0;
       while(names[i]!=null)
       {
       choicebox.getItems().add(names[i]);
       i++;
       }
       setStats();
       
        
       
        stage.setTitle("Server Statistics reported by NodeQuery agents");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.autosize();
        
        bc.setTitle("Server Statistics");
        //xAxis.setLabel("Country");       
        yAxis.setLabel("Value");
        xAxis.setLabel("Update Time");
        
       
        
         VBox root = new VBox();
         root.getChildren().addAll(choicebox, bc);
        
        Scene scene  = new Scene(root,800,400);
        bc.getData().addAll(series1,series2);
        stage.setScene(scene);
        stage.show();
         
         choicebox.valueProperty().addListener(new ChangeListener<String>() 
         {
        @Override public void changed(ObservableValue ov, String t, String t1) {
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

 public void setVisible(boolean value){setVisible(value);
                                                            }
    public static void main(String[] args) {
        launch(args);
        
    }
}