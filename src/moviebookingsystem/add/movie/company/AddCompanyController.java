
package moviebookingsystem.add.movie.company;

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
import moviebookingsystem.databaseHandler.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Peti Ibhuzah
 */
public class AddCompanyController implements Initializable {
    @FXML
    private JFXTextField cp_name;
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
       UniqueId obj = new UniqueId();
        String CompanyName = cp_name.getText();
        //04. Checking if fields are Empty
        if (CompanyName.isEmpty()) {
            //06. Creating an Alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter all the fields");
            alert.showAndWait();
            
            return;
    }
                
//     +"    id varchar(200) primary key,\n"
//                         +"    company_name varchar(200),\n"
//                         +"    available boolean default true,\n"
//                         +"    created_at timestamp default current_timestamp"
//                         +" )");
        //07 Creating the SQL query
        String qu = "INSERT INTO COMPANY(id,company_name,available) VALUES("+
                "'" + obj.id()+"',"+
                "'" + CompanyName +"',"+
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
    }
     private void checkData() {
        //10. Creating the query
        String qu = "SELECT company_name FROM COMPANY";
        //11.Result Object for storing the returned values
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            //11.Printing Out values
            while(rs.next()){
                String category_name = rs.getString("company_name");
                System.out.println(category_name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddCompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
class UniqueId{
    public String id(){
    String c = "CP-";
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