/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import moviebookingsystem.dashboard.DashboardController;

/**
 * FXML Controller class
 *
 * @author Peti Ibhuzah
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private JFXButton btnLogin;
   
    @FXML
    private JFXTextField user_name;
    @FXML
    private JFXPasswordField pass;

    Preferences preference;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doLogin(ActionEvent event) {
        String uname = (String)user_name.getText().toUpperCase();
        String password = pass.getText();
        
        if (uname=="ADMIN") {
             loadMain();
             closeStage();
//            System.out.println("The Value of Test:"+test);
//            System.out.println("The Value of Username:"+uname);
        }else{
          System.out.println("Wrong User Name ->"+uname);
          System.out.println("Wrong Password:->"+password);
        }
       
        
    }
    private void closeStage() {
       ((Stage) user_name.getScene().getWindow()).close();
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/dashboard/dashboard.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Movie Booking System");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
