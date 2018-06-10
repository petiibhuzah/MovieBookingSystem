/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem.list.users;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ListUsersController implements Initializable {
    ObservableList<Customer> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableColumn<Customer, String> cust_id_col;
    @FXML
    private TableColumn<Customer, String> cust_f_name_col;
    @FXML
    private TableColumn<Customer, String> cust_s_name_col;
    @FXML
    private TableColumn<Customer, String> phone_col;
    @FXML
    private TableColumn<Customer, String> address_col;
    @FXML
    private TableColumn<Customer, String> created_at_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }  
    
     private void initCol() {
        cust_id_col.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        cust_f_name_col.setCellValueFactory(new PropertyValueFactory<>("cust_f_name"));
        cust_s_name_col.setCellValueFactory(new PropertyValueFactory<>("cust_s_name"));
        phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        created_at_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
    }

    private void loadData() {
         DatabaseHandler databaseHandler = new DatabaseHandler();
        //10.Using The code as Check data in adding category package
        
         // Creating the query
        String qu = "SELECT * FROM CUSTOMER";
        //Result Object for storing the returned values
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            //Printing Out values
//            customer_id varchar(200) primary key,\n"
//                         +"    f_name varchar(200),\n"
//                         +"    s_name varchar(200),\n"
//                         +"    phone varchar(200),\n"
//                         +"    address varchar(200),\n"
//                         +"    created_at timestamp default current_timestamp"
            while(rs.next()){
                String customer_id = rs.getString("customer_id");
                String f_name = rs.getString("f_name");
                String s_name = rs.getString("s_name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String created_at = rs.getString("created_at");
                
                //12.Adding the values in a list
                list.add(new Customer(customer_id ,f_name,s_name,phone,address,created_at));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //13.Making the realtion between Table view and list
        tableView.getItems().setAll(list);
    }
    
    public static class Customer{
    // 02.Displaying the Properties using SimpleDatatypesProperties
        private final SimpleStringProperty cust_id;
        private final SimpleStringProperty cust_f_name;
        private final SimpleStringProperty cust_s_name;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty address;
        private final SimpleStringProperty created_at;
        
    //03. Constructor for initialization of the properties using SimpleDataTypeProperties
        Customer(String cust_id,String cust_f_name,String cust_s_name,String phone,String address,String created_at ){
            this.cust_id = new SimpleStringProperty(cust_id);
            this.cust_f_name = new SimpleStringProperty(cust_f_name);
            this.cust_s_name = new SimpleStringProperty(cust_s_name);
            this.phone = new SimpleStringProperty(phone);
            this.address = new SimpleStringProperty(address);
            this.created_at = new SimpleStringProperty(created_at);
        }

        public String getCust_id() {
            return cust_id.get();
        }

        public String getCust_f_name() {
            return cust_f_name.get();
        }

        public String getCust_s_name() {
            return cust_s_name.get();
        }

        public String getPhone() {
            return phone.get();
        }

        public String getAddress() {
            return address.get();
        }

        public String getCreated_at() {
            return created_at.get();
        }

       
    
     }
}
