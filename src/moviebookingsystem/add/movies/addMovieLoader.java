/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviebookingsystem.add.movies;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Peti Ibhuzah
 */
public class addMovieLoader extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("addMovie.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     public static void main(String[] args) {
        launch(args);
    }
    
}
