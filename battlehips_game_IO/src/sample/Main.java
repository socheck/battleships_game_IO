package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        primaryStage.setTitle("MENU");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
