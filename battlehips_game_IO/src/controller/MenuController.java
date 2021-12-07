package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    public Button newGameButton;
    @FXML
    public Button replaceButton;
    @FXML
    public Button statisticProfileButton;
    @FXML
    public Button exitButton;


    @FXML
    public void newGameAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/newGameScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("New Game");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        ((Stage) newGameButton.getScene().getWindow()).close();

    }
    @FXML
    public void replaceAction(){

    }
    @FXML
    public void statisticProfileAction(){

    }
    @FXML
    public void exitAction() {
        ((Stage) exitButton.getScene().getWindow()).close();
    }

}