/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem.list.movies;

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

/**
 * FXML Controller class
 *
 * @author Peti Ibhuzah
 */
public class ListMoviesController implements Initializable {
    ObservableList<Movie> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Movie> tableView;
    @FXML
    private TableColumn<Movie, String> mv_id_col;
    @FXML
    private TableColumn<Movie, String> mv_name_col;
    @FXML
    private TableColumn<Movie, String> company_col;
    @FXML
    private TableColumn<Movie, String> cat_col;
    @FXML
    private TableColumn<Movie, String> cinema_hall_col;
    @FXML
    private TableColumn<Movie, String> Rate_col;
    @FXML
    private TableColumn<Movie, String> desc_col;
    @FXML
    private TableColumn<Movie, String> avail_col;
    @FXML
    private TableColumn<Movie, String> created_at;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initCol();
      loadData();
    }    
    private void initCol() {
        mv_id_col.setCellValueFactory(new PropertyValueFactory<>("Movie_Id"));
        mv_name_col.setCellValueFactory(new PropertyValueFactory<>("Movie_Name"));
        company_col.setCellValueFactory(new PropertyValueFactory<>("Company"));
        cat_col.setCellValueFactory(new PropertyValueFactory<>("Movie_Category"));
        cinema_hall_col.setCellValueFactory(new PropertyValueFactory<>("Cinema_Hall"));
        Rate_col.setCellValueFactory(new PropertyValueFactory<>("Price"));
        desc_col.setCellValueFactory(new PropertyValueFactory<>("Description"));
        avail_col.setCellValueFactory(new PropertyValueFactory<>("availability"));
        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
    }

    private void loadData() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String qu = "SELECT * FROM MOVIE";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
// stmt.execute("CREATE TABLE "+TABLE_NAME+"("
//                         +"    movie_id varchar(200) primary key,\n"
//                         +"    movie_name varchar(200),\n"
//                         +"    movie_company varchar(200),\n"
//                         +"    movie_category varchar(200),\n"
//                         +"    cinema_hall varchar(200),\n"
//                         +"    price varchar(200),\n"
//                         +"    descriptions varchar(200),\n"
//                         +"    available boolean default true,\n"
//                         +"    created_at timestamp default current_timestamp"
//                         +" )");
            while(rs.next()){
                String Movie_Id = rs.getString("movie_id");
                String Movie_Name = rs.getString("movie_name");
                String Company = rs.getString("movie_company");
                String Movie_Category = rs.getString("movie_category");
                String Cinema_Hall = rs.getString("cinema_hall");
                String Rate = rs.getString("price");
                String Description = rs.getString("descriptions");
                Boolean Available = rs.getBoolean("available");
                String created_at = rs.getString("created_at");
                
                //12.Adding the values in a list
                list.add(new Movie(Movie_Id ,Movie_Name,Company,Movie_Category,Cinema_Hall,Rate,Description,Available,created_at));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListMoviesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         tableView.getItems().setAll(list);
    }
    
     public static class Movie{
   
        private final SimpleStringProperty Movie_Id;
        private final SimpleStringProperty Movie_Name;
        private final SimpleStringProperty Company;
        private final SimpleStringProperty Movie_Category;
        private final SimpleStringProperty Cinema_Hall;
        private final SimpleStringProperty Price;
        private final SimpleStringProperty Description;
        private final SimpleBooleanProperty availability;
        private final SimpleStringProperty created_at;
        
        Movie(String Movie_Id,String Movie_Name,String Company,String Movie_Category,String Cinema_Hall,String Price,String Description,Boolean availability,String created_at ){
            this.Movie_Id = new SimpleStringProperty(Movie_Id);
            this.Movie_Name = new SimpleStringProperty(Movie_Name);
            this.Company = new SimpleStringProperty(Company);
            this.Movie_Category = new SimpleStringProperty(Movie_Category);
            this.Cinema_Hall = new SimpleStringProperty(Cinema_Hall);
            this.Price = new SimpleStringProperty(Price);
            this.Description = new SimpleStringProperty(Description);
            this.availability = new SimpleBooleanProperty(availability);
            this.created_at = new SimpleStringProperty(created_at);
        }

        public String getCinema_Hall() {
            return Cinema_Hall.get();
        }

        public String getMovie_Id() {
            return Movie_Id.get();
        }

        public String getMovie_Name() {
            return Movie_Name.get();
        }

        public String getCompany() {
            return Company.get();
        }

        public String getMovie_Category() {
            return Movie_Category.get();
        }

        public String getPrice() {
            return Price.get();
        }

        public String getDescription() {
            return Description.get();
        }

        public Boolean getAvailability() {
            return availability.get();
        }

        public String getCreated_at() {
            return created_at.get();
        }
        
     }
     
   
}
