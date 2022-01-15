package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {
    @FXML
    public Button newGameButton;
    @FXML
    public Button replaysButton;
    @FXML
    public Button statisticProfileButton;
    @FXML
    public Button exitButton;



    @FXML
    public void newGameAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/newGameScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)newGameButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("New Game");
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();


    }
    @FXML
    public void replaysAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/replaysMenuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)newGameButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Replays");
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();

    }
    @FXML
    public void statisticProfileAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/statisticProfileScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node)newGameButton).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Statistic/Profile");
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();

    }
    @FXML
    public void exitAction() {
        ((Stage) exitButton.getScene().getWindow()).close();
    }

}
