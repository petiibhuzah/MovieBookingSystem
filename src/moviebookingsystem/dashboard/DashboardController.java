/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem.dashboard;

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

/**
 * FXML Controller class
 *
 * @author Peti Ibhuzah
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addCategory(ActionEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/add/movie/category/addCategory.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add Category");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addCompany(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/add/movie/company/addCompany.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add Comapany");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addMovie(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/add/movies/addMovie.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add Movie");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addCustomer(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/add/user/addUser.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add Customer");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bookTicket(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/add/booking/bookings.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Book Ticket");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listategories(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/list/categories/listCategories.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("List of Categories");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listCompanies(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/list/movie/companies/listCompanies.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("List of  Companies");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listMovies(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/list/movies/listMovies.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add Category");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listCustomers(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/moviebookingsystem/list/users/listUsers.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add Category");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
