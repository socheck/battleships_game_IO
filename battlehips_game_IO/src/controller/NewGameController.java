package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class NewGameController {

    @FXML
    public Button playerVsPlayerButton;
    @FXML
    public Button playerVsAiButton;
    @FXML
    public Button aiVsAiButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button backToMenuButton;


    @FXML
    public void playerVsPlayerAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayer1Player2Screen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Players login");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        ((Stage) backToMenuButton.getScene().getWindow()).close();

    }
    @FXML
    public void playerVsAiAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayerLevelAiScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LoginPlayerLevelAiController loginPlayerLevelAiController = (LoginPlayerLevelAiController) fxmlLoader.getController();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("MENU");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        ((Stage) backToMenuButton.getScene().getWindow()).close();
        loginPlayerLevelAiController.setStartGameButtonDisable();

    }
    @FXML
    public void aiVsAiAction(){

    }
    @FXML
    public void exitAction(){
        ((Stage) exitButton.getScene().getWindow()).close();
    }
    @FXML
    public void  backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MENU");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        ((Stage) backToMenuButton.getScene().getWindow()).close();

    }


}
