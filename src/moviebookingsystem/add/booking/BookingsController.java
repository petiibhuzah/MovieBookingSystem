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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import moviebookingsystem.add.movies.AddMovieController;
import moviebookingsystem.databaseHandler.DatabaseHandler;
import moviebookingsystem.list.users.ListUsersController;

/**
 * FXML Controller class
 *
 * @author Peti Ibhuzah
 */
public class BookingsController implements Initializable {
     ObservableList<Bookings> list = FXCollections.observableArrayList();
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
    private TableView<Bookings> tableTickets;
    @FXML
    private Label lblTicketsSold;
    @FXML
    private Label lblTotalSales;
 
    DatabaseHandler databaseHandler;
    @FXML
    private JFXComboBox<String> movie_combo;
    @FXML
    private TableColumn<Bookings, String> customer_col;
    @FXML
    private TableColumn<Bookings, String> movie_col;
    @FXML
    private TableColumn<Bookings, String> date_col;
    @FXML
    private TableColumn<Bookings, String> session_col;
    private TableColumn<Bookings, String> customer_col1;
    @FXML
    private TableColumn<Bookings, String> bookings_id_col;
    @FXML
    private TableColumn<Bookings, String> created_at_col;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = new DatabaseHandler();
        setUserToCombo();
        setMovieToCombo(); 
        initCol();
        loadData();
    }    
    private void initCol() {
        bookings_id_col.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
        customer_col.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        movie_col.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
        session_col.setCellValueFactory(new PropertyValueFactory<>("session"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        created_at_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
    }

    private void loadData()  {
         DatabaseHandler databaseHandler = new DatabaseHandler();
        //10.Using The code as Check data in adding category package
        
         // Creating the query
        String qu = "SELECT * FROM BOOKING";
        String ct = "SELECT COUNT(booking_id) FROM BOOKING";
        
        ResultSet rs1 = databaseHandler.execQuery(ct);
       
        System.out.println("The Total number:"+rs1);
        //Result Object for storing the returned values
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
// stmt.execute("CREATE TABLE "+TABLE_NAME+"("
//                         +"    booking_id varchar(200) primary key,\n"
//                         +"    movie_name varchar(200),\n"
//                         +"    customer_name varchar(200),\n"
//                         +"    booking_date varchar(200),\n"
//                         +"    session varchar(200),\n"
//                         +"    created_at timestamp default current_timestamp"
//                         +" )");
            while(rs.next()){
                String booking_id = rs.getString("booking_id");
                String movie_name = rs.getString("movie_name");
                String customer_name = rs.getString("customer_name");
                String booking_date = rs.getString("booking_date");
                String session = rs.getString("session");
                String created_at = rs.getString("created_at");
                
                //12.Adding the values in a list
                list.add(new BookingsController.Bookings(booking_id ,movie_name,customer_name,booking_date,session,created_at));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //13.Making the realtion between Table view and list
        tableTickets.getItems().setAll(list);
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

public static class Bookings{
    // 02.Displaying the Properties using SimpleDatatypesProperties
        private final SimpleStringProperty booking_id;
        private final SimpleStringProperty customer_name;
        private final SimpleStringProperty movie_name;
        private final SimpleStringProperty date;
        private final SimpleStringProperty session;
        private final SimpleStringProperty created_at;
        
    //03. Constructor for initialization of the properties using SimpleDataTypeProperties
        Bookings(String booking_id,String customer_name,String movie_name,String date,String session,String created_at ){
            this.booking_id = new SimpleStringProperty(booking_id);
            this.customer_name = new SimpleStringProperty(customer_name);
            this.movie_name = new SimpleStringProperty(movie_name);
            this.date = new SimpleStringProperty(date);
            this.session = new SimpleStringProperty(session);
            this.created_at = new SimpleStringProperty(created_at);
        }

        public String getBooking_id() {
            return booking_id.get();
        }

        public String getCustomer_name() {
            return customer_name.get();
        }

        public String getMovie_name() {
            return movie_name.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getSession() {
            return session.get();
        }

        public String getCreated_at() {
            return created_at.get();
        }

       
    
     }
}