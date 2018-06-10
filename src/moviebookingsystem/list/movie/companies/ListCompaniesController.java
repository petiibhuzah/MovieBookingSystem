/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem.list.movie.companies;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import moviebookingsystem.databaseHandler.DatabaseHandler;
import moviebookingsystem.list.categories.ListCategoriesController;

/**
 * FXML Controller class
 *
 * @author Peti Ibhuzah
 */
public class ListCompaniesController implements Initializable {
     ObservableList<Company> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Company> tableView;
    @FXML
    private TableColumn<Company, String> cp_id_col;
    @FXML
    private TableColumn<Company, String> cp_name_col;
    @FXML
    private TableColumn<Company, Boolean> avail_col;
    @FXML
    private TableColumn<Company, String> created_at_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initCol();
        
        //08.Creating the LoadData function for loading the Data from the Database
        loadData();
    }  
    private void initCol() {
        //07.setting the value using setCellValueFactory and PropertyValueFactory Instance specifying the columns we declared when creating Category class
        cp_id_col.setCellValueFactory(new PropertyValueFactory<>("company_Id"));
        cp_name_col.setCellValueFactory(new PropertyValueFactory<>("company_name"));
        avail_col.setCellValueFactory(new PropertyValueFactory<>("availability"));
        created_at_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        
    }

    private void loadData() {
        //09.Creating The Database Hundler Object Locally for database connectivity
        DatabaseHandler databaseHandler = new DatabaseHandler();
        //10.Using The code as Check data in adding category package
        
         // Creating the query
        String qu = "SELECT * FROM COMPANY";
        System.out.println(qu);
        //Result Object for storing the returned values
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            //Printing Out values
            while(rs.next()){
                String company_id = rs.getString("id");
                String company_name = rs.getString("company_name");
                Boolean Available = rs.getBoolean("available");
                String created_at = rs.getString("created_at");
                
                //12.Adding the values in a list
                list.add(new ListCompaniesController.Company(company_id ,company_name,Available,created_at));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //13.Making the realtion between Table view and list
        tableView.getItems().setAll(list);
    }
    public static class Company{
    // 02.Displaying the Properties using SimpleDatatypesProperties
        private final SimpleStringProperty company_Id;
        private final SimpleStringProperty company_name;
        private final SimpleBooleanProperty availability;
        private final SimpleStringProperty created_at;
        
    //03. Constructor for initialization of the properties using SimpleDataTypeProperties
        Company(String company_Id,String company_name,Boolean availability,String created_at ){
            this.company_Id = new SimpleStringProperty(company_Id);
            this.company_name = new SimpleStringProperty(company_name);
            this.availability = new SimpleBooleanProperty(availability);
            this.created_at = new SimpleStringProperty(created_at);
        }
        //04.genereting the Gatters but we are expeting the string values not simpleStringProperty using get() method

        public String getCompany_Id() {
            return company_Id.get();
        }

        public String getCompany_name() {
            return company_name.get();
        }

        public Boolean getAvailability() {
            return availability.get();
        }

        public String getCreated_at() {
            return created_at.get();
        }

       
        
        
    }
    
}
