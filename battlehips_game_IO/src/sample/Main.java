package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/startView.fxml"));
        Parent scene = fxmlLoader.load();
        System.out.println("1 ");
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(scene));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
