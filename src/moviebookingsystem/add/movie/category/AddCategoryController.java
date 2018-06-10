/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem.add.movie.category;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import moviebookingsystem.databaseHandler.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Peti Ibhuzah
 */
public class AddCategoryController implements Initializable {
    @FXML
    private JFXTextField cat_name;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;

    //01. Including The Database Hundler Object
    DatabaseHandler databaseHandler;
    private AnchorPane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //02.Initializing the Database Hundler Object
       databaseHandler = new DatabaseHandler();
       //09.Creating The function for fetching Data(Writing The query for fetching data)
       checkData(); 
    }    

    @FXML
    private void save(ActionEvent event) {
        //03. Declaring the Fields from the FXML
       CategoryId cat = new CategoryId();
        String CategoryName = cat_name.getText();
        //04. Checking if fields are Empty
        if (CategoryName.isEmpty()) {
            //06. Creating an Alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter all the fields");
            alert.showAndWait();
            
            return;
        }
        
//        id varchar(200) primary key,\n"
//                         +"    cat_name varchar(200),\n"
//                         +"    available boolean default true"
        //07 Creating the SQL query
        String qu = "INSERT INTO CATEGORY(id,cat_name,available) VALUES("+
                "'" + cat.catid()+"',"+
                "'" + CategoryName +"',"+
                "'" + "true" +"'"+
                ")";
        //In order to see the query
        System.out.println(qu);
        //08. Executing the query using Database Handler Object
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
        //12. Creating The Stage Object of type Stage through rootpane using getscene() and getwindow() methods
        Stage stage = (Stage) rootPane.getScene().getWindow();
       stage.close();
    }

    private void checkData() {
        //10. Creating the query
        String qu = "SELECT cat_name FROM CATEGORY";
        //11.Result Object for storing the returned values
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            //11.Printing Out values
            while(rs.next()){
                String category_name = rs.getString("cat_name");
                System.out.println(category_name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
class CategoryId{
    public String catid(){
    String c = "CAT-";
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