package moviebookingsystem.add.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
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
public class AddUserController implements Initializable {
    @FXML
    private JFXTextField f_name;
    @FXML
    private JFXTextField sname;
    @FXML
    private JFXTextField phone_number;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private AnchorPane rootPane;
  
    DatabaseHandler databaseHandler;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         databaseHandler = new DatabaseHandler();
    }    

    @FXML
    private void save(ActionEvent event) {
        CustomerId cst = new CustomerId();
        String First_Name = f_name.getText();
        String S_Name = sname.getText();
        String Phone_Number = phone_number.getText();
        String Address = address.getText();
        if (First_Name.isEmpty()||S_Name.isEmpty()||Phone_Number.isEmpty()||Address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter all the fields");
            alert.showAndWait();
            
            return;
    }
     String qu = "INSERT INTO CUSTOMER(customer_id,f_name,s_name,phone,address) VALUES("+
                "'" + cst.cstId()+"',"+
                "'" + First_Name.toLowerCase() +"',"+
                "'" + S_Name.toUpperCase() +"',"+
                "'" + Phone_Number +"',"+
                "'" + Address +"'"+
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
    
}
class CustomerId{
    public String cstId(){
     String s = "CST-";
        double d;
        for (int i = 1; i <= 12; i++) {
            d = Math.random() * 10;
            s = s + ((int)d);
            if (i % 4 == 0 && i != 12) {
                s = s + "-";
            }
        }   
         return s;
    }
 }