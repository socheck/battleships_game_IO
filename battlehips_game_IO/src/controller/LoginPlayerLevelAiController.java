package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPlayerLevelAiController {

    @FXML
    public Button loginPlayer1Button;
    @FXML
    public Button backToMenuButton;
    @FXML
    public Button startGameButton;
    @FXML
    public RadioButton easyRadioButton;
    @FXML
    public RadioButton mediumRadioButton;
    @FXML
    public RadioButton hardRadioButton;

    @FXML
    public void startGameAction(){

    }
    @FXML
    public void loginPlayer1Action() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/loginPlayerScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        LoginPlayerController loginPlayerController = (LoginPlayerController) fxmlLoader.getController();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        loginPlayerController.playerLoginLabel.setText("Player 1 Login");
    }

    @FXML
    public void backToMenuAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MENU");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        ((Stage) backToMenuButton.getScene().getWindow()).close();

    }
    @FXML
    public void easyAction(){

            mediumRadioButton.setSelected(false);
            hardRadioButton.setSelected(false);

    }
    @FXML
    public void mediumAction(){
        easyRadioButton.setSelected(false);
        hardRadioButton.setSelected(false);
    }
    @FXML
    public void hardAction(){
        mediumRadioButton.setSelected(false);
        easyRadioButton.setSelected(false);
    }

}
