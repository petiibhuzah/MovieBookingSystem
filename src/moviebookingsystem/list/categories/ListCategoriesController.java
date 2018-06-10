/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem.list.categories;

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
import javafx.scene.layout.AnchorPane;
import moviebookingsystem.databaseHandler.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Peti Ibhuzah
 */
public class ListCategoriesController implements Initializable {
     ObservableList<Category> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Category> tableView;
    @FXML
    private TableColumn<Category, String> cat_id_col;
    @FXML
    private TableColumn<Category, String> cat_name_col;
    @FXML
    private TableColumn<Category, Boolean> avail_col;
    @FXML
    private TableColumn<Category, String> created_at_col;

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
        cat_id_col.setCellValueFactory(new PropertyValueFactory<>("category_Id"));
        cat_name_col.setCellValueFactory(new PropertyValueFactory<>("category_Name"));
        avail_col.setCellValueFactory(new PropertyValueFactory<>("availability"));
        created_at_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        
    }

    private void loadData() {
        //09.Creating The Database Hundler Object Locally for database connectivity
        DatabaseHandler databaseHandler = new DatabaseHandler();
        //10.Using The code as Check data in adding category package
        
         // Creating the query
        String qu = "SELECT * FROM CATEGORY";
        System.out.println(qu);
        //Result Object for storing the returned values
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            //Printing Out values
            while(rs.next()){
                String category_id = rs.getString("id");
                String category_name = rs.getString("cat_name");
                Boolean Available = rs.getBoolean("available");
                String created_at = rs.getString("created_at");
                
                //12.Adding the values in a list
                list.add(new Category(category_id ,category_name,Available,created_at));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //13.Making the realtion between Table view and list
        tableView.getItems().setAll(list);
    }
    
     public static class Category{
    // 02.Displaying the Properties using SimpleDatatypesProperties
        private final SimpleStringProperty category_Id;
        private final SimpleStringProperty category_Name;
        private final SimpleBooleanProperty availability;
        private final SimpleStringProperty created_at;
        
    //03. Constructor for initialization of the properties using SimpleDataTypeProperties
        Category(String category_Id,String category_Name,Boolean availability,String created_at ){
            this.category_Id = new SimpleStringProperty(category_Id);
            this.category_Name = new SimpleStringProperty(category_Name);
            this.availability = new SimpleBooleanProperty(availability);
            this.created_at = new SimpleStringProperty(created_at);
        }
        //04.genereting the Gatters but we are expeting the string values not simpleStringProperty using get() method

        public String getCategory_Id() {
            return category_Id.get();
        }

        public String getCategory_Name() {
            return category_Name.get();
        }

        public Boolean getAvailability() {
            return availability.get();
        }

        public String getCreated_at() {
            return created_at.get();
        }

       
        
        
    }
    
}
