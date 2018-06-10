package moviebookingsystem.add.movies;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import moviebookingsystem.databaseHandler.DatabaseHandler;

public class AddMovieController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField movie_name;
    @FXML
    private JFXComboBox<String> company_combo;
    @FXML
    private JFXComboBox<String> category_combo;
    @FXML
    private JFXTextField cinema_hall;
    @FXML
    private JFXTextField qty;
    @FXML
    private JFXTextField mv_description;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    
    DatabaseHandler databaseHandler;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       databaseHandler = new DatabaseHandler();
        setCompanyToCombo();
        setCategoryToCombo(); 
    }    

    @FXML
    private void save(ActionEvent event) {
        UniqueId obj = new UniqueId();
        String Movie_Name = movie_name.getText();
        String Movie_Company = company_combo.getSelectionModel().getSelectedItem();
        String Movie_Category = category_combo.getSelectionModel().getSelectedItem();
        String Cinema_Hall = cinema_hall.getText();
        String price = qty.getText();
        String Description = mv_description.getText();
        if(Movie_Name.isEmpty()||Movie_Company.isEmpty()||Movie_Category.isEmpty()||Cinema_Hall.isEmpty()||price.isEmpty()||Description.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter all the fields");
            alert.showAndWait();
            return;
        }
//         +"    movie_id varchar(200) primary key,\n"
//                         +"    movie_name varchar(200),\n"
//                         +"    movie_company varchar(200),\n"
//                         +"    movie_category varchar(200),\n"
//                         +"    cinema_hall varchar(200),\n"
//                         +"    price varchar(200),\n"
//                         +"    descriptions varchar(200),\n"
//                         +"    available boolean default true,\n"
//                         +"    created_at timestamp default current_timestamp"
        String qu = "INSERT INTO MOVIE(movie_id,movie_name,movie_company,movie_category,cinema_hall,price,descriptions,available) VALUES("+
                "'" + obj.id()+"',"+
                "'" + Movie_Name +"',"+
                "'" + Movie_Company +"',"+
                "'" + Movie_Category +"',"+
                "'" + Cinema_Hall +"',"+
                 "'"+ price+"',"+
                 "'"+ Description+"',"+
                "'" + "true" +"'"+
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


    @FXML
    private void cancel(ActionEvent event) {
    }
    
     private void setCompanyToCombo() {
       try {
           String quu = "SELECT company_name FROM COMPANY";
           ResultSet rs = databaseHandler.execQuery(quu);
            while(rs.next()){
                company_combo.getItems().addAll(rs.getString("company_name"));             
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     private void setCategoryToCombo() {
         try {
           String que = "SELECT cat_name FROM CATEGORY";
           ResultSet rs = databaseHandler.execQuery(que);
            while(rs.next()){
                category_combo.getItems().addAll(rs.getString("cat_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
class UniqueId{
    public String id(){
    String c = "MV-";
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