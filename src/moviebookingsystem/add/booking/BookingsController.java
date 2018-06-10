/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem.add.booking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import moviebookingsystem.add.movies.AddMovieController;
import moviebookingsystem.databaseHandler.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Peti Ibhuzah
 */
public class BookingsController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXComboBox<String> customer_combo;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private JFXTextField txtSession;
    @FXML
    private JFXButton btnPay;
    @FXML
    private TableView<?> tableTickets;
    @FXML
    private Label lblTicketsSold;
    @FXML
    private Label lblTotalSales;
 
    DatabaseHandler databaseHandler;
    @FXML
    private JFXComboBox<String> movie_combo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = new DatabaseHandler();
        setUserToCombo();
        setMovieToCombo(); 
    }    

    @FXML
    private void bookTicket(ActionEvent event) {
    Booking obj = new Booking();
        //LocalDate
        LocalDate Date = txtDate.getValue();
        String Session = txtSession.getText();
        String Movie = movie_combo.getSelectionModel().getSelectedItem();
        String Customer = customer_combo.getSelectionModel().getSelectedItem();
        
        if(Session.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter all the fields");
            alert.showAndWait();
            return;
      }
//        stmt.execute("CREATE TABLE "+TABLE_NAME+"("
//                         +"    booking_id varchar(200) primary key,\n"
        //                       movie_name
//                         +"    customer_name varchar(200),\n"
//                         +"    booking_date varchar(200),\n"
//                         +"    session varchar(200),\n"
//                         +"    created_at timestamp default current_timestamp"
//                         +" )");
        String qu = "INSERT INTO BOOKING(booking_id,movie_name,customer_name,booking_date,session) VALUES("+
                "'" + obj.id()+"',"+
                "'" + Movie+"',"+
                "'" + Customer +"',"+
                "'" + Date +"',"+
                "'" + Session +"'"+
                ")";
         System.out.println(qu);
        if (databaseHandler.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        }else{ //Error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }
    private void setMovieToCombo() {
       try {
           String quu = "SELECT movie_name,movie_id FROM MOVIE";
           ResultSet rs = databaseHandler.execQuery(quu);
            while(rs.next()){
                movie_combo.getItems().addAll(rs.getString("movie_name"));  
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     private void setUserToCombo() {
         try {
           String que = "SELECT customer_id,s_name FROM CUSTOMER";
           ResultSet rs = databaseHandler.execQuery(que);
            while(rs.next()){
                customer_combo.getItems().addAll(rs.getString("s_name"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

class Booking{
    public String id(){
    String c = "BK-";
    double cm;
    for(int i=1; i<5; i++){
        cm = Math.random() *10;
        c = c + ((int)cm);
        if (i % 4 == 0 && i !=4) {
            c = c + "-";
        }
    }
         return c;
    }
}
}