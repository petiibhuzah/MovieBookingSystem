
package moviebookingsystem.databaseHandler;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public final class DatabaseHandler {
    //01. Declaration of connection variables
    private static final DatabaseHandler handler = null;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
    //End 01
    
    //03. Creating The Constructor for initializing connection and creating table when the programs runs
    public DatabaseHandler(){
        createConnection();
        setupCategoryTable();
        setupBookingTable();
        setupCompanyTable();
        setupMovieTable();
        setupUserTable();
        setupCustomerTable();
    }
    
    //02.Create The Fucntion for creating the connection and loading the drivers
     private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Cant load database", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
       
    }
     //End 02
     
     //04. Creating The Category Table
     void setupCategoryTable(){
         String TABLE_NAME = "CATEGORY";
         try{
             stmt = conn.createStatement();
             DatabaseMetaData dbm = conn.getMetaData();
             ResultSet tables = dbm.getTables(null,null, TABLE_NAME.toUpperCase(), null);
             if (tables.next()) {
                 System.out.println("Table "+TABLE_NAME+" Arleady exists.Ready for go!");
             }else{
                 stmt.execute("CREATE TABLE "+TABLE_NAME+"("
                         +"    id varchar(200) primary key,\n"
                         +"    cat_name varchar(200),\n"
                         +"    available boolean default true,\n"
                         +"    created_at timestamp default current_timestamp"
                         +" )");
             }
         }
         catch(SQLException e){
             System.err.println(e.getMessage()+" -- Setup Database");
         }finally{
       }
     }
     //End 04
    
     //07. Creating The Category Table
     void setupCompanyTable(){
         String TABLE_NAME = "COMPANY";
         try{
             stmt = conn.createStatement();
             DatabaseMetaData dbm = conn.getMetaData();
             ResultSet tables = dbm.getTables(null,null, TABLE_NAME.toUpperCase(), null);
             if (tables.next()) {
                 System.out.println("Table "+TABLE_NAME+" Arleady exists.Ready for go!");
             }else{       stmt.execute("CREATE TABLE "+TABLE_NAME+"("
                         +"    id varchar(200) primary key,\n"
                         +"    company_name varchar(200),\n"
                         +"    available boolean default true,\n"
                         +"    created_at timestamp default current_timestamp"
                         +" )");
          
             }
         }
         catch(SQLException e){
             System.err.println(e.getMessage()+" -- Setup Database");
         }finally{
       }
     }
     //End 07
     
     //08
      private void setupMovieTable() {
       String TABLE_NAME = "MOVIE";
         try{
             stmt = conn.createStatement();
             DatabaseMetaData dbm = conn.getMetaData();
             ResultSet tables = dbm.getTables(null,null, TABLE_NAME.toUpperCase(), null);
             if (tables.next()) {
                 System.out.println("Table "+TABLE_NAME+" Arleady exists.Ready for go!");
             }else{
                 stmt.execute("CREATE TABLE "+TABLE_NAME+"("
                         +"    movie_id varchar(200) primary key,\n"
                         +"    movie_name varchar(200),\n"
                         +"    movie_company varchar(200),\n"
                         +"    movie_category varchar(200),\n"
                         +"    cinema_hall varchar(200),\n"
                         +"    price varchar(200),\n"
                         +"    descriptions varchar(200),\n"
                         +"    available boolean default true,\n"
                         +"    created_at timestamp default current_timestamp"
                         +" )");
             }
         }
         catch(SQLException e){
             System.err.println(e.getMessage()+" -- Setup Database");
         }finally{
       }
    }
      //END 08
       private void setupUserTable() {
       
           
    }
      //08
      private void setupCustomerTable() {
        String TABLE_NAME = "CUSTOMER";
         try{
             stmt = conn.createStatement();
             DatabaseMetaData dbm = conn.getMetaData();
             ResultSet tables = dbm.getTables(null,null, TABLE_NAME.toUpperCase(), null);
             if (tables.next()) {
                 System.out.println("Table "+TABLE_NAME+" Arleady exists.Ready for go!");
             }else{
                 stmt.execute("CREATE TABLE "+TABLE_NAME+"("
                         +"    customer_id varchar(200) primary key,\n"
                         +"    f_name varchar(200),\n"
                         +"    s_name varchar(200),\n"
                         +"    phone varchar(200),\n"
                         +"    address varchar(200),\n"
                         +"    created_at timestamp default current_timestamp"
                         +" )");
             }
         }
         catch(SQLException e){
             System.err.println(e.getMessage()+" -- Setup Database");
         }finally{
       }
    }
     
     //END 08
     private void setupBookingTable() {
        String TABLE_NAME = "BOOKING";
         try{
             stmt = conn.createStatement();
             DatabaseMetaData dbm = conn.getMetaData();
             ResultSet tables = dbm.getTables(null,null, TABLE_NAME.toUpperCase(), null);
             if (tables.next()) {
                 System.out.println("Table "+TABLE_NAME+" Arleady exists.Ready for go!");
             }else{
                 stmt.execute("CREATE TABLE "+TABLE_NAME+"("
                         +"    booking_id varchar(200) primary key,\n"
                         +"    movie_name varchar(200),\n"
                         +"    customer_name varchar(200),\n"
                         +"    booking_date varchar(200),\n"
                         +"    session varchar(200),\n"
                         +"    created_at timestamp default current_timestamp"
                         +" )");
             }
         }
         catch(SQLException e){
             System.err.println(e.getMessage()+" -- Setup Database");
         }finally{
       }
    }
     //05. Executing Query function
     // Query Return something 
     // -select    
     // Return Value
     public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }
     //End 05

     //06. Executing Action
     // Actions
     //-Insert Data
     //-Delect Data
     //-Update Data
     // Does Not return any value
    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        } finally {
        }
    }
    //End o6

   

    
   
}
